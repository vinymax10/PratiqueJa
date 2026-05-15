package bean.configuracao;

import java.io.Serializable;

import bean.util.Mensagem;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;

import dao.configuracao.ConfigCleanupDAO;
import modelo.configuracao.ConfigCleanup;

@Data
@Named
@SessionScoped
public class ConfigCleanupBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private ConfigCleanup configCleanup;
	
	@Inject
	private ConfigCleanupDAO configCleanupDAO;
	
	public void salvar()
	{
		configCleanupDAO.salvar(configCleanup);
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "ConfigCleanup salvo com sucesso.");
	}
	
	@PostConstruct
	public void init()
	{
		configCleanup = configCleanupDAO.buscar();
		
		if(configCleanup==null)
		{
			configCleanup=new ConfigCleanup();
			configCleanup.setDiasRemoverExercicioRealizado(7);
			configCleanup.setDiasRemoverExercicioNaoRealizado(7);
			configCleanup.setDiasRemoverTesteRealizado(7);
			configCleanup.setDiasRemoverTesteNaoRealizado(7);
			
			configCleanupDAO.salvar(configCleanup);
		}
	}

}