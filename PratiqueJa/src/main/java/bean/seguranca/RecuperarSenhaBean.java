package bean.seguranca;

import java.io.Serializable;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.ValidaSenha;
import dao.seguranca.PasswordResetTokenDAO;
import dao.usuario.UsuarioDAO;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import modelo.seguranca.PasswordResetToken;
import modelo.usuario.Usuario;
import service.seguranca.AcessoService;
import service.email.EmailService;
import web.session.Sessao;
import bean.util.Mensagem;

@Data
@Named
@SessionScoped
public class RecuperarSenhaBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(RecuperarSenhaBean.class);

	@Pattern(regexp = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}", message = "Email inválido.")
	private String email;
	private String senha;
	private String confirmacaoSenha;

	private Usuario usuario;

	@Inject
	private UsuarioDAO usuarioDAO;

	@Inject
	private EmailService emailService;

	private String urlInicial = "/catalogo/produto/list.xhtml?conta=0&faces-redirect=true";
	private String urlRedefinirSenha = "/login/redefinirSenha.xhtml?faces-redirect=true";
	private String urlLogin = "/login/login.xhtml?faces-redirect=true";

	@Inject
	private AcessoService acessoService;

	@Inject
	private SessaoBean sessaoBean;

	private static final SecureRandom secureRandom = new SecureRandom();

	@Inject
	private PasswordResetTokenDAO passwordResetTokenDAO;

	private String tokenStr;
	private PasswordResetToken token;

	public void init()
	{
		email = "";
		senha = "";
	}

	public String atualizarSenha()
	{
		try
		{
			validarToken();

			usuario = token.getUsuario();
			validarSenha(usuario);

			usuario.setSenha(BCrypt.hashpw(senha, BCrypt.gensalt(12)));
			usuario.setResetSenha(false);
			usuario = usuarioDAO.salvar(usuario);

			token.setUsado(true);
			token.setUsadoEm(LocalDateTime.now());
			token = passwordResetTokenDAO.salvar(token);
			token = null;

			Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_INFO, "Senha atualizada com sucesso.");
			return urlInicial;
		}
		catch(IllegalStateException e)
		{
			LOG.warn("Falha ao redefinir senha: {}", e.getMessage());
			Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_ERROR, e.getMessage());
			return urlInicial;
		}
		catch (IllegalArgumentException e)
		{
			LOG.warn("Senha inválida ao redefinir: {}", e.getMessage());
		    Mensagem.send("form:senha", FacesMessage.SEVERITY_ERROR, e.getMessage());
		    return "";
		}

	}

	public void validarSenha(Usuario usuario)
	{
		ValidaSenha.validarSenha(senha);

		if(BCrypt.checkpw(senha, usuario.getSenha()))
			throw new IllegalArgumentException("Senha deve ser diferente da anterior.");
	}

	public void validarToken()
	{
		token = passwordResetTokenDAO.token(tokenStr);

		if(token == null
		|| token.isUsado()
		|| token.getExpiraEm().isBefore(LocalDateTime.now())
		|| !token.getToken().equals(this.tokenStr))
			throw new IllegalStateException("Este link de redefinição é inválido ou expirou. Solicite um novo link.");
	}

	public String recupearSenha()
	{
		LOG.debug("recupearSenha: {}", email);
		usuario = usuarioDAO.getUsuario(email, "");

		if(usuario != null)
		{
			removerTokenNaoUsados(usuario);

			String token = generateToken();

			LocalDateTime expiraEm = LocalDateTime.now().plusMinutes(30);
			HttpServletRequest request = (HttpServletRequest) Sessao.externalContext().getRequest();
			String userAgent = request.getHeader("User-Agent");
			String ip = request.getRemoteAddr();

			PasswordResetToken resetToken = new PasswordResetToken();
			resetToken.setCriadoEm(LocalDateTime.now());
			resetToken.setUsuario(usuario);
			resetToken.setToken(token);
			resetToken.setExpiraEm(expiraEm);
			resetToken.setIpSolicitacao(ip);
			resetToken.setUserAgent(userAgent);

			passwordResetTokenDAO.adicionar(resetToken);

			String link = "http://localhost:8080/login/reset-senha?token=" + token;

			String assunto = "Redefinição de senha para o Meninas Modas";
			String mensagem = "Olá, " + usuario.getFirstNome() + "\n\n" + "Recebemos uma solicitação para redefinir a senha da sua conta.\n\n"
			+ "Para criar uma nova senha, clique no link abaixo:\n\n" + link + "\n\n" + "Este link é válido por 30 minutos.\n\n"
			+ "Se você não solicitou esta redefinição, ignore este e-mail. Sua conta permanecerá segura.\n\n" + "Atenciosamente,\n\n" + "Equipe do Sistema\n\n";

			emailService.adicionar(email, assunto, mensagem);
		}
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Se existir uma conta com esse e-mail, enviaremos instruções.");

		return "";
	}

	private void removerTokenNaoUsados(Usuario usuario)
	{
		List<PasswordResetToken> tokens = passwordResetTokenDAO.tokenAtivos(usuario);
		for(PasswordResetToken passwordResetToken : tokens)
		{
			passwordResetTokenDAO.remover(passwordResetToken);
		}
	}

	public static String generateToken()
	{
		byte[] randomBytes = new byte[32];
		secureRandom.nextBytes(randomBytes);
		return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
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
