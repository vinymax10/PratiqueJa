package bean.instagram;

import java.util.Random;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import modelo.publicacao.Cta;
import modelo.publicacao.FinalidadeCta;

import org.primefaces.event.CellEditEvent;

import bean.ConfigBean;
import bean.util.Mensagem;
import dao.instagram.CtaDAO;
import exceptions.RelacaoException;

@Named
@ViewScoped
public class CtaBean extends ConfigBean<Cta, CtaDAO>
{
	private static final long serialVersionUID = 1L;

	private String ctas;
	private FinalidadeCta finalidadeCta;

	@Inject
	private ConfigPostBean configPostBean;

	public CtaBean()
	{
		super(Cta.class, "CTA");
	}

	@PostConstruct
	@Override
	public void init()
	{
		this.opcoes = entidadeDAO.listarGenericas();
		this.opcoesAtivas = entidadeDAO.listarOpcoesAtivas();
		cadastrar();
	}

	@Override
	public String adicionar()
	{
		String result = super.adicionar();
		cadastrar();
		return result;
	}

	@Override
	public String salvar()
	{
		String result = super.salvar();
		cadastrar();
		return result;
	}

	@Override
	public void cancelar()
	{
		super.cancelar();
		cadastrar();
	}

	@Override
	protected void podeRemover(Cta entidade) throws RelacaoException {}

	public String adicionarCTAs(boolean personal)
	{
		try
		{
			Cta cta;
			String[] list = ctas.split("\\n");
			for (String ctaString : list)
			{
				cta = new Cta();
				cta.setNome(ctaString);
				cta.setFinalidadeCta(finalidadeCta);
				if (personal)
				{
					cta.setConfigPost(configPostBean.getConfigPost());
					entidadeDAO.salvar(cta);
					configPostBean.getConfigPost().getCtas().add(cta);
				}
				else
				{
					entidadeDAO.salvar(cta);
					opcoes.add(cta);
				}
			}
			ctas = "";
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
		Cta entidade = opcoes.get(index);
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
		if (personal)
		{
			while (configPostBean.getConfigPost().getCtas().size() > 0)
			{
				cta = configPostBean.getConfigPost().getCtas().get(0);
				entidadeDAO.remover(cta);
				configPostBean.getConfigPost().getCtas().remove(0);
			}
		}
		else
		{
			while (opcoes.size() > 0)
			{
				cta = opcoes.get(0);
				entidadeDAO.remover(cta);
				opcoes.remove(0);
			}
		}
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "CTAs removidos com sucesso.");
	}

	public String getAnyCta()
	{
		Random rand = new Random();
		return opcoes.get(rand.nextInt(opcoes.size())).getNome();
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
