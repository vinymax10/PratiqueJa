package bean.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import filtro.email.FiltroEmail;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import modelo.email.Email;
import modelo.email.StatusEmail;
import service.EmailService;

@Data
@Named
@ViewScoped
public class EmailBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private EmailService emailService;

	@Inject
	private FiltroEmail filtro;

	private List<Email> emails = new ArrayList<>();

	@PostConstruct
	public void init()
	{
		filtrar();
	}

	public void filtrar()
	{
		this.emails = emailService.buscar(filtro);
	}

	public void filtrarInit()
	{
		filtro.limpar();
		filtrar();
	}

	public void remover(Email email)
	{
		emailService.remover(email);
		filtrar();
	}

	public StatusEmail[] getStatusValues()
	{
		return StatusEmail.values();
	}
}
