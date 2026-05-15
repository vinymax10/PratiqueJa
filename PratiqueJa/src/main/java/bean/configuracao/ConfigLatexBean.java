package bean.configuracao;

import java.io.Serializable;

import bean.util.Mensagem;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import dao.configuracao.ConfigLatexDAO;
import modelo.configuracao.ConfigLatex;

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
