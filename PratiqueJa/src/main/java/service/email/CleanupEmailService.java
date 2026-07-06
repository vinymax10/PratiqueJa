package service.email;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

import dao.email.EmailDAO;
import modelo.email.Email;

/**
 * Limpeza diária dos e-mails enviados: apaga os anexos após 7 dias e o
 * registro do e-mail após 90 dias contados a partir de dataEnvio.
 */
@Singleton
@Startup
public class CleanupEmailService
{
	private static final int DIAS_ANEXOS = 7;
	private static final int DIAS_EMAIL = 90;

	private static final Logger logger = Logger.getLogger(CleanupEmailService.class.getName());

	@Inject
	private EmailDAO emailDAO;

	@Schedule(hour = "2", minute = "0", second = "0", persistent = false)
	public void removerAntigos()
	{
		LocalDateTime agora = LocalDateTime.now();
		removerAnexos(agora.minusDays(DIAS_ANEXOS));
		removerEmails(agora.minusDays(DIAS_EMAIL));
	}

	private void removerAnexos(LocalDateTime limite)
	{
		List<Email> comAnexos = emailDAO.buscarEnviadosComAnexosAntesDe(limite);
		for(Email email : comAnexos)
		{
			try
			{
				email.getDocumentosFile().clear();
				emailDAO.salvar(email);
			}
			catch(Exception e)
			{
				logger.warning("Falha ao remover anexos do e-mail id=" + email.getId() + ": " + e.getMessage());
			}
		}
	}

	private void removerEmails(LocalDateTime limite)
	{
		List<Email> antigos = emailDAO.buscarEnviadosAntesDe(limite);
		for(Email email : antigos)
		{
			try
			{
				emailDAO.remover(email);
			}
			catch(Exception e)
			{
				logger.warning("Falha ao remover e-mail id=" + email.getId() + ": " + e.getMessage());
			}
		}
	}
}
