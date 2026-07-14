package web.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public abstract class Filtro implements Filter
{

	public void destroy()
	{
	}

	protected void redirecionar(boolean acesso, ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		if(acesso)
		{
			HttpSession session = ((HttpServletRequest) request).getSession(true);
			if(session!=null&&session.getAttribute("page")!=null
			&&!session.getAttribute("page").equals(httpRequest.getRequestURI()))
			{
				session.setAttribute("page", httpRequest.getRequestURI());
			}
			
			if(session!=null&&session.getAttribute("page")==null)
				session.setAttribute("page", httpRequest.getRequestURI());
			
			chain.doFilter(request, response);
		}
		else
		{
			if(isAjax(httpRequest))
			{
				httpResponse.getWriter().print(xmlPartialRedirectToPage(httpRequest, "/acesso/acesso-negado.xhtml"));
				httpResponse.flushBuffer();
			}
			else
			{
				String contextPath = ((HttpServletRequest) request).getContextPath();
				httpResponse.sendRedirect(contextPath + "/acesso/acesso-negado.xhtml");
			}
		}
	}

	protected String xmlPartialRedirectToPage(HttpServletRequest request, String page)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<partial-response><redirect url=\"").append(request.getContextPath()).append(page).append("\"/></partial-response>");
		return sb.toString();
	}

	protected boolean isAjax(HttpServletRequest request)
	{
		return ("XMLHttpRequest".equals(request.getHeader("X-Requested-With")) || "partial/ajax".equals(request.getHeader("Faces-Request")));
	}

	public void init(FilterConfig arg0) throws ServletException
	{

	}

}