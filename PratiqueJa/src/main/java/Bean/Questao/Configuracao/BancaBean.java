package Bean.Questao.Configuracao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import DAO.Questao.Configuracao.BancaDAO;
import Exceptions.ContensException;
import Exceptions.RelacaoException;
import Infra.ListAux;
import Infra.Mensagem;
import Modelo.Questao.Configuracao.Banca;

@Named
@ViewScoped
public class BancaBean implements Serializable
{

	private static final long serialVersionUID = 1L;

	private String nome = "Banca";

	private Banca entidade;

	@Inject
	private Banca entidadeNova;

	@Inject
	private BancaDAO entidadeDAO;

	private List<Banca> lista = new ArrayList<Banca>();

	private List<Banca> listaBusca = new ArrayList<Banca>();

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
			this.entidadeNova = new Banca();
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " adicionada com sucesso.");
		}
		catch (ContensException e)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível adicionar a " + nome);
		}

		return "";
	}

	private void podeAdicionar() throws ContensException
	{
		if (lista.contains(entidadeNova))
			throw new ContensException(
			"Não foi possível adicionar a " + nome + ". Já existe uma " + nome + " com o mesmo nome.");
	}

	public String salvar()
	{
		try
		{
			podeSalvar();
			entidade=entidadeDAO.salvar(entidade);
			this.entidade = null;
			cadastro = true;
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " salva com sucesso.");
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
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível salvar a " + nome);
		}

		return "";
	}

	private void updateListas(Banca entidade)
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
		if (ListAux.containsNumOcorrencias(entidade, lista) > 1)
			throw new ContensException(
			"Não foi possível salvar a " + nome + ". Já existe uma " + nome + " com o mesmo nome.");
	}

	public String remover(Banca entidade)
	{
		try
		{
			podeRemover(entidade);
			lista.remove(entidade);
			listaBusca.remove(entidade);
			entidadeDAO.remover(entidade);
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

	private void podeRemover(Banca entidade) throws RelacaoException
	{
		if (entidade.getQuestoes().size() > 0)
			throw new RelacaoException("Não foi possível remover a " + nome + ". Existem questões relacionadas.");
	}

	public String editar(Banca entidade)
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

	public void filtrar()
	{
		this.listaBusca = entidadeDAO.filtrar(nomeFiltro, siglaFiltro);
	}

	public void procurarConcomitancia()
	{
		List<Banca> listaCompleta = entidadeDAO.listaTudoOpcao();
		List<Banca> listaParcial;
		this.lista = new ArrayList<Banca>();

		for (Banca banca : listaCompleta)
		{
			listaParcial = entidadeDAO.procurarParecido(banca);
			if (listaParcial.size() > 0)
			{
				lista.add(banca);
				for (Banca bancaConcomitante : listaParcial)
					lista.add(bancaConcomitante);
			}
		}
	}

	@PostConstruct
	public void init()
	{
		this.lista = entidadeDAO.listaTudoOpcao();
		this.listaBusca = entidadeDAO.listaTudoOpcao();
	}

	public Banca getEntidade()
	{
		return entidade;
	}

	public void setEntidade(Banca entidade)
	{
		this.entidade = entidade;
	}

	public List<Banca> getLista()
	{
		return lista;
	}

	public void setLista(List<Banca> lista)
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

	public Banca getEntidadeNova()
	{
		return entidadeNova;
	}

	public void setEntidadeNova(Banca entidadeNova)
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

	public BancaDAO getEntidadeDAO()
	{
		return entidadeDAO;
	}

	public void setEntidadeDAO(BancaDAO entidadeDAO)
	{
		this.entidadeDAO = entidadeDAO;
	}

	public List<Banca> getListaBusca()
	{
		return listaBusca;
	}

	public void setListaBusca(List<Banca> listaBusca)
	{
		this.listaBusca = listaBusca;
	}

}
