package service;

import java.util.List;

import dao.email.EmailDAO;
import filtro.email.FiltroEmail;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.push.Push;
import jakarta.faces.push.PushContext;
import jakarta.inject.Inject;
import modelo.DocumentoFile;
import modelo.email.Email;

@ApplicationScoped
public class EmailService
{
	@Inject
	private EmailDAO emailDAO;

//	private static final String EMAIL_PESSOAL = "vinymax10@gmail.com";

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

	public void remover(Email email)
	{
		emailDAO.remover(email);
        push.send("email-removido");
	}
}
