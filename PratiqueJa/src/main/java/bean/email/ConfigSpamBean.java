package bean.email;

import java.io.Serializable;

import bean.util.Mensagem;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import dao.email.ConfigSpamDAO;
import modelo.email.ConfigSpam;

@Named
@ViewScoped
public class ConfigSpamBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private ConfigSpam configSpam;

	@Inject
	private ConfigSpamDAO configSpamDAO;

	public void salvar()
	{
		configSpamDAO.salvar(configSpam);
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "ConfigSpam salva com sucesso.");
	}

	@PostConstruct
	public void init()
	{
		configSpam = configSpamDAO.buscar();

		if(configSpam==null)
		{
			configSpam=new ConfigSpam();
			configSpamDAO.salvar(configSpam);
		}
	}

	public ConfigSpam getConfigSpam() {
		return configSpam;
	}

	public void setConfigSpam(ConfigSpam configSpam) {
		this.configSpam = configSpam;
	}

}
