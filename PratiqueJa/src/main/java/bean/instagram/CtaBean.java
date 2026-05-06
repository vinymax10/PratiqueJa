package bean.instagram;

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

import org.primefaces.event.CellEditEvent;

import dao.instagram.CtaDAO;
import exceptions.RelacaoException;
import infra.Mensagem;
import modelo.instagram.Cta;
import modelo.instagram.FinalidadeCta;

@Named
@ViewScoped
public class CtaBean implements Serializable
{

	private static final long serialVersionUID = 1L;

	private String nome = "CTA";

	private Cta entidade;

	@Inject
	private Cta entidadeNova;

	@Inject
	private CtaDAO entidadeDAO;

	private List<Cta> lista = new ArrayList<Cta>();

	private boolean cadastro = true;
	
	private String ctas;
	
	private FinalidadeCta finalidadeCta;
	
	@Inject
	private ConfigPostBean configPostBean;

	public String adicionarCTAs(boolean personal)
	{
		try
		{
			Cta cta;
			String list[]=ctas.split("\\n");
			for(String ctaString : list)
			{
				System.out.println(ctaString);
				cta=new Cta();
				cta.setNome(ctaString);
				cta.setFinalidadeCta(finalidadeCta);
				if(personal)
				{
					cta.setConfigPost(configPostBean.getConfigPost());
					entidadeDAO.salvar(cta);
					configPostBean.getConfigPost().getCtas().add(cta);
				}
				else
				{
					entidadeDAO.salvar(cta);
					lista.add(cta);
				}
				
			}
			ctas="";
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "CTAs adicionados com sucesso.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível adicionar o " + nome);
		}
		return "";
	}

	public void onCellEdit(CellEditEvent<Cta> event) 
	{
		int index = event.getRowIndex();
		Cta entidade = configPostBean.getConfigPost().getCtas().get(index);
		entidadeDAO.salvar(entidade);

		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " salvo(a) com sucesso.");
	}
	
	public void onCellEditLista(CellEditEvent<Cta> event) 
	{
		int index = event.getRowIndex();
		Cta entidade = lista.get(index);
		entidadeDAO.salvar(entidade);

		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " salvo(a) com sucesso.");
	}
	
	public String removerPost(Cta entidade)
	{
		try
		{
			configPostBean.getConfigPost().getCtas().remove(entidade);
			entidadeDAO.remover(entidade);
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " removido com sucesso.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível remover o " + nome);
		}
		return "";
	}
	
	public void removerTodosCTAs(boolean personal)
	{
		Cta cta;
		if(personal)
		{
			while(configPostBean.getConfigPost().getCtas().size()>0)
			{
				cta=configPostBean.getConfigPost().getCtas().get(0);
				entidadeDAO.remover(cta);
				configPostBean.getConfigPost().getCtas().remove(0);
			}
		}
		else
		{
			while(lista.size()>0)
			{
				cta=lista.get(0);
				entidadeDAO.remover(cta);
				lista.remove(0);
			}
		}
		
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "CTAs removidos com sucesso.");
	}
	
	public String adicionar()
	{
		try
		{
			entidadeDAO.salvar(entidadeNova);
			entidadeNova = new Cta();
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

	public String remover(Cta entidade)
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

	private void podeRemover(Cta entidade) throws RelacaoException
	{
	}

	public String editar(Cta entidade)
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
		Cta entidade = new Cta();
		entidade.setNome(nome);
		if ((cadastro && lista.contains(entidade))
		|| (!cadastro && lista.contains(entidade) && !this.entidade.equals(entidade)))
		{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nome já existente.","" );
			throw new ValidatorException(msg);
		}
	}

	public String getAnyCta()
	{
		Random rand=new Random();
		return lista.get(rand.nextInt(lista.size())).getNome();
	}
	
	@PostConstruct
	public void init()
	{
		this.lista = entidadeDAO.listarGenericas();
	}

	public Cta getEntidade()
	{
		return entidade;
	}

	public void setEntidade(Cta entidade)
	{
		this.entidade = entidade;
	}

	public List<Cta> getLista()
	{
		return lista;
	}

	public void setLista(List<Cta> lista)
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

	public Cta getEntidadeNova()
	{
		return entidadeNova;
	}

	public void setEntidadeNova(Cta entidadeNova)
	{
		this.entidadeNova = entidadeNova;
	}

	public CtaDAO getEntidadeDAO()
	{
		return entidadeDAO;
	}

	public void setEntidadeDAO(CtaDAO entidadeDAO)
	{
		this.entidadeDAO = entidadeDAO;
	}

	public String getCtas()
	{
		return ctas;
	}

	public void setCtas(String ctas)
	{
		this.ctas = ctas;
	}

	public FinalidadeCta getFinalidadeCta()
	{
		return finalidadeCta;
	}

	public void setFinalidadeCta(FinalidadeCta finalidadeCta)
	{
		this.finalidadeCta = finalidadeCta;
	}

}
