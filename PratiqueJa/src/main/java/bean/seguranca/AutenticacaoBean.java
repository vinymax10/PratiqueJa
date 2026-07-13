package bean.seguranca;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;

import bean.download.Diretorio;
import bean.email.EmailBean;
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
import modelo.usuario.Imagem;
import modelo.usuario.Usuario;
import net.coobird.thumbnailator.Thumbnails;
import service.configuracao.DiretorioService;
import service.seguranca.AcessoService;
import util.FileAux;
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
	
	private Usuario usuario;

	@Inject
	private UsuarioDAO usuarioDAO;

	@Inject
	private EmailBean emailBean;
	
	private String urlRedefinirSenha = "/login/redefinirSenha.xhtml";

	@Inject
	private AcessoService acessoService;
	
	@Inject
	private SessaoBean sessaoBean;

	@Inject
	private DiretorioService diretorioService;

	public void init()
	{
		email = "";
		senha = "";
		senhaAntiga="";
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

	/** Chamado via p:remoteCommand após o login com o Google (google.js). */
	public void loginGoogle()
	{
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String emailGoogle = params.get("email");
		String nome = params.get("name");
		String sub = params.get("sub");
		String picture = params.get("picture");

		usuario = usuarioDAO.getUsuarioGoogle(sub);

		if(usuario == null)
		{
			usuario = usuarioDAO.getUsuario(emailGoogle, "");
			boolean novo = usuario == null;
			if(novo)
			{
				usuario = new Usuario();
				usuario.setEmail(emailGoogle);
				usuario.setNome(nome);
			}
			usuario.setSubGoogle(sub);
			usuario = usuarioDAO.salvar(usuario);
			salvarFotoGoogle(picture);
		}

		if(!usuario.isAtivo())
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Usuário inativo.");
			return;
		}

		iniciarSessaoUsuario();
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Login efetuado com sucesso.");
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
