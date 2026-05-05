package Session;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Modelo.Usuario.Usuario;
import Modelo.Usuario.Enum.PerfilUsuario;

public class PageFilter extends Filtro
{
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	throws IOException, ServletException
	{
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		boolean acesso = false;
		HttpSession session = ((HttpServletRequest) request).getSession(true);

		Usuario usuario = (Usuario) session.getAttribute("UsuarioLogado");

		if(httpRequest.getRequestURI().contains("/matematica/")
		|| httpRequest.getRequestURI().contains("/produtos/")
		|| httpRequest.getRequestURI().contains("/informativo/")
		|| httpRequest.getRequestURI().contains("/download/")
		|| httpRequest.getRequestURI().contains("/questao/"))
			acesso = true;
		
		if(usuario != null)
		{
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

		redirecionar(acesso, request, response, chain);
	}

}