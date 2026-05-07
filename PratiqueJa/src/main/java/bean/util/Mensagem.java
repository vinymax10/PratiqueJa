package bean.util;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.application.FacesMessage.Severity;
import jakarta.faces.context.FacesContext;

public class Mensagem
{
	public static void send(String local, Severity severity, String mensagem)
	{
		FacesMessage msg = new FacesMessage(severity, "",mensagem);
		FacesContext.getCurrentInstance().addMessage(local, msg);
	}
	
	public static void sendRedirect(String local, Severity severity, String mensagem)
	{
		FacesMessage msg = new FacesMessage(severity, "",mensagem);
		FacesContext.getCurrentInstance().addMessage(local, msg);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}
	
	public static String messagePermissaoNegada()
	{
		  return "Você não tem permissão para realizar esta ação.";
	}
}

