package bean.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import dao.usuario.TurmaDAO;
import exceptions.RelacaoException;
import lombok.Data;
import modelo.usuario.Turma;
import filtro.usuario.FiltroTurma;
import bean.util.Mensagem;

@Data
@Named
@ViewScoped
public class TurmaBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String nome = "Turma";

	@Inject
	private Turma turma;

	private boolean lista = true;
	private boolean cadastro = false;
	private int activeIndex;

	@Inject
	private TurmaDAO turmaDAO;

	private List<Turma> turmas = new ArrayList<Turma>();
	private List<Turma> todasTurmas = new ArrayList<Turma>();

	@Inject
	private FiltroTurma filtroTurma;

	public String cadastrar()
	{
		cadastro = true;
		lista = false;
		turma = new Turma();
		return "";
	}

	public String adicionar()
	{
		try
		{
			turmaDAO.salvar(turma);
			lista = true;
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " adicionada com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível adicionar a " + nome);
		}
		return "";
	}

	public String salvar()
	{
		try
		{
			turma = turmaDAO.salvar(turma);
			lista = true;
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " salva com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível salvar a " + nome);
		}
		return "";
	}

	public String remover()
	{
		try
		{
			podeRemover();
			turmaDAO.remover(turma);

			lista = true;
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " removida com sucesso.");
		}
		catch(RelacaoException e)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, e.getMessage());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível remover a " + nome);
		}
		return "";
	}

	private void podeRemover() throws RelacaoException
	{
		if(!turma.getAlunos().isEmpty())
			throw new RelacaoException("Não foi possível remover a " + nome + ". Existem alunos relacionados.");
	}

	public String cancelar()
	{
		lista = true;
		return "";
	}

	public void filtrar()
	{
		this.turmas = turmaDAO.buscar(filtroTurma);
	}

	@PostConstruct
	public void init()
	{
		todasTurmas = turmaDAO.todas();
	}

	public void onSelected()
	{
		cadastro = false;
		lista = false;
	}
}
