package bean.publicacao;

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

import dao.publicacao.ConfigPostDAO;
import lombok.Data;
import modelo.publicacao.ConfigPost;
import modelo.publicacao.ImagemPost;
import modelo.usuario.Usuario;
import bean.download.Diretorio;
import service.configuracao.DiretorioService;
import bean.util.Mensagem;
import service.publicacao.ImagemPostService;
import service.publicacao.ProgramacaoPostService;
import web.session.Sessao;

@Data
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
	private ProgramacaoPostService programacaoPostService;

	@Inject
	private ImagemPostService imagemPostService;

	private int activeIndex;

	private UploadedFile uploadedFile;

	@Inject
	private DiretorioService diretorioService;

	private Diretorio diretorio;

	public ConfigPost cadastrar(Usuario usuario)
	{
		try
		{
			ConfigPost configPost = new ConfigPost();
			configPost.setUsuario(usuario);
			configPostDAO.salvar(configPost);
			this.configPost = configPost;
			configDefault();
			programacaoPostService.programacaoDefault(configPost);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return configPost;
	}

	public String salvar()
	{
		try
		{
			configPost = configPostDAO.salvar(configPost);
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " salvo com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível salvar o " + nome);
		}
		return "";
	}

	public String uploadLogo(FileUploadEvent event)
	{
		uploadedFile = event.getFile();
		try
		{
			long id = configPost.getUsuario().getId();
			String endBase = diretorio.getConfig().getEndereco();
			String endRel = "/images/background/" + id + "/";

			ImagemPost logo = configPost.getLogo() != null ? configPost.getLogo() : new ImagemPost();
			logo = imagemPostService.salvar(uploadedFile, logo, endBase, endRel, 400, 100);

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
		File file = new File(diretorio.getConfig().getEndereco() + configPost.getLogo().getEndImagem());
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
		configPost = configPostDAO.salvar(configPost);
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Restauração da configuração default realizado com sucesso.");

		return "";
	}

	@PostConstruct
	public void init()
	{
		diretorio = diretorioService.criarDiretorioSemReserva();
		Usuario usuario = Sessao.getUsuarioLogado();
		if(usuario.isCriador())
			configPost = usuario.getConfigPost();
	}

	public void carregarConfigPost()
	{
		this.configPost = configPostDAO.getConfigPost(idConfigPost);
	}
}
