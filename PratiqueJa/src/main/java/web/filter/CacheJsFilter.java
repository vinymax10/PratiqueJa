package web.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Obriga o navegador a revalidar os scripts de {@code /js/} a cada visita.
 *
 * <p><b>Por que existe.</b> O WildFly serve esses arquivos sem {@code Cache-Control} e
 * sem {@code ETag} — só com {@code Last-Modified}. Sem prazo declarado, o navegador
 * aplica o cache heurístico: considera o arquivo fresco por ~10% do tempo desde a
 * última modificação. Como o {@code js/google.js} estava parado havia meses, esse
 * prazo era de vários dias, e quem já tinha visitado o site continuou rodando a versão
 * velha do script muito depois do deploy da nova.</p>
 *
 * <p>Foi exatamente isso que derrubou o login pelo Google: o {@code google.js} antigo
 * (em cache) chamava {@code jwt_decode()}, função do {@code js/jwt-decode.js}, que a
 * correção de segurança removeu da página. O script velho quebrava com
 * {@code ReferenceError} antes de postar qualquer coisa — o clique no botão do Google
 * não fazia nada e o servidor sequer registrava a tentativa.</p>
 *
 * <p>{@code no-cache} não proíbe guardar: manda revalidar antes de reusar. O navegador
 * continua mandando {@code If-Modified-Since} e recebendo 304 quando nada mudou, então
 * o custo é uma requisição condicional — e um deploy passa a valer na hora.</p>
 */
public class CacheJsFilter implements Filter
{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	throws IOException, ServletException
	{
		((HttpServletResponse) response).setHeader("Cache-Control", "no-cache, must-revalidate");

		chain.doFilter(request, response);
	}
}
