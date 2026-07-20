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

import bean.usuario.ControleAcessoBean;
import dao.publicacao.ConfigPostDAO;
import dao.usuario.UsuarioDAO;
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
import jakarta.faces.context.FacesContext;

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
	private UsuarioDAO usuarioDAO;

	@Inject
	private ProgramacaoPostService programacaoPostService;

	@Inject
	private ImagemPostService imagemPostService;

	@Inject
	private ControleAcessoBean controleAcessoBean;

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
		if(!controleAcessoBean.verificaEstaLogado())
			return "";

		try
		{
			usuarioDAO.salvar(configPost.getUsuario());
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
		if(!controleAcessoBean.verificaEstaLogado())
			return "";

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
		catch(Exception e)
		{
			// Antes o erro só ia para o stderr (upload "não fazia nada"); agora avisa o usuário.
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR,
				"Não foi possível enviar a imagem. Use um PNG válido de até 5 MB. (" + e.getMessage() + ")");
		}
		return "";
	}

	public String removerLogo()
	{
		if(!controleAcessoBean.verificaEstaLogado())
			return "";

		File file = new File(diretorio.getConfig().getEndereco() + configPost.getLogo().getEndImagem());
		file.delete();
		configPost.setLogo(null);
		configPostDAO.salvar(configPost);
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Imagem removida com sucesso.");

		return "";
	}

	public String configDefault()
	{
		if(!controleAcessoBean.verificaEstaLogado())
			return "";

		configPost = configPostDAO.salvar(configPost);
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Restauração da configuração default realizado com sucesso.");

		return "";
	}

	@PostConstruct
	public void init()
	{
		diretorio = diretorioService.criarDiretorioSemReserva();
		sincronizarConfigPost();
	}

	public void carregarConfigPost()
	{
		sincronizarConfigPost();
	}

	/**
	 * Sincroniza o configPost lendo o parâmetro {@code configPost} DIRETO do request: presente →
	 * aquele config (admin, modo suporte); ausente → volta para o config do próprio usuário logado.
	 * Diferente do {@code f:viewParam} (que mantém o valor antigo na sessão quando o parâmetro some),
	 * isto reseta de fato — usado ao entrar em Gerar Posts pelo menu lateral para sair do modo suporte
	 * e voltar ao próprio configPost.
	 */
	public void sincronizarPeloRequest()
	{
		String p = FacesContext.getCurrentInstance().getExternalContext()
			.getRequestParameterMap().get("configPost");

		if(p == null || p.isBlank())
			idConfigPost = null;
		else
			try { idConfigPost = Long.valueOf(p); }
			catch(NumberFormatException e) { idConfigPost = null; }

		sincronizarConfigPost();
	}

	/**
	 * Alinha o configPost em edição com o usuário da sessão. A tela é navegável sem login (como
	 * "Criar Avaliação"); sem usuário logado fica um objeto vazio só para exibição — as ações que
	 * de fato gravam (salvar, upload) exigem login antes de prosseguir. Um admin pode inspecionar
	 * o configPost de outro usuário via o parâmetro {@code configPost} da URL; para os demais,
	 * ignora esse parâmetro e usa sempre o próprio configPost — inclusive ao recarregar a página
	 * após o login (o formulário de login redireciona de volta para a mesma URL).
	 */
	private void sincronizarConfigPost()
	{
		Usuario usuario = Sessao.getUsuarioLogado();
		if(usuario == null)
		{
			configPost = new ConfigPost();
			return;
		}
		if(idConfigPost != null && usuario.isAdmin())
			configPost = configPostDAO.getConfigPost(idConfigPost);
		else
			configPost = usuario.getConfigPost();
	}

	/**
	 * Dono do configPost em edição quando é OUTRO usuário (admin no modo suporte, via {@code ?configPost=ID}).
	 * {@code null} quando é a própria área do logado. Usado para o chip no cabeçalho das telas de Post.
	 */
	public Usuario getUsuarioVisualizado()
	{
		if(configPost == null || configPost.getUsuario() == null)
			return null;

		Usuario logado = Sessao.getUsuarioLogado();
		if(logado != null && !configPost.getUsuario().getId().equals(logado.getId()))
			return configPost.getUsuario();

		return null;
	}
}
