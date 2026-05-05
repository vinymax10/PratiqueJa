package Bean.Exercicio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import Bean.AutenticacaoBean;
import Bean.Download.Diretorio;
import Bean.Download.PoolNomesBean;
import Bean.Exercicio.Filtro.FiltroExercicio;
import Bean.Usuario.ControleAcessoBean;
import DAO.Exercicio.ExercicioDAO;
import DAO.Exercicio.ResultadoExercicioDAO;
import DAO.Usuario.UsuarioDAO;
import Infra.Mensagem;
import Infra.Navegacao;
import Modelo.Exercicio.Exercicio;
import Modelo.Exercicio.ExercicioPadrao;
import Modelo.Exercicio.ResultadoExercicio;
import Modelo.Matematica.Conta;
import Modelo.Usuario.Usuario;
import Pdf.latex.GerarLatexExercicio;
import Session.SessionContext;

@Named
@ViewScoped
public class ExercicioBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private ExercicioDAO exercicioDAO;

	@Inject
	private Exercicio exercicio;

	@Inject
	AutenticacaoBean autenticacaoBean;

	DecimalFormat deci = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.FRANCE));

	private String nome = "Exercicio";
	private boolean lista = true;
	private boolean cadastro = false;

	private List<Exercicio> exercicios = new ArrayList<Exercicio>();

	@Inject
	private UsuarioDAO usuarioDAO;

	private int activeIndex;

	@Inject
	private FiltroExercicio filtroExercicio;

	private Long id;

	@Inject
	private ConfigDownload configDownload;

	@Inject
	private PoolNomesBean poolNomesBean;
	
	@Inject
	private ControleAcessoBean controleAcessoBean;
	
	private ExercicioPadrao exercicioPadrao;
	
	@Inject
	private ResultadoExercicioDAO resultadoExercicioDAO;
	
	public String cadastrar()
	{
		activeIndex = 0;
		cadastro = true;
		lista = false;
		exercicio = new Exercicio();
		return "";
	}
	
	public String criarExercicio(ExercicioPadrao exercicioPadrao)
	{
		if(controleAcessoBean.verificaEstaLogado())
		{
			exercicio = new Exercicio();
			exercicio.setExercicioPadrao(exercicioPadrao);
			Usuario usuario = (Usuario) SessionContext.getInstance().getAttribute("UsuarioLogado");
			exercicio.setUsuario(usuario);
			
			construirExercicio();
			Navegacao.redirect("/exercicio/exercicio/verExercicio.xhtml?id="+exercicio.getId());
		}
		return "";
	}

	private void construirExercicio()
	{
		Conta conta;
		try
		{
			for(int i = 0; i < exercicio.getExercicioPadrao().getQuantidade(); i++)
			{
				do
				{
					conta = (Conta) Class.forName(exercicio.getExercicioPadrao().getClasse()).getConstructor(Integer.TYPE).newInstance(i + 1);
					conta.setExercicio(exercicio);
					conta.setTipoExercicio(exercicio.getExercicioPadrao().getTipoExercicio());
				}
				while(exercicio.getContas().contains(conta));
				exercicio.getContas().add(conta);
			}
			
			if(exercicio.getPrazo()==null)
				exercicio.setPrazo(LocalDate.now());
			
			exercicioDAO.salvar(exercicio);

		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
		| ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public String adicionar()
	{
		try
		{
			construirExercicio();
			cadastro = false;
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " adicionado com sucesso.");
		}
		catch(IllegalArgumentException | SecurityException e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível adicionar a " + nome + ". Tipo de exercício não encontrado.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível adicionar a " + nome);
		}

		return "";
	}

	public void update()
	{
		try
		{
			exercicioDAO.salvar(exercicio);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível salvar a " + nome);
		}
	}
	
	public String salvar()
	{
		try
		{
			exercicio=exercicioDAO.salvar(exercicio);
			lista = true;
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Nova " + nome + " salva com sucesso.", ""));
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
			exercicioDAO.remover(exercicio);
			exercicios.remove(exercicio);
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

	public String responder(Conta conta)
	{
		conta.setRespondida(true);
		if(conta.isCorreta())
			exercicio.incrementaContasCorretas();
		
		exercicio.incrementaContasRealizadas();
		exercicio.calculaNota();
		exercicio.setRealizacao(LocalDate.now());
		exercicio.setRealizado(true);
		
		if(exercicio.getResultadoExercicio()==null)
		{
			ResultadoExercicio resultadoExercicio = new ResultadoExercicio();
			resultadoExercicio.setExercicioPadrao(exercicio.getExercicioPadrao());
			resultadoExercicio.setUsuario(exercicio.getUsuario());
			resultadoExercicio.setRealizacao(LocalDate.now());
			resultadoExercicio.setNota(exercicio.getNota());
			resultadoExercicioDAO.salvar(resultadoExercicio);
			exercicio.setResultadoExercicio(resultadoExercicio);
		}
		else
		{
			ResultadoExercicio resultadoExercicio = exercicio.getResultadoExercicio();
			resultadoExercicio.setNota(exercicio.getNota());
			resultadoExercicioDAO.salvar(resultadoExercicio);
		}
		exercicioDAO.salvar(exercicio);
		return "";
	}
	
//	public String finalizar()
//	{
//		if(!exercicio.isRealizado())
//		{
//			int numCorretas = 0;
//			for(Conta conta : exercicio.getContas())
//			{
//				conta.setRespondida(true);
//				if(conta.isCorreta())
//					numCorretas++;
//			}
//			exercicio.setRealizado(true);
//			exercicio.setRealizacao(LocalDate.now());
//			exercicio.setNota(100*(double) numCorretas / exercicio.getContas().size());
//			
//			ResultadoExercicio resultadoExercicio = new ResultadoExercicio();
//			resultadoExercicio.setExercicioPadrao(exercicio.getExercicioPadrao());
//			resultadoExercicio.setUsuario(exercicio.getUsuario());
//			resultadoExercicio.setRealizacao(LocalDate.now());
//			resultadoExercicio.setNota(100*(double) numCorretas / exercicio.getContas().size());
//			resultadoExercicioDAO.salvar(resultadoExercicio);
//			
//			try
//			{
//				exercicioDAO.salvar(exercicio);
//				Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " Finalizada com sucesso\nNota: " + deci.format(exercicio.getNotaPorcentagem()));
//			}
//			catch(Exception e)
//			{
//				e.printStackTrace();
//				Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível finalizar a " + nome);
//			}
//		}
//		return "";
//	}

	public void filtrar()
	{
		this.exercicios = exercicioDAO.buscar(filtroExercicio);
	}

	public StreamedContent download(boolean criarExercicio)
	{
		if(criarExercicio)
		{
			exercicio = new Exercicio();
			exercicio.setExercicioPadrao(exercicioPadrao);
			Usuario usuario = (Usuario) SessionContext.getInstance().getAttribute("UsuarioLogado");
			exercicio.setUsuario(usuario);
			
			construirExercicio();
		}
		
		Usuario usuario = (Usuario) SessionContext.getInstance().getAttribute("UsuarioLogado");
		usuario = usuarioDAO.carrega(usuario.getId());
		configDownload.setUsuario(usuario);
		
		Diretorio diretorio = poolNomesBean.criarDiretorio();
		
		GerarLatexExercicio gerarLatex=new GerarLatexExercicio(diretorio);
		
		gerarLatex.gerarPDFExercicio(exercicio, configDownload);
		gerarLatex.gerar();
		
		File initialFile = new File(diretorio.getEnderecoPdf());
	    InputStream inStream;
	    StreamedContent file=null;
		try
		{
			inStream = new FileInputStream(initialFile);
			file = DefaultStreamedContent.builder().name(
			"Exercicio" + exercicio.getExercicioPadrao().getAssuntoCurso().getChave() + exercicio.getExercicioPadrao().getNivel() + ".pdf")
			.contentType("aplication/pdf").stream(() -> inStream).build();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		poolNomesBean.freeDiretorio(diretorio);
		controleAcessoBean.registrarDownloadExercicio();

		return file;
	}

	public void toogleResolucao(Conta conta)
	{
		conta.toogleShowResolucao();
		controleAcessoBean.registrarResolucaoExercicio();
	}
	
	public void podeFazerDownload(ExercicioPadrao exercicioPadrao)
	{
		if(controleAcessoBean.verificaEstaLogado())
		{
			this.exercicioPadrao=exercicioPadrao;
			if(controleAcessoBean.podeFazerDownloadExercicio())
				PrimeFaces.current().executeScript("PF('downloadExercicioWidget').show()");
			else
				controleAcessoBean.showUpgrade("Limite diário de downloads de exercícios foi excedido."
				+ "\nPor favor faça o upgrade de sua conta.");
		}
	}
	
	public void onSelected()
	{
		activeIndex = 0;
		cadastro = false;
		lista = false;
	}

	public List<Exercicio> getMeusExercicios(Boolean realizada)
	{
		Usuario usuario = (Usuario) SessionContext.getInstance().getAttribute("UsuarioLogado");
		return exercicioDAO.meusExercicios(usuario, realizada);
	}

	public Long numeroMeusExercicios(Boolean realizado)
	{
		Usuario usuario = (Usuario) SessionContext.getInstance().getAttribute("UsuarioLogado");
		return exercicioDAO.numeroMeusExercicios(usuario, realizado);
	}
	
	@PostConstruct
	public void init()
	{
		if(id != null)
		{
			exercicio = exercicioDAO.carrega(id);
		}
	}
	
	public Exercicio getExercicio()
	{
		return exercicio;
	}

	public void setExercicio(Exercicio exercicio)
	{
		this.exercicio = exercicio;
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

	public List<Exercicio> getExercicios()
	{
		return exercicios;
	}

	public void setExercicios(List<Exercicio> exercicios)
	{
		this.exercicios = exercicios;
	}

	public int getActiveIndex()
	{
		return activeIndex;
	}

	public void setActiveIndex(int activeIndex)
	{
		this.activeIndex = activeIndex;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public FiltroExercicio getFiltroExercicio()
	{
		return filtroExercicio;
	}

	public void setFiltroExercicio(FiltroExercicio filtroExercicio)
	{
		this.filtroExercicio = filtroExercicio;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public ConfigDownload getConfigDownload()
	{
		return configDownload;
	}

	public void setConfigDownload(ConfigDownload configDownload)
	{
		this.configDownload = configDownload;
	}

	public ExercicioDAO getExercicioDAO()
	{
		return exercicioDAO;
	}

	public void setExercicioDAO(ExercicioDAO exercicioDAO)
	{
		this.exercicioDAO = exercicioDAO;
	}

}