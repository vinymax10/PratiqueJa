package Bean.Spam;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import DAO.Spam.ConfigSpamDAO;
import Infra.Mensagem;
import Modelo.Spam.ConfigSpam;

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