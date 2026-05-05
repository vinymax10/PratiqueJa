package Bean.Teste;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import DAO.Teste.ConteudoTesteDAO;
import Infra.Mensagem;
import Modelo.Teste.ConteudoTeste;
import Modelo.Teste.TestePadrao;

@Named
@ViewScoped
public class ConteudoTesteBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	String nome = "Conteúdo do Teste";

	private ConteudoTeste conteudoTeste;

	private boolean lista = true;
	private boolean cadastro = false;

	@Inject
	private TestePadraoBean testePadraoBean;

	@Inject
	private ConteudoTesteDAO conteudoTesteDAO;

	public String cadastrar()
	{
		cadastro = true;
		lista = false;
		conteudoTeste = new ConteudoTeste();
		return "";
	}

	public String adicionar()
	{
		try
		{
			TestePadrao testePadrao = testePadraoBean.getTestePadrao();
			conteudoTeste.setTestePadrao(testePadrao);
			testePadrao.getConteudosTeste().add(conteudoTeste);
			conteudoTesteDAO.salvar(conteudoTeste);
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
			conteudoTeste=conteudoTesteDAO.salvar(conteudoTeste);
			lista = true;
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " salvo com sucesso.");
		}
		catch(Exception e)
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
			TestePadrao testePadrao = testePadraoBean.getTestePadrao();
			testePadrao.getConteudosTeste().remove(conteudoTeste);
			conteudoTesteDAO.remover(conteudoTeste);
			lista = true;
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " removido com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível remover o " + nome);
		}
		return "";
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

	public ConteudoTeste getConteudoTeste()
	{
		return conteudoTeste;
	}

	public void setConteudoTeste(ConteudoTeste conteudoTeste)
	{
		this.conteudoTeste = conteudoTeste;
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

}