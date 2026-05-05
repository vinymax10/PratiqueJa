package Bean.Instagram;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import DAO.Instagram.ConviteGrupoDAO;
import Exceptions.RelacaoException;
import Infra.Mensagem;
import Modelo.Instagram.ConviteGrupo;

@Named
@ViewScoped
public class ConviteGrupoBean implements Serializable
{

	private static final long serialVersionUID = 1L;

	private String nome = "Convite ao Grupo";

	private ConviteGrupo entidade;

	@Inject
	private ConviteGrupo entidadeNova;

	@Inject
	private ConviteGrupoDAO entidadeDAO;

	private List<ConviteGrupo> lista = new ArrayList<ConviteGrupo>();

	private boolean cadastro = true;

	public String adicionar()
	{
		try
		{
			entidadeDAO.salvar(entidadeNova);
			entidadeNova = new ConviteGrupo();
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

	public String remover(ConviteGrupo entidade)
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

	private void podeRemover(ConviteGrupo entidade) throws RelacaoException
	{
	}

	public String editar(ConviteGrupo entidade)
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
		ConviteGrupo entidade = new ConviteGrupo();
		entidade.setNome(nome);
		if ((cadastro && lista.contains(entidade))
		|| (!cadastro && lista.contains(entidade) && !this.entidade.equals(entidade)))
		{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nome já existente.","" );
			throw new ValidatorException(msg);
		}
	}

	public String getAnyConviteGrupo()
	{
		Random rand=new Random();
		if(lista.size()>0)
			return lista.get(rand.nextInt(lista.size())).getNome();
		else
			return "";
	}
	
	@PostConstruct
	public void init()
	{
		this.lista = entidadeDAO.listaTudoOpcao();
	}

	public ConviteGrupo getEntidade()
	{
		return entidade;
	}

	public void setEntidade(ConviteGrupo entidade)
	{
		this.entidade = entidade;
	}

	public List<ConviteGrupo> getLista()
	{
		return lista;
	}

	public void setLista(List<ConviteGrupo> lista)
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

	public ConviteGrupo getEntidadeNova()
	{
		return entidadeNova;
	}

	public void setEntidadeNova(ConviteGrupo entidadeNova)
	{
		this.entidadeNova = entidadeNova;
	}

	public ConviteGrupoDAO getEntidadeDAO()
	{
		return entidadeDAO;
	}

	public void setEntidadeDAO(ConviteGrupoDAO entidadeDAO)
	{
		this.entidadeDAO = entidadeDAO;
	}

}
