package service.usuario;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;

import dao.seguranca.TokenSenhaDAO;
import dao.usuario.UsuarioDAO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import modelo.seguranca.TokenSenha;
import modelo.usuario.Usuario;
import service.email.EmailService;

/**
 * Fluxo de "Esqueci minha senha" por token de uso único:
 *
 * <ol>
 *   <li>{@link #solicitar(Usuario, String)} gera um token aleatório, grava só o hash
 *       ({@link TokenSenha}) e manda por e-mail o link de retorno com o token;</li>
 *   <li>o link cai em {@code /recuperar-senha} ({@code RecuperacaoSenhaServlet}), que valida
 *       o token e encaminha para a tela {@code /login/novaSenha.xhtml};</li>
 *   <li>{@link #redefinir(TokenSenha, String)} troca a senha e queima o token.</li>
 * </ol>
 *
 * A senha atual continua valendo enquanto o token não for usado — pedir recuperação não
 * derruba o acesso de quem lembrar a senha depois.
 */
@ApplicationScoped
public class RecuperacaoSenhaService
{
	private static final String ASSUNTO = "Redefinição de senha - Pratique Já";

	/** Caminho do endpoint de retorno do link do e-mail (ver RecuperacaoSenhaServlet). */
	public static final String CAMINHO_RETORNO = "/recuperar-senha";

	/** Tempo de vida do link enviado por e-mail. */
	private static final int VALIDADE_MINUTOS = 60;

	private static final SecureRandom RANDOM = new SecureRandom();

	@Inject
	private TokenSenhaDAO tokenSenhaDAO;

	@Inject
	private UsuarioDAO usuarioDAO;

	@Inject
	private UsuarioService usuarioService;

	@Inject
	private EmailService emailService;

	/**
	 * Cria o token do usuário (invalidando os anteriores) e enfileira o e-mail com o link.
	 *
	 * @param urlBase raiz do site, sem barra final (ex.: {@code https://pratiqueja.com})
	 */
	@Transactional
	public void solicitar(Usuario usuario, String urlBase)
	{
		tokenSenhaDAO.limpar(usuario);

		byte[] bytes = new byte[32];
		RANDOM.nextBytes(bytes);
		String token = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);

		TokenSenha tokenSenha = new TokenSenha();
		tokenSenha.setUsuario(usuario);
		tokenSenha.setHash(hash(token));
		tokenSenha.setCriacao(LocalDateTime.now());
		tokenSenha.setExpiracao(LocalDateTime.now().plusMinutes(VALIDADE_MINUTOS));
		tokenSenhaDAO.salvar(tokenSenha);

		String link = urlBase + CAMINHO_RETORNO + "?token=" + token;
		emailService.adicionar(usuario.getEmail(), ASSUNTO, montarHtml(primeiroNome(usuario), link));
	}

	/** Devolve o token da URL se ele existir, não tiver sido usado e não estiver expirado. */
	public TokenSenha validar(String token)
	{
		if(token == null || token.isBlank())
			return null;

		TokenSenha tokenSenha = tokenSenhaDAO.buscarPorHash(hash(token));
		return tokenSenha != null && tokenSenha.isValido() ? tokenSenha : null;
	}

	/** Troca a senha e queima o token (uso único) — na mesma transação, ou nenhum dos dois. */
	@Transactional
	public void redefinir(TokenSenha tokenSenha, String novaSenha)
	{
		Usuario usuario = usuarioDAO.carrega(tokenSenha.getUsuario().getId());
		usuario.setSenha(usuarioService.hashPassword(novaSenha));
		usuario.setResetSenha(false);
		usuarioDAO.salvar(usuario);

		tokenSenha.setUso(LocalDateTime.now());
		tokenSenhaDAO.salvar(tokenSenha);
	}

	private String hash(String token)
	{
		try
		{
			byte[] digest = MessageDigest.getInstance("SHA-256").digest(token.getBytes(StandardCharsets.UTF_8));
			StringBuilder hex = new StringBuilder(digest.length * 2);
			for(byte b : digest)
				hex.append(Character.forDigit((b >> 4) & 0xF, 16)).append(Character.forDigit(b & 0xF, 16));
			return hex.toString();
		}
		catch(NoSuchAlgorithmException e)
		{
			throw new IllegalStateException("SHA-256 indisponível na JVM", e);
		}
	}

	/** getFirstNome() faz split no nome — usuário vindo do login Google pode estar sem nome. */
	private String primeiroNome(Usuario usuario)
	{
		return usuario.getNome() != null && !usuario.getNome().isBlank() ? usuario.getFirstNome() : "";
	}

	/**
	 * Corpo HTML do e-mail de redefinição, no mesmo padrão visual dos demais e-mails do
	 * Pratique Já. Inicia com "&lt;" para o e-mail ser reconhecido como HTML (ver CommonsEmail).
	 */
	private String montarHtml(String nome, String link)
	{
		String saudacao = nome == null || nome.isBlank() ? "Olá! 👋" : "Olá, <b>" + escapeHtml(nome) + "</b>! 👋";
		String href = escapeHtml(link);

		return "<!DOCTYPE html><html><body style=\"margin:0;padding:0;background:#eef1f8;\">"
		+ "<table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"background:#eef1f8;padding:24px 12px;font-family:Arial,Helvetica,sans-serif;\"><tr><td align=\"center\">"
		+ "<table role=\"presentation\" width=\"600\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:600px;width:100%;background:#ffffff;border-radius:16px;overflow:hidden;border:1px solid #e2e8f4;\">"
		+ "<tr><td style=\"padding:20px 28px;border-bottom:3px solid #2563eb;\">"
		+ "<span style=\"font-size:22px;font-weight:bold;color:#2563eb;\">Pratique<span style=\"color:#de7b40;\">Já</span></span>"
		+ "<span style=\"font-size:12px;color:#8a93a6;float:right;padding-top:9px;\">Redefinição de Senha</span>"
		+ "</td></tr>"
		+ "<tr><td style=\"padding:26px 28px 6px;\">"
		+ "<p style=\"margin:0 0 6px;font-size:17px;color:#2b3445;\">" + saudacao + "</p>"
		+ "<p style=\"margin:0 0 16px;font-size:14px;color:#6b7689;line-height:1.5;\">Recebemos um pedido para redefinir a senha desta conta. Clique no botão abaixo para cadastrar uma nova senha.</p>"
		+ "</td></tr>"
		+ "<tr><td align=\"center\" style=\"padding:10px 28px 6px;\">"
		+ "<a href=\"" + href + "\" style=\"display:inline-block;background:#2563eb;color:#ffffff;text-decoration:none;font-size:15px;font-weight:bold;padding:14px 32px;border-radius:10px;\">Cadastrar nova senha</a>"
		+ "</td></tr>"
		+ "<tr><td align=\"center\" style=\"padding:14px 28px 6px;\">"
		+ "<p style=\"margin:0;font-size:12px;color:#8a93a6;line-height:1.5;\">Se o botão não funcionar, copie e cole este endereço no navegador:<br/>"
		+ "<span style=\"color:#2563eb;word-break:break-all;\">" + href + "</span></p>"
		+ "</td></tr>"
		+ "<tr><td style=\"padding:18px 28px 8px;\">"
		+ "<div style=\"background:#f6f8fc;border:1px dashed #c2cce0;border-radius:12px;padding:14px 18px;\">"
		+ "<p style=\"margin:0;font-size:13px;color:#6b7689;line-height:1.5;\">🔒 O link vale por <b>" + VALIDADE_MINUTOS + " minutos</b> e só pode ser usado uma vez.</p>"
		+ "</div>"
		+ "</td></tr>"
		+ "<tr><td style=\"padding:12px 28px 8px;\">"
		+ "<p style=\"margin:0;font-size:13px;color:#6b7689;line-height:1.5;\">Se não foi você quem pediu, é só ignorar este e-mail — sua senha atual continua valendo.</p>"
		+ "</td></tr>"
		+ "<tr><td style=\"padding:8px 28px 24px;border-top:1px solid #eef1f8;\">"
		+ "<p style=\"margin:14px 0 0;font-size:13px;color:#2563eb;font-weight:bold;\">Equipe do Pratique Já</p>"
		+ "<p style=\"margin:2px 0 0;font-size:12px;color:#8a93a6;\">pratiqueja.com</p>"
		+ "</td></tr>"
		+ "</table></td></tr></table></body></html>";
	}

	/** Escapa os caracteres que quebrariam o HTML (conteúdo vem do banco/usuário). */
	private String escapeHtml(String texto)
	{
		if(texto == null)
			return "";
		return texto.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
	}
}
