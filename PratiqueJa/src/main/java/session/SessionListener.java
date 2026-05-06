package session;

import java.text.SimpleDateFormat;
import java.util.Date;

import bean.AutenticacaoBean;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener
{

	@Inject
	AutenticacaoBean autenticacaoBean;

	public void sessionCreated(HttpSessionEvent event)
	{
		System.out.println("Sessão criada " + event.getSession().getId());
	}

	public void sessionDestroyed(HttpSessionEvent event)
	{
		autenticacaoBean.salvarAcessoLogout(event.getSession().getId(), true);
		String ultimoAcesso = (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(new Date(event.getSession().getLastAccessedTime()));
		System.out.println("Sessão expirada " + event.getSession().getId() + ". Ultimo Acesso = " + ultimoAcesso);
	}

}