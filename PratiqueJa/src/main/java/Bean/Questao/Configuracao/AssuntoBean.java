package Bean.Questao.Configuracao;

import java.io.Serializable;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import DAO.Questao.Configuracao.AssuntoDAO;
import Exceptions.RelacaoException;
import Infra.Mensagem;
import Modelo.Questao.Configuracao.Assunto;
import Modelo.Questao.Configuracao.Disciplina;

@Named
@ViewScoped
public class AssuntoBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	String nome = "Assunto";

	private Assunto assunto;

	private boolean lista = true;
	private boolean cadastro = false;

	@Inject
	private DisciplinaBean disciplinaBean;

	@Inject
	private AssuntoDAO assuntoDAO;

	public String cadastrar()
	{
		cadastro = true;
		lista = false;
		assunto = new Assunto();
		return "";
	}

	public String adicionar()
	{
		try
		{
			Disciplina disciplina = disciplinaBean.getDisciplina();
			assunto.setDisciplina(disciplina);
			disciplina.getAssuntos().add(assunto);
			assuntoDAO.salvar(assunto);
			lista = true;
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " adicionado com sucesso.");
		}
		catch (Exception e)
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
			assunto=assuntoDAO.salvar(assunto);
			lista = true;
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " salvo com sucesso.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível salvar o " + nome);
		}
		return "";
	}

	public String remover()
	{
		try
		{
			podeRemover();
			Disciplina disciplina = disciplinaBean.getDisciplina();
			disciplina.getAssuntos().remove(assunto);
			assuntoDAO.remover(assunto);
			lista = true;
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " removido com sucesso.");
		}
		catch (RelacaoException e)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível remover o " + nome);
		}
		return "";
	}

	private void podeRemover() throws RelacaoException
	{
		if (assunto.getQuestoes().size() > 0)
			throw new RelacaoException("Não foi possível remover o " + nome + ". Existem questões relacionadas.");
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

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public Assunto getAssunto()
	{
		return assunto;
	}

	public void setAssunto(Assunto assunto)
	{
		this.assunto = assunto;
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

	public AssuntoDAO getAssuntoDAO()
	{
		return assuntoDAO;
	}

	public void setAssuntoDAO(AssuntoDAO assuntoDAO)
	{
		this.assuntoDAO = assuntoDAO;
	}

}