package service.email;

import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dao.email.EmailDAO;
import filtro.email.FiltroEmail;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.push.Push;
import jakarta.faces.push.PushContext;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import modelo.DocumentoFile;
import modelo.email.Email;
import modelo.email.StatusEmail;

@ApplicationScoped
public class EmailService
{
	@Inject
	private EmailDAO emailDAO;

	@Inject
	@Push(channel = "email")
	private PushContext push;

	public List<Email> listarPendentes()
	{
		return emailDAO.listarPendentes();
	}

	public List<Email> buscar(FiltroEmail filtro)
	{
		return emailDAO.buscar(filtro);
	}

	public void adicionar(String destinatario, String assunto, String mensagem)
	{
		Email email = new Email();
		email.setDestinatario(destinatario);
		email.setAssunto(assunto);
		email.setMensagem(mensagem);
		adicionar(email);
	}

	public void adicionar(String destinatario, String subject, String msg, List<DocumentoFile> documentosFile)
	{
		Email email = new Email();
		email.setDestinatario(destinatario);
		email.setAssunto(subject);
		email.setMensagem(msg);
		email.setDocumentosFile(documentosFile);

		adicionar(email);
	}

	public void adicionar(Email email)
	{
		emailDAO.adicionar(email);
        push.send("email-adicionado");
	}

	public Email salvar(Email email)
	{
		return emailDAO.salvar(email);
	}

	/**
	 * Carrega os e-mails pendentes já com os bytes dos anexos lidos, dentro de
	 * uma transação, para que possam ser enviados via SMTP fora dela.
	 */
	@Transactional
	public List<EmailParaEnvio> prepararPendentes()
	{
		List<EmailParaEnvio> prontos = new ArrayList<>();

		for(Email email : emailDAO.listarPendentes())
		{
			EmailParaEnvio dto = new EmailParaEnvio(email.getId(), email.getDestinatario(),
			email.getAssunto(), email.getMensagem());

			for(DocumentoFile documentoFile : email.getDocumentosFile())
			{
				try
				{
					Blob blob = documentoFile.getFile();
					byte[] dados = blob == null ? new byte[0] : blob.getBytes(1, (int) blob.length());
					dto.adicionarAnexo(documentoFile.getEndDocumentacao(), dados);
				}
				catch(SQLException e)
				{
					throw new RuntimeException("Falha ao ler anexo do e-mail " + email.getId(), e);
				}
			}

			prontos.add(dto);
		}

		return prontos;
	}

	@Transactional
	public void registrarEnvio(Long id)
	{
		Email email = emailDAO.carrega(id);
		if(email == null)
			return;

		email.setStatus(StatusEmail.ENVIADO);
		email.setDataEnvio(LocalDateTime.now());
		email.setErro(null);
		emailDAO.salvar(email);
	}

	@Transactional
	public void registrarFalha(Long id, String erro, int maxTentativas)
	{
		Email email = emailDAO.carrega(id);
		if(email == null)
			return;

		email.incrementaTetativa();
		email.setErro(erro);
		email.setDataEnvio(LocalDateTime.now());

		if(email.getTentativaEnvio() >= maxTentativas)
			email.setStatus(StatusEmail.FALHA_DEFINITIVA);
		// caso contrário permanece PENDENTE e será reprocessado no próximo ciclo.

		emailDAO.salvar(email);
	}

	public void remover(Email email)
	{
		emailDAO.remover(email);
        push.send("email-removido");
	}
}
