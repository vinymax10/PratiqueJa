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
import modelo.usuario.PerfilUsuario;
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
			|| httpRequest.getRequestURI().contains("/questao/"))
				acesso = true;

			if(usuario != null)
			{
				if(httpRequest.getRequestURI().contains("/avaliacao/"))
					acesso = true;
				MDC.put("usuario", String.valueOf(usuario.getId()));
				if(httpRequest.getRequestURI().contains("/atividades/")
				||httpRequest.getRequestURI().contains("/exercicio/exercicio/verExercicio")
				||httpRequest.getRequestURI().contains("/teste/teste/verTeste")
				||httpRequest.getRequestURI().contains("usuario/pagamento/finalizado")
				||usuario.getPerfil() == PerfilUsuario.Admin)
					acesso = true;
				
				if(httpRequest.getRequestURI().contains("/post/")
				&&usuario.isCriador())
				{
					String configPost = httpRequest.getParameter("configPost");
		            Long idConfigPost = null;
		            if(!isAjax(httpRequest)&&configPost != null) 
		            {
		                try 
		                {
		                    idConfigPost = Long.valueOf(configPost);
		                    if(usuario.getConfigPost().getId().equals(idConfigPost))
		                    	acesso = true;
		                } 
		                catch (NumberFormatException e) {
		                    e.printStackTrace();
		                }
		            }
		            else
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