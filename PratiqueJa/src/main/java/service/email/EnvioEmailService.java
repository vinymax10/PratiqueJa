package service.email;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

import infra.CommonsEmail;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.ConcurrencyManagement;
import jakarta.ejb.ConcurrencyManagementType;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;

/**
 * Despachante de e-mails: a cada minuto envia, via SMTP, os e-mails que estão
 * com status PENDENTE. Em caso de falha, incrementa a tentativa e, após
 * {@link #MAX_TENTATIVAS}, marca como falha definitiva.
 */
@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class EnvioEmailService
{
	private static final int MAX_TENTATIVAS = 5;

	/** Tamanho máximo da coluna "erro" no banco. */
	private static final int LIMITE_ERRO = 255;

	private static final Logger logger = Logger.getLogger(EnvioEmailService.class.getName());

	@Inject
	private EmailService emailService;

	/** Evita que dois ciclos rodem ao mesmo tempo, sem bloquear a thread. */
	private final AtomicBoolean enviando = new AtomicBoolean(false);

	@PostConstruct
	public void init()
	{
		logger.info("----------------Envio Email iniciado----------------");
	}

	@Schedule(hour = "*", minute = "*", second = "0", persistent = false)
	public void enviarPendentes()
	{
		if(!enviando.compareAndSet(false, true))
		{
			logger.fine("Envio de e-mails já em andamento; ciclo ignorado.");
			return;
		}

		try
		{
			List<EmailParaEnvio> pendentes = emailService.prepararPendentes();
			if(pendentes.isEmpty())
				return;

			logger.fine("e-mails pendentes: " + pendentes.size());

			for(EmailParaEnvio email : pendentes)
				processar(email);
		}
		finally
		{
			enviando.set(false);
		}
	}

	private void processar(EmailParaEnvio email)
	{
		try
		{
			CommonsEmail.mandarEmail(email);
			emailService.registrarEnvio(email.getId());
		}
		catch(Exception e)
		{
			emailService.registrarFalha(email.getId(), resumirErro(e), MAX_TENTATIVAS);
			logger.warning("Falha ao enviar e-mail id=" + email.getId() + ": " + e.getMessage());
		}
	}

	private String resumirErro(Exception e)
	{
		String mensagem = e.getMessage() != null ? e.getMessage() : e.getClass().getSimpleName();
		return mensagem.length() > LIMITE_ERRO ? mensagem.substring(0, LIMITE_ERRO) : mensagem;
	}
}
