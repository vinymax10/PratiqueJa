package infra;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.application.FacesMessage.Severity;
import jakarta.faces.context.FacesContext;

public class Mensagem
{
	public static void send(String local, Severity severity, String mensagem)
	{
		if(FacesContext.getCurrentInstance()!=null)
		{
			System.out.println("adicionei mensagem: "+mensagem);
			FacesMessage msg = new FacesMessage(severity, mensagem, "");
			FacesContext.getCurrentInstance().addMessage(local, msg);
		}
	}
	
	public static void send(String local, Severity severity, String titulo, String mensagem)
	{
		if(FacesContext.getCurrentInstance()!=null)
		{
			System.out.println("adicionei mensagem: "+mensagem);
			FacesMessage msg = new FacesMessage(severity, titulo, mensagem);
			FacesContext.getCurrentInstance().addMessage(local, msg);
		}
	}
}
