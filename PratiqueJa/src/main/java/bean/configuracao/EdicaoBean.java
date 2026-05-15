package bean.configuracao;

import java.io.Serializable;

import bean.util.Mensagem;
import dao.configuracao.EdicaoDAO;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import modelo.academico.Modulo;
import modelo.configuracao.Edicao;

@Data
@Named
@ViewScoped
public class EdicaoBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Edicao edicao;

	@Inject
	private EdicaoDAO edicaoDAO;

	public void salvar()
	{
		edicaoDAO.salvar(edicao);
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Edicao salvo com sucesso.");
	}

	public void incrementa(Modulo modulo)
	{
		edicao.incrementa(modulo);
		edicaoDAO.salvar(edicao);
	}

	@PostConstruct
	public void init()
	{
		edicao = edicaoDAO.buscar();

		if(edicao==null)
		{
			edicao=new Edicao();
			edicaoDAO.salvar(edicao);
		}
	}

}
