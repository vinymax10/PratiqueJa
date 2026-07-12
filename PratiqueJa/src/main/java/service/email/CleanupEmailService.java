package service.email;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

import dao.email.EmailDAO;
import modelo.DocumentoFile;
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
				apagarArquivos(email.getDocumentosFile());
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
				// Defensivo: se por algum motivo os anexos ainda não tinham sido limpos.
				apagarArquivos(email.getDocumentosFile());
				emailDAO.remover(email);
			}
			catch(Exception e)
			{
				logger.warning("Falha ao remover e-mail id=" + email.getId() + ": " + e.getMessage());
			}
		}
	}

	/** Apaga do disco os arquivos dos anexos; o registro em si é removido pelo chamador. */
	private void apagarArquivos(List<DocumentoFile> documentosFile)
	{
		for(DocumentoFile documentoFile : documentosFile)
		{
			if(documentoFile.getCaminhoArquivo() == null)
				continue;
			try
			{
				Files.deleteIfExists(Path.of(documentoFile.getCaminhoArquivo()));
			}
			catch(IOException e)
			{
				logger.warning("Falha ao apagar arquivo de anexo " + documentoFile.getCaminhoArquivo()
					+ ": " + e.getMessage());
			}
		}
	}
}
