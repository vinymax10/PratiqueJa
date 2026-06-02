package web.listener;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import service.seguranca.AcessoService;

public class SessionListener implements HttpSessionListener
{
	private static final Logger LOG = LoggerFactory.getLogger(SessionListener.class);

	@Inject
	private AcessoService acessoService;

	public void sessionCreated(HttpSessionEvent event)
	{
		LOG.info("Sessão criada {}", event.getSession().getId());
	}

	public void sessionDestroyed(HttpSessionEvent event)
	{
		acessoService.registrarLogout(event.getSession().getId());
		String ultimoAcesso = (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(new Date(event.getSession().getLastAccessedTime()));
		LOG.info("Sessão expirada {}. Ultimo Acesso = {}", event.getSession().getId(), ultimoAcesso);
	}

}