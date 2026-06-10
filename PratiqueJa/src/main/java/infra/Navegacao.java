package infra;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.primefaces.PrimeFaces;

import jakarta.faces.application.NavigationHandler;
import jakarta.faces.context.FacesContext;
import web.session.Sessao;

public class Navegacao
{
	public static void changeURL(String url)
	{
		FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts().add("history.pushState({}, null, '" + url + "');");
	}

	public static void redirect(String url)
	{
		try
		{
			String tabId = Sessao.getRequest("tabId");

			if(tabId != null && !tabId.isEmpty())
			{
				String separator = url.contains("?") ? "&" : "?";
				url = url + separator + "tabId=" + URLEncoder.encode(tabId, StandardCharsets.UTF_8);
			}

			Sessao.externalContext().redirect(url);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void redirectAjax(String url)
	{
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler handler = context.getApplication().getNavigationHandler();
		handler.handleNavigation(context, null, url + "?faces-redirect=true");
	}

	public static void redirectPF(String url, String target)
	{
		PrimeFaces.current().executeScript("window.open('" + url + "', '" + target + "')");
	}

}