package bean.exercicio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.PrimeFaces;
import org.primefaces.model.StreamedContent;

import bean.download.Diretorio;
import service.configuracao.DiretorioService;
import bean.usuario.ControleAcessoBean;
import bean.util.Mensagem;
import filtro.exercicio.FiltroExercicio;
import infra.Navegacao;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import modelo.exercicio.Exercicio;
import modelo.exercicio.ExercicioPadrao;
import modelo.matematica.Conta;
import modelo.usuario.Usuario;
import service.exercicio.ExercicioService;
import web.session.Sessao;

@Data
@Named
@ViewScoped
public class ExercicioBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private ExercicioService exercicioService;

	@Inject
	private Exercicio exercicio;

	@Inject
	private FiltroExercicio filtroExercicio;

	@Inject
	private ConfigDownload configDownload;

	@Inject
	private DiretorioService diretorioService;

	@Inject
	private ControleAcessoBean controleAcessoBean;

	private String nome = "Exercicio";
	private boolean lista = true;
	private boolean cadastro = false;

	private List<Exercicio> exercicios = new ArrayList<Exercicio>();

	private int activeIndex;

	private Long id;

	private ExercicioPadrao exercicioPadrao;

	@PostConstruct
	public void init()
	{
		if(id != null)
			exercicio = exercicioService.carrega(id);
	}

	private Usuario getUsuarioLogado()
	{
		return Sessao.getUsuarioLogado();
	}

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
			exercicio.setUsuario(getUsuarioLogado());

			construirExercicio();
			Navegacao.redirect("/exercicio/exercicio/verExercicio.xhtml?id="+exercicio.getId());
		}
		return "";
	}

	private void construirExercicio()
	{
		try
		{
			exercicioService.construirExercicio(exercicio);
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
			exercicioService.salvar(exercicio);
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
			exercicio = exercicioService.salvar(exercicio);
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
			exercicioService.remover(exercicio);
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
		exercicioService.registrarResposta(exercicio, conta);
		return "";
	}

	public void filtrar()
	{
		this.exercicios = exercicioService.buscar(filtroExercicio);
	}

	public StreamedContent download(boolean criarNovo)
	{
		if(criarNovo)
		{
			exercicio = new Exercicio();
			exercicio.setExercicioPadrao(exercicioPadrao);
			exercicio.setUsuario(getUsuarioLogado());

			construirExercicio();
		}

		Diretorio diretorio = diretorioService.criarDiretorio();
		StreamedContent file = exercicioService.gerarPdfExercicio(exercicio, configDownload, diretorio, getUsuarioLogado());
		diretorioService.freeDiretorio(diretorio);
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
			this.exercicioPadrao = exercicioPadrao;
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
		return exercicioService.meusExercicios(getUsuarioLogado(), realizada);
	}

	public Long numeroMeusExercicios(Boolean realizado)
	{
		return exercicioService.numeroMeusExercicios(getUsuarioLogado(), realizado);
	}

}
