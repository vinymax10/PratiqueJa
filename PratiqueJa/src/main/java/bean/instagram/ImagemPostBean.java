package bean.instagram;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import modelo.publicacao.Background;
import modelo.publicacao.ConfigPost;
import modelo.publicacao.ImagemPost;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.ResponsiveOption;
import org.primefaces.model.file.UploadedFile;

import util.FileAux;
import infra.Graphics;
import dao.instagram.BackgroundDAO;
import dao.instagram.ImagemPostDAO;
import exceptions.RelacaoException;
import bean.download.Diretorio;
import bean.download.PoolNomesBean;
import bean.util.Mensagem;

@Named
@SessionScoped
public class ImagemPostBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String nome = "Configuração de Post";

	@Inject
	private ConfigPostBean configPostBean;

	@Inject
	private ImagemPostDAO imagemPostDAO;

	@Inject
	private BackgroundDAO backgroundDAO;
	
	private List<Background> imagensFeed;
	private List<Background> imagensReel;

	private UploadedFile uploadedFile;
	
    private int activeIndex = 0;

	@Inject
	private PoolNomesBean poolNomesBean;
	
	private Diretorio diretorio;
    
    private List<ResponsiveOption> responsiveOptions;
    
    public boolean podeFazerUpload(boolean feed)
    {
    	ConfigPost configPost = configPostBean.getConfigPost();
    	switch(configPost.getPerfilCriador())
    	{
    		case Basico: return false;
    		case Master: 
    			if(imagemPost(feed).size()>=3)
    				return false;
	    		else
	    			return true;
    			
    		case Premium: 
    			if(imagemPost(feed).size()>=1)
    				return false;
	    		else
	    			return true;
    	}
    	return false;
    }
    
	public String uploadFeed(FileUploadEvent event)
	{
		uploadedFile = event.getFile();
		criarImagemPost(true);
		return "";
	}
	
	public String uploadReel(FileUploadEvent event)
	{
		uploadedFile = event.getFile();
		criarImagemPost(false);
		return "";
	}
	
	public String remover(ImagemPost imagem)
	{
		try
		{
			podeRemover(imagem);
			File file=new File(diretorio.getEndBackgroundServidor()+imagem.getEndImagem());
			file.delete();

			imagemPostDAO.remover(imagem);
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Imagem removida com sucesso.");
			init();
		}
		catch (RelacaoException e)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível remover o " + nome);
		}
		return "";
	}
	
	private void podeRemover(ImagemPost imagem) throws RelacaoException
	{
		if((imagem.isFeed()&&imagem.getProgramacoesFeed().size() > 0)||((!imagem.isFeed()&&imagem.getProgramacoesReel().size() > 0)))
			throw new RelacaoException("Não foi possível remover a imagem. Existem programações relacionadas.");
	}
	
	private void criarImagemPost(boolean feed)
	{
		try
		{
			ImagemPost imagem = new ImagemPost();

			long id=configPostBean.getConfigPost().getUsuario().getId();
			String endBase=diretorio.getEndBackgroundServidor();
			String nome=uploadedFile.getFileName();

			String endRel;
			byte[] bytes;
			
			if(feed)
			{
				endRel="/background/"+id+"/feed/";
				bytes=Graphics.shapeImage(uploadedFile, 1080, 1350);
			}
			else
			{
				endRel="/background/"+id+"/reel/";
				bytes=Graphics.shapeImage(uploadedFile, 1080, 1920);
			}
			
			FileAux.gravarFile(endBase+endRel,nome,bytes);
			
			imagem.setEndImagem(endRel+nome);
			imagem.setConfigPost(configPostBean.getConfigPost());
			imagem.setFeed(feed);
			imagem.setNome(nome);
			imagemPostDAO.salvar(imagem);
			configPostBean.getConfigPost().getImagensFundo().add(imagem);
			
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Upload do arquivo " + uploadedFile.getFileName() + " realizado com sucesso.");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public List<ImagemPost> imagemPost(boolean feed)
	{
		return imagemPostDAO.carrega(configPostBean.getConfigPost(),feed);
	}
	
	protected void carregarBackground()
	{
		Background background;
		
		for(int i = 1; i <= 103; i++)
		{
			background=new Background();
			background.setEndereco("/background/feed/background"+i+".png");
			background.setNome("Background "+i);
			background.setFeed(true);
			backgroundDAO.salvar(background);

			background=new Background();
			background.setEndereco("/background/reel/background"+i+".png");
			background.setNome("Background "+i);
			background.setFeed(false);
			backgroundDAO.salvar(background);
		}
	}
	
	public Background aleatorioPadraoFeed()
	{
		Random rand=new Random();
		return imagensFeed.get(rand.nextInt(imagensFeed.size()));
	}
	
	public Background aleatorioPadraoReel()
	{
		Random rand=new Random();
		return imagensReel.get(rand.nextInt(imagensReel.size()));
	}
	
	public ImagemPost aleatorioImagemPostFeed()
	{
		Random rand=new Random();
		List<ImagemPost> imagens=imagemPost(true);
		return imagens.get(rand.nextInt(imagens.size()));
	}
	
	public ImagemPost aleatorioImagemPostReel()
	{
		Random rand=new Random();
		List<ImagemPost> imagens=imagemPost(false);
		return imagens.get(rand.nextInt(imagens.size()));	
	}
	
	@PostConstruct
	public void init()
	{
//		carregarBackground();
		diretorio=poolNomesBean.criarDiretorioSemReserva();

		imagensFeed = backgroundDAO.carregar(true);
		imagensReel = backgroundDAO.carregar(false);
		
		responsiveOptions = new ArrayList<>();
		responsiveOptions.add(new ResponsiveOption("1440px", 5));
        responsiveOptions.add(new ResponsiveOption("1280px", 4));
        responsiveOptions.add(new ResponsiveOption("768px", 3));
        responsiveOptions.add(new ResponsiveOption("560px", 2));
        responsiveOptions.add(new ResponsiveOption("384px", 1));
	}
	
	public void changeActiveIndex() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.activeIndex = Integer.valueOf(params.get("index"));
    }

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public List<Background> getImagensFeed()
	{
		return imagensFeed;
	}

	public void setImagensFeed(List<Background> imagensFeed)
	{
		this.imagensFeed = imagensFeed;
	}

	public List<Background> getImagensReel()
	{
		return imagensReel;
	}

	public void setImagensReel(List<Background> imagensReel)
	{
		this.imagensReel = imagensReel;
	}

	public int getActiveIndex()
	{
		return activeIndex;
	}

	public void setActiveIndex(int activeIndex)
	{
		this.activeIndex = activeIndex;
	}

	public List<ResponsiveOption> getResponsiveOptions()
	{
		return responsiveOptions;
	}

	public void setResponsiveOptions(List<ResponsiveOption> responsiveOptions)
	{
		this.responsiveOptions = responsiveOptions;
	}

}
