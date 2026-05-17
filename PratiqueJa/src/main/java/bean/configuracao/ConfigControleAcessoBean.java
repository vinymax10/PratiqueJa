package bean.configuracao;

import java.io.Serializable;

import bean.util.Mensagem;
import dao.configuracao.ConfigControleAcessoDAO;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import modelo.configuracao.ConfigControleAcesso;

@Data
@Named
@SessionScoped
public class ConfigControleAcessoBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private ConfigControleAcesso configControleAcesso;

	@Inject
	private ConfigControleAcessoDAO configControleAcessoDAO;

	public void salvar()
	{
		configControleAcessoDAO.salvar(configControleAcesso);
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Config Controle de Acesso salvo com sucesso.");
	}

	@PostConstruct
	public void init()
	{
		configControleAcesso = configControleAcessoDAO.buscar();

		if(configControleAcesso == null)
		{
			configControleAcesso = new ConfigControleAcesso();
			configControleAcesso.setLimitePaginasBaixadas(100);
			configControleAcesso.setLimiteResolucaoExercicio(50);
			configControleAcesso.setLimiteResolucaoQuestao(50);
			configControleAcesso.setLimiteQuestaoFeita(50);
			configControleAcessoDAO.salvar(configControleAcesso);
		}
	}
}
