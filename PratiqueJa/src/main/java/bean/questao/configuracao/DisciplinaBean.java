package bean.questao.configuracao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bean.util.Mensagem;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import dao.questao.configuracao.DisciplinaDAO;
import exceptions.RelacaoException;
import modelo.questao.configuracao.Disciplina;

@Named
@ViewScoped
public class DisciplinaBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	String nome = "Disciplina";
	@Inject
	private DisciplinaDAO disciplinaDAO;

	private List<Disciplina> disciplinas = new ArrayList<Disciplina>();

	private Disciplina disciplina;

	private boolean lista = true;
	private boolean cadastro = false;

	public String cadastrar()
	{
		cadastro = true;
		lista = false;
		disciplina = new Disciplina();
		return "";
	}

	public String adicionar()
	{
		try
		{
			disciplinaDAO.salvar(disciplina);
			disciplinas.add(disciplina);
			lista = true;
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " adicionada com sucesso.");
		}
		catch (Exception e)
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
			disciplina=disciplinaDAO.salvar(disciplina);
			lista = true;
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " salva com sucesso.");
		}
		catch (Exception e)
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
			disciplinaDAO.remover(disciplina);
			disciplinas.remove(disciplina);
			lista = true;
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " removida com sucesso.");
		}
		catch (RelacaoException e)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível remover a " + nome);
		}
		return "";
	}

	private void podeRemover() throws RelacaoException
	{
		if (disciplina.getQuestoes().size() > 0)
			throw new RelacaoException("Não foi possível remover a " + nome + ". Existem questões relacionadas.");
	}

	public String cancelar()
	{
		lista = true;
		return "";
	}

	public void onSelected()
	{
		cadastro = false;
		lista = false;
	}

	@PostConstruct
	public void init()
	{
		this.disciplinas = disciplinaDAO.listarTudo();
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public List<Disciplina> getDisciplinas()
	{
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas)
	{
		this.disciplinas = disciplinas;
	}

	public Disciplina getDisciplina()
	{
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina)
	{
		this.disciplina = disciplina;
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

	public DisciplinaDAO getDisciplinaDAO()
	{
		return disciplinaDAO;
	}

	public void setDisciplinaDAO(DisciplinaDAO disciplinaDAO)
	{
		this.disciplinaDAO = disciplinaDAO;
	}

}