package Bean.Exercicio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import Bean.Exercicio.Filtro.FiltroExercicioPadrao;
import DAO.Exercicio.ExercicioPadraoDAO;
import Infra.Mensagem;
import Modelo.Exercicio.ExercicioPadrao;

@Named
@ViewScoped
public class ExercicioPadraoBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private ExercicioPadraoDAO exercicioPadraoDAO;

	private List<ExercicioPadrao> exerciciosPadrao = new ArrayList<ExercicioPadrao>();

	private ExercicioPadrao exercicioPadrao;
	
	private String nome = "Exercício Padrão";
	private boolean lista = true;
	private boolean cadastro = false;

	@Inject
	private FiltroExercicioPadrao filtroExercicioPadrao;

	public String cadastrar()
	{
		cadastro = true;
		lista = false;
		exercicioPadrao = new ExercicioPadrao();
		return "";
	}

	public String adicionar()
	{
		try
		{
			exercicioPadraoDAO.salvar(exercicioPadrao);
			exerciciosPadrao.add(exercicioPadrao);
			lista = true;
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " adicionado com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível adicionar o " + nome);
		}
		return "";
	}

	public String salvar()
	{
		try
		{
			exercicioPadrao=exercicioPadraoDAO.salvar(exercicioPadrao);
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " salva com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível salvar a " + nome);
		}
		lista = true;
		return "";
	}

	public String remover()
	{
		try
		{
			exercicioPadraoDAO.remover(exercicioPadrao);
			exerciciosPadrao.remove(exercicioPadrao);
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " removido com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível remover o " + nome);
		}
		lista = true;
		return "";
	}

	public void onSelected()
	{
		cadastro = false;
		lista = false;
	}

	public void filtrar()
	{
		this.exerciciosPadrao = exercicioPadraoDAO.buscar(filtroExercicioPadrao);
	}

	public List<ExercicioPadrao> getTodosExerciciosPadrao()
	{
		return exercicioPadraoDAO.listaTudo();
	}

	public ExercicioPadrao getExercicioPadrao()
	{
		return exercicioPadrao;
	}

	public void setExercicioPadrao(ExercicioPadrao exercicio)
	{
		this.exercicioPadrao = exercicio;
	}
	
	public List<ExercicioPadrao> getExerciciosPadrao()
	{
		return exerciciosPadrao;
	}

	public void setExerciciosPadrao(List<ExercicioPadrao> exerciciosPadrao)
	{
		this.exerciciosPadrao = exerciciosPadrao;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
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

	public FiltroExercicioPadrao getFiltroExercicioPadrao()
	{
		return filtroExercicioPadrao;
	}

	public void setFiltroExercicioPadrao(FiltroExercicioPadrao filtroExercicioPadrao)
	{
		this.filtroExercicioPadrao = filtroExercicioPadrao;
	}

}