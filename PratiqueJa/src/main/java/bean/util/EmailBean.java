package bean.util;

import java.io.Serializable;
import java.util.List;

import modelo.email.Email;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import service.EmailService;

@Data
@Named
@ViewScoped
public class EmailBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private EmailService emailService;

	private Email email;
	private List<Email> emails;

	@PostConstruct
	public void init()
	{
		email = new Email();
		carregarEmails();
	}

	public void carregarEmails()
	{
		emails = emailService.listarPendentes();
	}

	public void adicionar()
	{
		emailService.adicionar(email);
		email = new Email();
		carregarEmails();
	}

	public void remover(Email email)
	{
		emailService.remover(email);
		carregarEmails();
	}

}
