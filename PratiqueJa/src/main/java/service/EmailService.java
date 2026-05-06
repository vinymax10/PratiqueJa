package service;

import java.util.List;

import dao.EmailDAO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.push.Push;
import jakarta.faces.push.PushContext;
import jakarta.inject.Inject;
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

	public void adicionar(String destinatario, String assunto, String mensagem)
	{
		Email email = new Email();
		email.setDestinatario(destinatario);
		email.setAssunto(assunto);
		email.setMensagem(mensagem);
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
