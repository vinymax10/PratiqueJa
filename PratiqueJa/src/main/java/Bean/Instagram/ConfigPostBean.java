package Bean.Instagram;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import Auxiliar.FileAux;
import Auxiliar.Graphics;
import Bean.AutenticacaoBean;
import Bean.Download.Diretorio;
import Bean.Download.PoolNomesBean;
import DAO.Instagram.ConfigPostDAO;
import DAO.Instagram.ImagemPostDAO;
import Infra.Mensagem;
import Modelo.Instagram.ConfigPost;
import Modelo.Instagram.ImagemPost;
import Modelo.Usuario.Usuario;

@Named
@SessionScoped
public class ConfigPostBean implements Serializable
{

	private static final long serialVersionUID = 1L;

	private String nome = "Configuração de Post";

	private Long idConfigPost;
	
	@Inject
	private ConfigPost configPost;

	@Inject
	private ConfigPostDAO configPostDAO;

	@Inject
	private ProgramacaoPostBean programacaoPostBean;
	
	@Inject
	private AutenticacaoBean autenticacaoBean;
	
	private int activeIndex;
	
	private UploadedFile uploadedFile;

	@Inject
	private PoolNomesBean poolNomesBean;
	
	private Diretorio diretorio;

	@Inject
	private ImagemPostDAO imagemPostDAO;
	
	public ConfigPost cadastrar(Usuario usuario)
	{
		try
		{
			ConfigPost configPost=new ConfigPost();
			configPost.setUsuario(usuario);
			configPostDAO.salvar(configPost);
			this.configPost=configPost;
			configDefault();
			programacaoPostBean.programacaoDefault();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return configPost;
	}
	
	public String salvar()
	{
		try
		{
			configPost=configPostDAO.salvar(configPost);
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " salvo com sucesso.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível salvar o "+nome);
		}
		return "";
	}

	public String uploadLogo(FileUploadEvent event)
	{
		uploadedFile = event.getFile();
		try
		{

			long id=configPost.getUsuario().getId();
			String endBase=diretorio.getEndBackgroundServidor();
			String nome=uploadedFile.getFileName();

			ImagemPost logo;
			if(configPost.getLogo()==null)
				logo = new ImagemPost();
			else
			{
				logo=configPost.getLogo();
				File destino = new File(endBase+logo.getEndImagem());
				if(destino.exists())
					destino.delete();
			}
			
			String endRel;
			byte[] bytes=Graphics.shapeImage(uploadedFile, 400, 100);
			endRel="/background/"+id+"/";
			
			FileAux.gravarFile(endBase+endRel,nome,bytes);

			logo.setEndImagem(endRel+nome);
			logo.setNome(nome);
			imagemPostDAO.salvar(logo);

			configPost.setLogo(logo);
			configPostDAO.salvar(configPost);
			
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, 
			"Upload do arquivo " + uploadedFile.getFileName() + " realizado com sucesso.");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return "";
	}
	
	public String removerLogo()
	{
		File file=new File(diretorio.getEndBackgroundServidor()+configPost.getLogo().getEndImagem());
		file.delete();
		configPost.setLogo(null);
		configPostDAO.salvar(configPost);
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Imagem removida com sucesso.");

		return "";
	}
	
	public String configDefault()
	{
		configPost.setCorFonte("#4D4D4D");
		configPost.setCorTitulo("#4059E3");
		configPost.setCorNome("#4D4D4D");
		configPost.setCorFormula("#4059E3");

		configPost.setTransparencia(15);
		configPost=configPostDAO.salvar(configPost);
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Restauração da configuração default realizado com sucesso.");

		return "";
	}
	
	@PostConstruct
	public void init()
	{
		diretorio=poolNomesBean.criarDiretorioSemReserva();
		Usuario usuario=autenticacaoBean.getUsuario();
		if(usuario.isCriador())
			configPost=usuario.getConfigPost();
	}

	public void carregarConfigPost()
	{
		this.configPost = configPostDAO.getConfigPost(idConfigPost);
	}
	
	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public ConfigPost getConfigPost()
	{
		return configPost;
	}

	public void setConfigPost(ConfigPost configPost)
	{
		this.configPost = configPost;
	}

	public ConfigPostDAO getConfigPostDAO()
	{
		return configPostDAO;
	}

	public void setConfigPostDAO(ConfigPostDAO configPostDAO)
	{
		this.configPostDAO = configPostDAO;
	}

	public int getActiveIndex()
	{
		return activeIndex;
	}

	public void setActiveIndex(int activeIndex)
	{
		this.activeIndex = activeIndex;
	}

	public Long getIdConfigPost()
	{
		return idConfigPost;
	}

	public void setIdConfigPost(Long idConfigPost)
	{
		this.idConfigPost = idConfigPost;
	}

}
