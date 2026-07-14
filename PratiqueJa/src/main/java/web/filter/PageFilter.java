package web.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.usuario.Usuario;

public class PageFilter extends Filtro
{
	private static final Logger LOG = LoggerFactory.getLogger(PageFilter.class);

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	throws IOException, ServletException
	{
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		boolean acesso = false;
		HttpSession session = ((HttpServletRequest) request).getSession(true);
		
		try
		{
			Usuario usuario = (Usuario) session.getAttribute("UsuarioLogado");
			String uri = httpRequest.getRequestURI();

			// Raiz do site ("/") não é uma URL de conteúdo real — sempre foi um atalho pra cair
			// na home, não uma tela protegida. Trata à parte, senão cai no fluxo de "acesso
			// negado" (que hoje mostra a tela de erro em vez de redirecionar pra home).
			String contextPath = httpRequest.getContextPath();
			if(uri.equals(contextPath) || uri.equals(contextPath + "/"))
			{
				((HttpServletResponse) response).sendRedirect(contextPath + "/inicio.xhtml");
				return;
			}

			if(usuario != null)
				MDC.put("usuario", String.valueOf(usuario.getId()));

			// Telas de erro (404/500/acesso negado) sempre acessíveis — senão um acesso negado
			// redireciona pra cá e cai em loop de redirecionamento.
			if(uri.contains("/acesso/"))
			{
				acesso = true;
			}
			// Área administrativa (inclui os fragmentos de exportação/auditoria, que expõem
			// download direto do bean se acessados sem passar pela página que os inclui):
			// ramo exclusivo, só admin logado — nenhuma das regras públicas abaixo se aplica,
			// senão heurísticas como "/questao/" vazariam acesso a /administracao/conteudo/questao/*.
			else if(uri.contains("/administracao/") || uri.contains("/auditoria/") || uri.contains("/exportar/"))
			{
				acesso = usuario != null && usuario.isAdmin();
			}
			else
			{
				if(uri.contains("/matematica/")
				|| uri.contains("/produtos/")
				|| uri.contains("/informativo/")
				|| uri.contains("/download/")
				|| uri.contains("/questao/")
				|| uri.contains("/usuario/perfil")
				// "Gerar Posts" é a porta de entrada da área de Post, navegável sem login (igual
				// "Criar Avaliação"); só a geração de fato exige login — ver PedidoPostBean. As
				// demais abas (Configurações, Programação, Background, Teste, CTAs) continuam
				// exigindo login para entrar, como antes.
				|| uri.contains("/post/gerar/"))
					acesso = true;

				if(usuario != null)
				{
					if(uri.contains("/avaliacao/")
					// Logado: as demais abas de Post (Configurações, Background, Teste, CTAs)
					// ficam liberadas — a ownership do configPost é garantida em
					// ConfigPostBean.sincronizarConfigPost(), não mais aqui no filtro.
					// "/post/programacao" fica de fora: só admin acessa (via usuario.isAdmin() logo
					// abaixo) — CTAs já seguia essa regra só na navTabs, aqui é reforçado no filtro.
					|| (uri.contains("/post/") && !uri.contains("/post/programacao")))
						acesso = true;

					if(uri.contains("/atividades/")
					|| uri.contains("/exercicio/exercicio/verExercicio")
					|| uri.contains("usuario/pagamento/finalizado")
					|| usuario.isAdmin())
						acesso = true;
				}
			}

			LOG.info("Acesso={} à url={}", acesso, httpRequest.getServletPath());
			redirecionar(acesso, request, response, chain);
		}
		finally
		{
			MDC.clear();
		}
	}

}