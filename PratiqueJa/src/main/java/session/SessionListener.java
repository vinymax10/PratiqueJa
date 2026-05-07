package session;

import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import service.AcessoService;

public class SessionListener implements HttpSessionListener
{

	@Inject
	private AcessoService acessoService;

	public void sessionCreated(HttpSessionEvent event)
	{
		System.out.println("Sessão criada " + event.getSession().getId());
	}

	public void sessionDestroyed(HttpSessionEvent event)
	{
		acessoService.registrarLogout(event.getSession().getId());
		String ultimoAcesso = (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(new Date(event.getSession().getLastAccessedTime()));
		System.out.println("Sessão expirada " + event.getSession().getId() + ". Ultimo Acesso = " + ultimoAcesso);
	}

}