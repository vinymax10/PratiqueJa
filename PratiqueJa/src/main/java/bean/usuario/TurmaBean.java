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
import infra.Mensagem;
import modelo.usuario.Turma;
import bean.usuario.filtro.FiltroTurma;

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
			turma=turmaDAO.salvar(turma);
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
		catch (RelacaoException e)
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
		if (turma.getAlunos().size() > 0)
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

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public Turma getTurma()
	{
		return turma;
	}

	public void setTurma(Turma turma)
	{
		this.turma = turma;
	}

	public boolean isLista()
	{
		return lista;
	}

	public void setLista(boolean lista)
	{
		this.lista = lista;
	}

	public boolean isCadastro()
	{
		return cadastro;
	}

	public void setCadastro(boolean cadastro)
	{
		this.cadastro = cadastro;
	}

	public List<Turma> getTurmas()
	{
		return turmas;
	}

	public void setTurmas(List<Turma> turmas)
	{
		this.turmas = turmas;
	}

	public FiltroTurma getFiltroTurma()
	{
		return filtroTurma;
	}

	public void setFiltroTurma(FiltroTurma filtroTurma)
	{
		this.filtroTurma = filtroTurma;
	}

	public int getActiveIndex()
	{
		return activeIndex;
	}

	public void setActiveIndex(int activeIndex)
	{
		this.activeIndex = activeIndex;
	}

	public List<Turma> getTodasTurmas() {
		return todasTurmas;
	}

	public void setTodasTurmas(List<Turma> todasTurmas) {
		this.todasTurmas = todasTurmas;
	}

}