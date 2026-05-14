package web.session;

import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import modelo.usuario.Usuario;

public class Sessao
{
	private Sessao()
	{
		// impede instanciação
	}

	public static ExternalContext externalContext()
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		if(fc == null)
		{
			throw new IllegalStateException("Sessao só pode ser acessada dentro de uma requisição JSF");
		}
		return fc.getExternalContext();
	}

	public static void encerrar()
	{
		externalContext().invalidateSession();
	}
	
	public static Object get(String chave)
	{
		return externalContext().getSessionMap().get(chave);
	}
	
	public static String getRequest(String chave)
	{
		return externalContext().getRequestParameterMap().get(chave);
	}

	public static void set(String chave, Object valor)
	{
		externalContext().getSessionMap().put(chave, valor);
	}
	
	public static void setRequest(String chave, String valor)
	{
		externalContext().getRequestParameterMap().put(chave, valor);
	}
	
	public static String id()
	{
	    return externalContext().getSessionId(false);
	}

	public static boolean estaLogado()
	{
		return get("UsuarioLogado") != null;
	}

	public static Usuario getUsuarioLogado()
	{
		return (Usuario) get("UsuarioLogado");
	}

}
