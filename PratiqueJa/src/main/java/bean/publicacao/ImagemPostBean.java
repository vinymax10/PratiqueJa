package bean.publicacao;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import exceptions.RelacaoException;
import bean.download.Diretorio;
import bean.download.PoolNomesBean;
import bean.util.Mensagem;
import service.ImagemPostService;
import service.ProgramacaoPostService;

@Named
@SessionScoped
public class ImagemPostBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String nome = "Configuração de Post";

	@Inject
	private ConfigPostBean configPostBean;

	@Inject
	private ImagemPostService imagemPostService;

	@Inject
	private ProgramacaoPostService programacaoPostService;

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
				return imagemPost(feed).size() < 3;
			case Premium:
				return imagemPost(feed).size() < 1;
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
			imagemPostService.remover(imagem, diretorio.getEndBackgroundServidor());
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Imagem removida com sucesso.");
			init();
		}
		catch(RelacaoException e)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, e.getMessage());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível remover o " + nome);
		}
		return "";
	}

	private void criarImagemPost(boolean feed)
	{
		try
		{
			long id = configPostBean.getConfigPost().getUsuario().getId();
			String endBase = diretorio.getEndBackgroundServidor();
			String endRel;
			int width, height;

			if(feed)
			{
				endRel = "/background/" + id + "/feed/";
				width = 1080;
				height = 1350;
			}
			else
			{
				endRel = "/background/" + id + "/reel/";
				width = 1080;
				height = 1920;
			}

			ImagemPost imagem = new ImagemPost();
			imagem.setConfigPost(configPostBean.getConfigPost());
			imagem.setFeed(feed);
			imagem = imagemPostService.salvar(uploadedFile, imagem, endBase, endRel, width, height);
			configPostBean.getConfigPost().getImagensFundo().add(imagem);

			Mensagem.send("growl", FacesMessage.SEVERITY_INFO,
			"Upload do arquivo " + uploadedFile.getFileName() + " realizado com sucesso.");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public List<ImagemPost> imagemPost(boolean feed)
	{
		return imagemPostService.listar(configPostBean.getConfigPost(), feed);
	}

	@PostConstruct
	public void init()
	{
		diretorio = poolNomesBean.criarDiretorioSemReserva();
		imagensFeed = programacaoPostService.getImagensFeed();
		imagensReel = programacaoPostService.getImagensReel();

		responsiveOptions = new ArrayList<>();
		responsiveOptions.add(new ResponsiveOption("1440px", 5));
		responsiveOptions.add(new ResponsiveOption("1280px", 4));
		responsiveOptions.add(new ResponsiveOption("768px", 3));
		responsiveOptions.add(new ResponsiveOption("560px", 2));
		responsiveOptions.add(new ResponsiveOption("384px", 1));
	}

	public void changeActiveIndex()
	{
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
