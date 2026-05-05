package Bean.Download;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import DAO.Configuracao.ConfigLatexDAO;
import Infra.Mensagem;
import Modelo.Configuracao.ConfigLatex;

@Named
@ViewScoped
public class ConfigLatexBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private ConfigLatex configLatex;
	
	@Inject
	private ConfigLatexDAO configLatexDAO;
	
	public void salvar()
	{
		configLatexDAO.salvar(configLatex);
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "ConfigLatex salva com sucesso.");
	}
	
	@PostConstruct
	public void init()
	{
		configLatex = configLatexDAO.buscar();
		
		if(configLatex==null)
		{
			configLatex=new ConfigLatex();
			configLatex.setEndereco("C:\\Users\\maximovrm\\Documents\\latex");
			configLatex.setNome("exercicio");
			configLatexDAO.salvar(configLatex);
		}
	}

	public ConfigLatex getConfigLatex() {
		return configLatex;
	}

	public void setConfigLatex(ConfigLatex configLatex) {
		this.configLatex = configLatex;
	}

}