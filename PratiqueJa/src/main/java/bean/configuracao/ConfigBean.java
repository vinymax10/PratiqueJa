package bean.configuracao;

import java.io.Serializable;

import bean.util.Mensagem;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;

import dao.configuracao.ConfigDAO;
import modelo.configuracao.Config;

@Data
@Named
@ViewScoped
public class ConfigBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Config config;

	@Inject
	private ConfigDAO configDAO;

	public void salvar()
	{
		configDAO.salvar(config);
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Config salva com sucesso.");
	}

	@PostConstruct
	public void init()
	{
		config = configDAO.buscar();

		if(config==null)
		{
			// Endereço-raiz em branco; os demais caminhos derivam dele (latex/, images/, pdf/,
			// ebook/, avaliacoes/, tex-new/, anexos-email/) — preencher e salvar aqui.
			config=new Config();
			configDAO.salvar(config);
		}
	}

}
