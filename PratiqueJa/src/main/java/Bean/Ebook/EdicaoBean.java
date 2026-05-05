package Bean.Ebook;

import java.io.Serializable;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import DAO.Configuracao.EdicaoDAO;
import Infra.Mensagem;
import Modelo.AssuntoCurso.Enum.Modulo;
import Modelo.Configuracao.Edicao;

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

	public Edicao getEdicao() {
		return edicao;
	}

	public void setEdicao(Edicao edicao) {
		this.edicao = edicao;
	}

}