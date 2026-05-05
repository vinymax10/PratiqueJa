package Bean.Questao.Configuracao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import DAO.Questao.Configuracao.OrgaoDAO;
import Exceptions.ContensException;
import Exceptions.RelacaoException;
import Infra.Mensagem;
import Modelo.Questao.Configuracao.Orgao;

@Named
@ViewScoped
public class OrgaoBean implements Serializable
{

	private static final long serialVersionUID = 1L;

	private String nome = "Órgão";

	private Orgao entidade;

	@Inject
	private Orgao entidadeNova;

	@Inject
	private OrgaoDAO entidadeDAO;

	private List<Orgao> lista = new ArrayList<Orgao>();
	private List<Orgao> listaBusca = new ArrayList<Orgao>();

	private boolean cadastro = true;

	private String nomeFiltro;

	private String siglaFiltro;

	public String adicionar()
	{
		try
		{
			podeAdicionar();
			entidadeDAO.salvar(entidadeNova);
			lista.add(entidadeNova);
			this.entidadeNova = new Orgao();
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " adicionado com sucesso.");
		}
		catch (ContensException e)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível adicionar o " + nome);
		}

		return "";
	}

	private void podeAdicionar() throws ContensException
	{
		if (entidadeDAO.contains(entidadeNova))
			throw new ContensException(
			"Não foi possível adicionar o " + nome + ". Já existe um " + nome + " com o mesmo nome.");
	}

	public String salvar()
	{
		try
		{
			podeSalvar();
			entidade=entidadeDAO.salvar(entidade);
			this.entidade = null;
			cadastro = true;
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " salvo com sucesso.");
		}
		catch (ContensException e)
		{
			entidade = entidadeDAO.refresh(entidade);
			updateListas(entidade);
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível salvar o " + nome);
		}

		return "";
	}

	private void updateListas(Orgao entidade)
	{
		int index = lista.indexOf(entidade);
		lista.remove(index);
		lista.add(index, entidade);

		if (listaBusca.contains(entidade))
		{
			index = listaBusca.indexOf(entidade);
			listaBusca.remove(index);
			listaBusca.add(index, entidade);
		}
	}

	private void podeSalvar() throws ContensException
	{
		if (entidadeDAO.contains(entidade))
			throw new ContensException(
			"Não foi possível salvar o " + nome + ". Já existe um " + nome + " com o mesmo nome.");
	}

	public String remover(Orgao entidade)
	{
		try
		{
			podeRemover(entidade);
			lista.remove(entidade);
			listaBusca.remove(entidade);
			entidadeDAO.remover(entidade);
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

	private void podeRemover(Orgao entidade) throws RelacaoException
	{
		if (entidade.getQuestoes().size() > 0)
			throw new RelacaoException("Não foi possível remover o " + nome + ". Existem questões relacionadas.");
	}

	public void filtrar()
	{
		this.listaBusca = entidadeDAO.filtrar(nomeFiltro, siglaFiltro);
	}

	public void procurarConcomitancia()
	{
		List<Orgao> listaCompleta = entidadeDAO.listaTudoOpcao();
		List<Orgao> listaParcial;
		this.lista = new ArrayList<Orgao>();

		for (Orgao orgao : listaCompleta)
		{
			listaParcial = entidadeDAO.procurarParecido(orgao);
			if (listaParcial.size() > 0)
			{
				lista.add(orgao);
				for (Orgao orgaoConcomitante : listaParcial)
					lista.add(orgaoConcomitante);
			}
		}
	}

	public String editar(Orgao entidade)
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

	@PostConstruct
	public void init()
	{
		this.lista = entidadeDAO.listaTudoOpcao();
		this.listaBusca = entidadeDAO.listaTudoOpcao();
	}

	public Orgao getEntidade()
	{
		return entidade;
	}

	public void setEntidade(Orgao entidade)
	{
		this.entidade = entidade;
	}

	public List<Orgao> getLista()
	{
		return lista;
	}

	public void setLista(List<Orgao> lista)
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

	public Orgao getEntidadeNova()
	{
		return entidadeNova;
	}

	public void setEntidadeNova(Orgao entidadeNova)
	{
		this.entidadeNova = entidadeNova;
	}

	public String getNomeFiltro()
	{
		return nomeFiltro;
	}

	public void setNomeFiltro(String nomeFiltro)
	{
		this.nomeFiltro = nomeFiltro;
	}

	public String getSiglaFiltro()
	{
		return siglaFiltro;
	}

	public void setSiglaFiltro(String siglaFiltro)
	{
		this.siglaFiltro = siglaFiltro;
	}

	public OrgaoDAO getEntidadeDAO()
	{
		return entidadeDAO;
	}

	public void setEntidadeDAO(OrgaoDAO entidadeDAO)
	{
		this.entidadeDAO = entidadeDAO;
	}

	public List<Orgao> getListaBusca()
	{
		return listaBusca;
	}

	public void setListaBusca(List<Orgao> listaBusca)
	{
		this.listaBusca = listaBusca;
	}

}
