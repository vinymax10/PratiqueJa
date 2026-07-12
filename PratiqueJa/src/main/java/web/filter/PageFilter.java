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

			if(httpRequest.getRequestURI().contains("/matematica/")
			|| httpRequest.getRequestURI().contains("/produtos/")
			|| httpRequest.getRequestURI().contains("/informativo/")
			|| httpRequest.getRequestURI().contains("/download/")
			|| httpRequest.getRequestURI().contains("/questao/")
			|| httpRequest.getRequestURI().contains("/usuario/perfil")
			// "Gerar Posts" é a porta de entrada da área de Post, navegável sem login (igual
			// "Criar Avaliação"); só a geração de fato exige login — ver PedidoPostBean. As
			// demais abas (Configurações, Programação, Background, Teste, CTAs) continuam
			// exigindo login para entrar, como antes.
			|| httpRequest.getRequestURI().contains("/post/gerar/"))
				acesso = true;

			if(usuario != null)
			{
				if(httpRequest.getRequestURI().contains("/avaliacao/")
				// Logado: as demais abas de Post (Configurações, Background, Teste, CTAs)
				// ficam liberadas — a ownership do configPost é garantida em
				// ConfigPostBean.sincronizarConfigPost(), não mais aqui no filtro.
				// "/post/programacao" fica de fora: só admin acessa (via usuario.isAdmin() logo
				// abaixo) — CTAs já seguia essa regra só na navTabs, aqui é reforçado no filtro.
				|| (httpRequest.getRequestURI().contains("/post/")
					&& !httpRequest.getRequestURI().contains("/post/programacao")))
					acesso = true;
				MDC.put("usuario", String.valueOf(usuario.getId()));
				if(httpRequest.getRequestURI().contains("/atividades/")
				||httpRequest.getRequestURI().contains("/exercicio/exercicio/verExercicio")
				||httpRequest.getRequestURI().contains("/teste/teste/verTeste")
				||httpRequest.getRequestURI().contains("usuario/pagamento/finalizado")
				||usuario.isAdmin())
					acesso = true;
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