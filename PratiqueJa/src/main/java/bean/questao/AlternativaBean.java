package bean.questao;

import java.io.Serializable;

import bean.util.Mensagem;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import dao.questao.AlternativaDAO;
import modelo.questao.Alternativa;
import modelo.questao.Questao;

@Named
@ViewScoped
public class AlternativaBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	String nome = "Alternativa";

	private Alternativa alternativa;

	private boolean lista = true;
	private boolean cadastro = false;

	@Inject
	private GestaoQuestaoBean gestaoQuestaoBean;

	@Inject
	private AlternativaDAO alternativaDAO;

	public String cadastrar()
	{
		cadastro = true;
		lista = false;
		alternativa = new Alternativa();
		return "";
	}

	public String adicionar()
	{
		try
		{
			Questao questao = gestaoQuestaoBean.getQuestao();
			alternativa.setQuestao(questao);
			alternativa.setOrdem(questao.getAlternativas().size());
			questao.getAlternativas().add(alternativa);
			alternativaDAO.salvar(alternativa);
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
			alternativa=alternativaDAO.salvar(alternativa);
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
			Questao questao = gestaoQuestaoBean.getQuestao();
			questao.getAlternativas().remove(alternativa);
			alternativaDAO.remover(alternativa);
			lista = true;
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " removida com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível remover a " + nome);
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

	public Alternativa getAlternativa()
	{
		return alternativa;
	}

	public void setAlternativa(Alternativa alternativa)
	{
		this.alternativa = alternativa;
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