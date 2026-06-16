package bean.avaliacao;

import java.io.Serializable;

import bean.util.Mensagem;
import dao.avaliacao.ConfigPedidoAvaliacaoDAO;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import modelo.avaliacao.ConfigPedidoAvaliacao;

@Data
@Named
@ViewScoped
public class ConfigPedidoAvaliacaoBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private ConfigPedidoAvaliacao config;

	@Inject
	private ConfigPedidoAvaliacaoDAO configDAO;

	@PostConstruct
	public void init()
	{
		config = configDAO.buscarOuCriarConfig();
	}

	public void salvar()
	{
		configDAO.salvar(config);
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Configuração salva com sucesso.");
	}
}
