package web.webhook;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.seguranca.TokenSenha;
import service.usuario.RecuperacaoSenhaService;

/**
 * Endpoint de retorno do link enviado no e-mail de "Esqueci minha senha". Valida o token e
 * redireciona para a tela própria de cadastro da nova senha ({@value #PAGINA}).
 *
 * O token fica só neste passo: o id do {@link TokenSenha} vai para a sessão e o navegador é
 * redirecionado para uma URL limpa — assim o token não fica no histórico nem vaza pelo
 * cabeçalho Referer dos recursos da página.
 */
@WebServlet(RecuperacaoSenhaService.CAMINHO_RETORNO)
public class RecuperacaoSenhaServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(RecuperacaoSenhaServlet.class);

	/** Tela própria para refazer a senha. */
	public static final String PAGINA = "/login/novaSenha.xhtml";

	/** Id do TokenSenha validado, lido pelo NovaSenhaBean. */
	public static final String ATRIBUTO_SESSAO = "TOKEN_SENHA_ID";

	@Inject
	private RecuperacaoSenhaService recuperacaoSenhaService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		TokenSenha tokenSenha = recuperacaoSenhaService.validar(request.getParameter("token"));

		if(tokenSenha == null)
		{
			// Sem sessão nova para link inválido/expirado (bot ou link velho não cria sessão).
			LOG.info("Link de redefinição de senha inválido ou expirado");
			response.sendRedirect(request.getContextPath() + PAGINA + "?expirado=1");
			return;
		}

		HttpSession session = request.getSession(true);
		session.setAttribute(ATRIBUTO_SESSAO, tokenSenha.getId());

		response.sendRedirect(request.getContextPath() + PAGINA);
	}
}
