package Bean.AssuntoCurso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;

import org.primefaces.event.ReorderEvent;

import Bean.Questao.QuestaoBean;
import DAO.AssuntoCursoDAO;
import DAO.Teste.ResultadoTesteDAO;
import Infra.Mensagem;
import Modelo.AssuntoCurso.AssuntoCurso;
import Modelo.AssuntoCurso.Enum.Modulo;
import Modelo.Exercicio.ExercicioPadrao;
import Modelo.Usuario.Usuario;
import Session.SessionContext;

@Named
@ViewScoped
public class AssuntoCursoBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private AssuntoCursoDAO assuntoCursoDAO;

	@Inject
	private AssuntoCurso assuntoCurso;

	private String nome = "Assunto";
	private boolean lista = true;
	private boolean cadastro = false;

	private List<AssuntoCurso> assuntosCurso = new ArrayList<AssuntoCurso>();
	
	@Inject
	private ResultadoTesteDAO resultadoTesteDAO;

	private int activeIndex;

	@Inject
	private FiltroAssuntoCurso filtroAssuntoCurso;

	@Inject
	private QuestaoBean questaoBean;
	
	private String chave;
	
	public String cadastrar()
	{
		cadastro = true;
		lista = false;
		assuntoCurso = new AssuntoCurso();
		return "";
	}

	public String adicionar()
	{
		try
		{
			assuntoCursoDAO.salvar(assuntoCurso);
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
			assuntoCurso=assuntoCursoDAO.salvar(assuntoCurso);
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
			assuntoCursoDAO.remover(assuntoCurso);
			assuntosCurso.remove(assuntoCurso);
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

	public void onSelected()
	{
		cadastro = false;
		lista = false;
	}

	public String cancelar()
	{
		lista = true;
		return "";
	}

	public void ordenarIndice()
	{
		assuntosCurso.remove(assuntoCurso);
		assuntosCurso.add(assuntoCurso.getIndice(),assuntoCurso);
		int cont=0;
		for(AssuntoCurso assuntoCurso : assuntosCurso)
		{
			assuntoCurso.setIndice(cont++);
			assuntoCursoDAO.salvar(assuntoCurso);
		}
	}
	
	public void onRowReorder(ReorderEvent event)
	{
		AssuntoCurso entidade;
		for(int i = 0; i < assuntosCurso.size(); i++)
		{
			entidade=assuntosCurso.get(i);
			entidade.setIndice(i);
			assuntoCursoDAO.salvar(entidade);
		}
	}
	
	public List<ExercicioPadrao> getExerciciosPadrao()
	{
		if(assuntoCurso != null)
			return assuntoCursoDAO.getExerciciosPadrao(assuntoCurso);

		return null;
	}

	public String pdfAnotacao()
	{
		String url = "/pdf/" + assuntoCurso.getModulo().toString().toLowerCase() + "/" + assuntoCurso.getChave() + ".pdf";
		return url;
	}

	public String getKeyPdfEmbedAdobe()
	{
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String serverName = request.getServerName();
		
		if(serverName.equals("pratiqueja.com"))
			return "ffa6fcd96c444c23a7a33890f2cc3191";

		if(serverName.equals("www.pratiqueja.com"))
			return "f9531c1d598a4add8119f9fc90092f80";

		if(serverName.equals("pratiqueja.com.br"))
			return "d564d4ff38e949cf84d43cd17ddfed7d";

		if(serverName.equals("www.pratiqueja.com.br"))
			return "71f1532cf63f42d6b493cb75073e5b8d";

		if(serverName.equals("localhost"))
			return "f6fdcab0872641c5bd7c6963fbce62f4";
		return "";
	}

	public String pdfName()
	{
		String url = assuntoCurso.getChave() + ".pdf";
		return url;
	}
	
	private int numStar(AssuntoCurso assuntoCurso)
	{
		Usuario usuario = (Usuario) SessionContext.getInstance().getAttribute("UsuarioLogado");
		if(usuario==null)
			return 0;
		
		double resultado = resultadoTesteDAO.melhorResultado(assuntoCurso, usuario);
		
		if(resultado>=100)
			return 5;
		if(resultado>=80)
			return 4;
		if(resultado>=60)
			return 3;
		if(resultado>=40)
			return 2;
		if(resultado>=20)
			return 1;

		return 0;
	}
	
	public double porcentagemConcluida(AssuntoCurso assuntoCurso)
	{
		Usuario usuario = (Usuario) SessionContext.getInstance().getAttribute("UsuarioLogado");
		if(usuario!=null)
			return resultadoTesteDAO.melhorResultado(assuntoCurso, usuario);
		else
			return 0;
	}

	public void filtrar()
	{
		this.assuntosCurso = assuntoCursoDAO.buscar(filtroAssuntoCurso);
	}

	public List<AssuntoCurso> getTodosAssuntoCursos()
	{
		return assuntoCursoDAO.todos();
	}

	public List<AssuntoCurso> getTodosAssuntoCursos(Modulo modulo)
	{
		List<AssuntoCurso> assuntosCurso = assuntoCursoDAO.buscar(modulo);
		for(AssuntoCurso assuntoCurso : assuntosCurso)
			assuntoCurso.setNumStar(numStar(assuntoCurso));
		
		return assuntosCurso;
	}

	@PostConstruct
	public void init()
	{
		if(chave != null)
		{
			SessionContext.getInstance().setAttribute("assunto", null);
			this.assuntoCurso = assuntoCursoDAO.assunto(chave);
			questaoBean.buscaQuestao(assuntoCurso);
		}
	}

	public AssuntoCurso getAssuntoCurso()
	{
		return assuntoCurso;
	}

	public void setAssuntoCurso(AssuntoCurso usuario)
	{
		this.assuntoCurso = usuario;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
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

	public List<AssuntoCurso> getAssuntosCurso()
	{
		return assuntosCurso;
	}

	public void setAssuntosCurso(List<AssuntoCurso> assuntosCurso)
	{
		this.assuntosCurso = assuntosCurso;
	}

	public int getActiveIndex()
	{
		return activeIndex;
	}

	public void setActiveIndex(int activeIndex)
	{
		this.activeIndex = activeIndex;
		FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts()
		.add("history.pushState({}, null, 'assunto?assunto=" + assuntoCurso.getChave() + "&tab=" + activeIndex + "');");
	}

	public FiltroAssuntoCurso getFiltroAssuntoCurso()
	{
		return filtroAssuntoCurso;
	}

	public void setFiltroAssuntoCurso(FiltroAssuntoCurso filtroAssuntoCurso)
	{
		this.filtroAssuntoCurso = filtroAssuntoCurso;
	}

	public String getChave()
	{
		return chave;
	}

	public void setChave(String chave)
	{
		this.chave = chave;
	}

}
