package bean.questao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.PrimeFaces;
import org.primefaces.event.ReorderEvent;
import org.primefaces.model.file.UploadedFile;

import bean.academico.AnoBean;
import bean.academico.BancaBean;
import bean.academico.DisciplinaBean;
import bean.academico.OrgaoBean;
import bean.exercicio.ConfigDownload;
import bean.util.Mensagem;
import dao.questao.QuestaoDAO;
import filtro.questao.FiltroQuestao;
import infra.Navegacao;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import modelo.questao.Alternativa;
import modelo.questao.Paragrafo;
import modelo.questao.Questao;
import service.questao.QuestaoImportService;
import web.session.Sessao;

@Data
@Named
@ViewScoped
public class GestaoQuestaoBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	String nome = "Questão";

	@Inject
	private QuestaoDAO questaoDAO;

	private List<Questao> questoes = new ArrayList<Questao>();

	private Questao questao;

	private boolean cadastro = false;

	private int activeIndex;

	private UploadedFile uploadedFile;

	@Inject
	private AnoBean anoBean;

	@Inject
	private BancaBean bancaBean;

	@Inject
	private OrgaoBean orgaoBean;

	@Inject
	private DisciplinaBean disciplinaBean;

	private int inicioCarregamento;

	private int fimCarregamento;

//  ---------Filtro---------

	@Inject
	private FiltroQuestao filtroQuestao;
	private long idQuestao;
	
	@Inject
	private ConfigDownload configDownload;
	
	@Inject
	private QuestaoBean questaoBean;

	@Inject
	private QuestaoImportService questaoImportService;

	public String cadastrar()
	{
		activeIndex = 0;
		cadastro = true;
		questao = new Questao();
		questaoDAO.salvar(questao);
		Navegacao.redirectPF("/gestaoQuestao/questao?questao="+questao.getId()+"&tab="+0,"_blank");
		return "";
	}

	public String adicionar()
	{
		try
		{
			questaoDAO.salvar(questao);
			questoes.add(questao);
			cadastro = false;
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
			questao=questaoDAO.salvar(questao);
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
			questaoDAO.remover(questao);
			questoes.remove(questao);
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " removida com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível remover a " + nome);
		}
		return "";
	}

	public String remover(Questao questao)
	{
		try
		{
			questaoDAO.remover(questao);
			questoes.remove(questao);
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " removida com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível remover a " + nome);
		}
		return "";
	}

	@PostConstruct
	public void init()
	{
		if(idQuestao!=0)
		{
			Sessao.set("id", null);
			this.questao = questaoDAO.getQuestao(idQuestao);
			cadastro = false;
		}
	}
	
	public void arrumar()
	{
//		filtroQuestao.limpar();
//		this.questoes = questaoDAO.filtrar(filtroQuestao);
//		for(Questao questao : questoes)
//		{
//			if(questao.getResolucaoComentadaFile()!=null)
//			{
//				questao.setResolucaoComentadaFile(null);
//				questaoDAO.salvar(questao);
//			}
//		}
	}

	public void carregar(Questao questao)
	{
		this.questao = questao;
		activeIndex = 3;
		cadastro = false;
		PrimeFaces.current().executeScript("window.open('/gestaoQuestao/questao?questao="+questao.getId()+"&tab="+3+"', '_blank')");
	}
	
	public void filtrar()
	{
		this.questoes = questaoDAO.filtrar(filtroQuestao);
		
		questaoBean.setQuestoes(questoes);
		questaoBean.setIndex(0);
		questaoBean.setQuestoes();
	}

	public void inserirPonto()
	{
		for(Alternativa alternativa : questao.getAlternativas())
		{
			alternativa.setTexto(alternativa.getTexto().concat("."));
		}
	}

	/** Importa em massa as questões serializadas do scraping (delegado ao {@link QuestaoImportService}). */
	public void adicionarQuestoesSalvas()
	{
		int importadas = questaoImportService.importarQuestoesSalvas(inicioCarregamento, fimCarregamento);
		FacesContext.getCurrentInstance().addMessage("growl",
			new FacesMessage(FacesMessage.SEVERITY_INFO, importadas + " questão(ões) importada(s) com sucesso.", ""));
	}

	public void reorderParagrafo(ReorderEvent event)
	{
		int index = 0;
		for(Paragrafo paragrafo : questao.getParagrafos())
			paragrafo.setOrdem(index++);

		questaoDAO.salvar(questao);
	}

	public void reorderAlternativa(ReorderEvent event)
	{
		int index = 0;
		for(Alternativa alternativa : questao.getAlternativas())
			alternativa.setOrdem(index++);

		questaoDAO.salvar(questao);
	}

	public void setActiveIndex(int activeIndex)
	{
		this.activeIndex = activeIndex;
		if(questao!=null)
			FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts()
			.add("history.pushState({}, null, 'questao?questao=" + questao.getId() + "&tab=" + activeIndex + "');");
	}

}