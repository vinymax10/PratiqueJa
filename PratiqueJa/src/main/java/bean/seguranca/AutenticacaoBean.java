package bean.seguranca;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;

import org.primefaces.PrimeFaces;

import bean.download.Diretorio;
import bean.util.Mensagem;
import dao.usuario.UsuarioDAO;
import infra.Navegacao;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Pattern;
import javax.imageio.ImageIO;
import lombok.Data;
import modelo.seguranca.GoogleUserInfo;
import modelo.usuario.Imagem;
import modelo.usuario.Usuario;
import net.coobird.thumbnailator.Thumbnails;
import service.configuracao.DiretorioService;
import service.seguranca.AcessoService;
import service.seguranca.GoogleTokenService;
import service.usuario.RecuperacaoSenhaService;
import util.FileAux;
import util.UrlAux;
import web.session.Sessao;

@Data
@Named
@SessionScoped
public class AutenticacaoBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Pattern(regexp = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}", message = "Email inválido.")
	private String email;
	private String senha;

	private String senhaAntiga;

	/** Campo do diálogo "Esqueci minha senha" — separado do e-mail do formulário de login. */
	private String emailRecuperacao;

	private Usuario usuario;

	@Inject
	private UsuarioDAO usuarioDAO;

	@Inject
	private RecuperacaoSenhaService recuperacaoSenhaService;

	private String urlRedefinirSenha = "/login/redefinirSenha.xhtml";

	@Inject
	private AcessoService acessoService;
	
	@Inject
	private SessaoBean sessaoBean;

	@Inject
	private DiretorioService diretorioService;

	@Inject
	private GoogleTokenService googleTokenService;

	public void init()
	{
		email = "";
		senha = "";
		senhaAntiga="";
		emailRecuperacao = "";
	}

	/**
	 * Ação do diálogo "Esqueci minha senha": envia por e-mail um link com token de uso único
	 * (ver {@link RecuperacaoSenhaService}). A senha atual continua valendo até o link ser usado.
	 */
	public void recuperarSenha()
	{
		// getUsuario ignora parâmetro em branco (varreria a tabela inteira) — barra antes.
		if(emailRecuperacao == null || emailRecuperacao.isBlank())
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Informe o e-mail cadastrado.");
			return;
		}

		Usuario usuarioRecuperacao = usuarioDAO.getUsuario(emailRecuperacao, "");

		if(usuarioRecuperacao == null)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "E-mail não cadastrado.");
			return;
		}

		if(!usuarioRecuperacao.isAtivo())
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Usuário inativo. Entre em contato com o suporte.");
			return;
		}

		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
			.getExternalContext().getRequest();
		recuperacaoSenhaService.solicitar(usuarioRecuperacao, UrlAux.base(request));

		Mensagem.send("growl", FacesMessage.SEVERITY_INFO,
			"Enviamos para " + emailRecuperacao + " um link para você cadastrar uma nova senha. Ele vale por 60 minutos.");

		emailRecuperacao = "";
		PrimeFaces.current().ajax().addCallbackParam("recuperado", true);
	}
	
	public String login()
	{
		usuario = usuarioDAO.getUsuario(email, "");
		if(validarLogin())
		{
			String paginaOrigem = obterPaginaOrigem();
			iniciarSessaoUsuario();
			if(usuario.isResetSenha())
				Navegacao.redirect(urlRedefinirSenha);
			else
			{
				Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_INFO, "Login efetuado com sucesso");
				Navegacao.redirect(paginaOrigem);
			}
		}

		return "";
	}

	/**
	 * Chamado via p:remoteCommand após o login com o Google (google.js).
	 *
	 * O único parâmetro aceito é o ID token ("credential"); e-mail, nome e sub saem das claims
	 * dele depois de verificado ({@link GoogleTokenService}). Ler esses campos direto do request
	 * permitiria a qualquer um postar o e-mail de outra pessoa e entrar na conta dela.
	 */
	public void loginGoogle()
	{
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

		GoogleUserInfo info = googleTokenService.verificar(params.get("credential"));

		if(info == null)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível validar o login com o Google.");
			return;
		}

		usuario = usuarioDAO.getUsuarioGoogle(info.sub());

		if(usuario == null)
		{
			// Vincula a conta já existente de mesmo e-mail: só é seguro porque o e-mail
			// vem do token verificado e com email_verified.
			usuario = usuarioDAO.getUsuario(info.email(), "");
			boolean novo = usuario == null;
			if(novo)
			{
				usuario = new Usuario();
				usuario.setEmail(info.email());
				usuario.setNome(info.name());
			}
			usuario.setSubGoogle(info.sub());
			usuario = usuarioDAO.salvar(usuario);
			salvarFotoGoogle(info.picture());
		}

		if(!usuario.isAtivo())
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Usuário inativo.");
			return;
		}

		iniciarSessaoUsuario();
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Login efetuado com sucesso.");

		// Sem isso o oncomplete recarregaria a página também quando o login falha, e o
		// growl com o motivo se perderia no reload.
		PrimeFaces.current().ajax().addCallbackParam("loginOk", true);
	}

	private void salvarFotoGoogle(String pictureUrl)
	{
		if(pictureUrl == null || pictureUrl.isBlank() || usuario.getFoto() != null)
			return;

		try
		{
			byte[] bytes = redimensionar(pictureUrl);

			Diretorio diretorio = diretorioService.criarDiretorioSemReserva();
			String endBase = diretorio.getConfig().getEndereco();
			String endRel = "/images/usuario/" + usuario.getId() + "/";
			String nomeArquivo = "google.png";

			FileAux.gravarFile(endBase + endRel, nomeArquivo, bytes);

			Imagem foto = new Imagem();
			foto.setEndereco(endRel + nomeArquivo);
			usuario.setFoto(foto);
			usuario = usuarioDAO.salvar(usuario);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	private byte[] redimensionar(String pictureUrl) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(Thumbnails.of(URI.create(pictureUrl).toURL()).size(400, 400).keepAspectRatio(false).asBufferedImage(),
			"png", baos);
		return baos.toByteArray();
	}

	private String obterPaginaOrigem()
	{
		try
		{
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
			String referer = request.getHeader("Referer");
			if(referer != null)
			{
				java.net.URL url = new java.net.URL(referer);
				String path = url.getPath();
				String contextPath = request.getContextPath();
				if(path.startsWith(contextPath))
					path = path.substring(contextPath.length());
				if(!path.contains("/login/") && !path.isBlank())
				{
					String query = url.getQuery();
					return query != null ? path + "?" + query : path;
				}
			}
		}
		catch(Exception ignored) {}
		return sessaoBean.getUrlInicial();
	}

	private boolean validarLogin()
	{
		boolean ok = true;

		if(usuario == null)
		{
			ok = false;
			Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_ERROR, "Usuário não cadastrado.");
		}
		else 
		{
			if(usuario.getSenha() == null || usuario.getSenha().isBlank() || !BCrypt.checkpw(senha, usuario.getSenha()))
			{
				ok = false;
				Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_ERROR, "Usuário e senha incorretos.");
			}
			
			if(!usuario.isAtivo())
			{
				ok = false;
				Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_ERROR, "Usuário inativo.");
			}
		}
		
		return ok;
	}

	private void iniciarSessaoUsuario()
	{
		sessaoBean.iniciarSession(usuario);
		acessoService.registrarLogin(usuario);
	}

	public String logout()
	{
		String paginaAtual = obterPaginaOrigem();

		acessoService.registrarLogout(Sessao.id());
		Sessao.encerrar();
		usuario = null;

		// A flash message precisa ser setada depois de encerrar a sessão antiga, senão
		// ela é descartada junto com o resto dos atributos da sessão invalidada.
		Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_INFO, "Logout efetuado com sucesso.");
		Navegacao.redirect(paginaAtual);

		return "";
	}

	public void validateEmail(FacesContext context, UIComponent component, Object email)
	{
		Usuario usuariosBanco = usuarioDAO.getUsuario((String) email, "");
		if(usuariosBanco == null)
		{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email", "Não cadastrado.");
			throw new ValidatorException(msg);
		}
	}

}
