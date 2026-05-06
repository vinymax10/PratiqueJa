package infra;

import java.io.IOException;

import jakarta.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

public class Navegacao
{
	public static void redirect(String url)
	{
		try
		{
			FacesContext.getCurrentInstance().getExternalContext().redirect(url);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void redirectPF(String url, String target)
	{
		PrimeFaces.current().executeScript("window.open('"+url+"', '"+target+"')");
	}
	
	
}
