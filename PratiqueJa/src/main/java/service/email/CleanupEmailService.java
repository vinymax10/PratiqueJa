package service.email;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;

import dao.email.EmailDAO;
import modelo.DocumentoFile;
import modelo.email.Email;

/**
 * Limpeza diária dos e-mails enviados: apaga os anexos após 7 dias e o
 * registro do e-mail após 90 dias contados a partir de dataEnvio.
 *
 * <p><b>Um e-mail, uma transação.</b> A classe é {@code NOT_SUPPORTED} de propósito: sem isso o
 * método do timer roda no default {@code REQUIRED} e as duas varreduras viram um commit só. Um
 * único e-mail com erro marcava a transação para rollback, o {@code catch} do laço engolia, os
 * demais seguiam "com sucesso" e nada era gravado — só que os arquivos já tinham saído do disco.
 * Ficavam registros com anexos que não existem mais.</p>
 *
 * <p>Rodar sem transação exige que as consultas tragam os anexos já carregados (por isso o
 * {@code JOIN FETCH} em {@code EmailDAO}); e a limpeza da coleção, que tem {@code orphanRemoval},
 * é feita pelo {@code limparAnexos(id)}, que recarrega o e-mail dentro da própria transação.</p>
 */
@Singleton
@Startup
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
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
		int limpos = 0;
		int falhas = 0;

		for(Email email : comAnexos)
		{
			try
			{
				// Disco primeiro: deleteIfExists não reclama de arquivo ausente, então se a
				// gravação se perder a rodada de amanhã reencontra o e-mail e conclui a limpeza.
				apagarArquivos(email.getDocumentosFile());
				emailDAO.limparAnexos(email.getId());
				limpos++;
			}
			catch(Exception e)
			{
				falhas++;
				logger.log(Level.WARNING, "Falha ao remover anexos do e-mail id=" + email.getId()
					+ "; os demais seguem.", e);
			}
		}

		if(limpos > 0 || falhas > 0)
			logger.info("Limpeza de anexos de e-mail: " + limpos + " limpo(s), " + falhas + " com falha.");
	}

	private void removerEmails(LocalDateTime limite)
	{
		List<Email> antigos = emailDAO.buscarEnviadosAntesDe(limite);
		int removidos = 0;
		int falhas = 0;

		for(Email email : antigos)
		{
			try
			{
				// Defensivo: se por algum motivo os anexos ainda não tinham sido limpos.
				apagarArquivos(email.getDocumentosFile());
				emailDAO.remover(email);
				removidos++;
			}
			catch(Exception e)
			{
				falhas++;
				logger.log(Level.WARNING, "Falha ao remover e-mail id=" + email.getId()
					+ "; os demais seguem.", e);
			}
		}

		if(removidos > 0 || falhas > 0)
			logger.info("Limpeza de e-mails antigos: " + removidos + " removido(s), " + falhas + " com falha.");
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
