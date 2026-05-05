package Bean.Instagram;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import DAO.Instagram.HashtagDAO;
import Exceptions.RelacaoException;
import Infra.Mensagem;
import Modelo.Instagram.Hashtag;

@Named
@SessionScoped
public class HashtagBean implements Serializable
{

	private static final long serialVersionUID = 1L;

	private String nome = "Hashtag";

	private Hashtag entidade;

	@Inject
	private Hashtag entidadeNova;

	@Inject
	private HashtagDAO entidadeDAO;

	private List<Hashtag> lista = new ArrayList<Hashtag>();

	private boolean cadastro = true;

	public String adicionar()
	{
		try
		{
			entidadeDAO.salvar(entidadeNova);
			entidadeNova = new Hashtag();
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

	public String remover(Hashtag entidade)
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

	private void podeRemover(Hashtag entidade) throws RelacaoException
	{
	}

	public String editar(Hashtag entidade)
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
		nome = nome.toLowerCase();
		Hashtag entidade = new Hashtag();
		entidade.setNome(nome);
		if ((cadastro && lista.contains(entidade))
		|| (!cadastro && lista.contains(entidade) && !this.entidade.equals(entidade)))
		{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nome já existente.","" );
			throw new ValidatorException(msg);
		}
	}
	
	public String getSomeHashtag(int number)
	{
		Random rand=new Random();
		String hashtags="";
		for(int i = 0; i < number; i++)
		{
			hashtags+=lista.get(rand.nextInt(lista.size())).getNome()+" ";
		}
		return hashtags;
	}

	@PostConstruct
	public void init()
	{
		this.lista = entidadeDAO.listaTudoOpcao();
	}

	public Hashtag getEntidade()
	{
		return entidade;
	}

	public void setEntidade(Hashtag entidade)
	{
		this.entidade = entidade;
	}

	public List<Hashtag> getLista()
	{
		return lista;
	}

	public void setLista(List<Hashtag> lista)
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

	public Hashtag getEntidadeNova()
	{
		return entidadeNova;
	}

	public void setEntidadeNova(Hashtag entidadeNova)
	{
		this.entidadeNova = entidadeNova;
	}

	public HashtagDAO getEntidadeDAO()
	{
		return entidadeDAO;
	}

	public void setEntidadeDAO(HashtagDAO entidadeDAO)
	{
		this.entidadeDAO = entidadeDAO;
	}

}
