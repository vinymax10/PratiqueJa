package bean.questao.configuracao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bean.util.Mensagem;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import dao.questao.configuracao.AnoDAO;
import exceptions.RelacaoException;
import modelo.questao.configuracao.Ano;

@Named
@ViewScoped
public class AnoBean implements Serializable
{

	private static final long serialVersionUID = 1L;

	private String nome = "Ano";

	private Ano entidade;

	@Inject
	private Ano entidadeNova;

	@Inject
	private AnoDAO entidadeDAO;

	private List<Ano> lista = new ArrayList<Ano>();

	private boolean cadastro = true;

	public String adicionar()
	{
		try
		{
			entidadeDAO.salvar(entidadeNova);
			entidadeNova = new Ano();
			init();
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
			entidade=entidadeDAO.salvar(entidade);
			this.entidade = null;
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " salvo com sucesso.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível salvar o " + nome);
		}
		cadastro = true;
		return "";
	}

	public String remover(Ano entidade)
	{
		try
		{
			podeRemover(entidade);
			entidadeDAO.remover(entidade);
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " removido com sucesso.");
			init();
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

	private void podeRemover(Ano entidade) throws RelacaoException
	{
		if (entidade.getQuestoes().size() > 0)
			throw new RelacaoException("Não foi possível remover o " + nome + ". Existem questões relacionadas.");
	}

	public String editar(Ano entidade)
	{
		this.entidade = entidade;
		cadastro = false;
		return "";
	}

	public String cancelar()
	{
		this.entidade = null;
		cadastro = true;
		return "";
	}

	public void validate(FacesContext context, UIComponent component, Object object)
	{
		String nome = (String) object;
		nome = nome.toUpperCase();
		Ano entidade = new Ano();
		entidade.setNome(nome);
		if ((cadastro && lista.contains(entidade))
		|| (!cadastro && lista.contains(entidade) && !this.entidade.equals(entidade)))
		{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Nome já existente.");
			throw new ValidatorException(msg);
		}
	}

	@PostConstruct
	public void init()
	{
		this.lista = entidadeDAO.listarTudo();
	}

	public Ano getEntidade()
	{
		return entidade;
	}

	public void setEntidade(Ano entidade)
	{
		this.entidade = entidade;
	}

	public List<Ano> getLista()
	{
		return lista;
	}

	public void setLista(List<Ano> lista)
	{
		this.lista = lista;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public boolean isCadastro()
	{
		return cadastro;
	}

	public void setCadastro(boolean cadastro)
	{
		this.cadastro = cadastro;
	}

	public Ano getEntidadeNova()
	{
		return entidadeNova;
	}

	public void setEntidadeNova(Ano entidadeNova)
	{
		this.entidadeNova = entidadeNova;
	}

	public AnoDAO getEntidadeDAO()
	{
		return entidadeDAO;
	}

	public void setEntidadeDAO(AnoDAO entidadeDAO)
	{
		this.entidadeDAO = entidadeDAO;
	}

}
