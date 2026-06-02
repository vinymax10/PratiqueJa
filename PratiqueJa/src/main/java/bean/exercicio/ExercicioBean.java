package bean.exercicio;

import java.util.List;

import org.primefaces.PrimeFaces;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.PaiBean;
import bean.download.Diretorio;
import bean.usuario.ControleAcessoBean;
import bean.util.Mensagem;
import dao.exercicio.ExercicioDAO;
import filtro.exercicio.FiltroExercicio;
import infra.Cripto;
import infra.Navegacao;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.exercicio.Exercicio;
import modelo.exercicio.ExercicioPadrao;
import modelo.matematica.Conta;
import modelo.seguranca.PermissaoPadrao;
import service.configuracao.DiretorioService;
import service.exercicio.ExercicioService;
import web.session.Sessao;

@Data
@EqualsAndHashCode(callSuper = false)
@Named
@ViewScoped
public class ExercicioBean extends PaiBean<Exercicio, ExercicioDAO, PermissaoPadrao<Exercicio>>
{
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(ExercicioBean.class);

	@Inject
	private FiltroExercicio filtro;

	@Inject
	private ExercicioService exercicioService;

	@Inject
	private ConfigDownload configDownload;

	@Inject
	private DiretorioService diretorioService;

	@Inject
	private ControleAcessoBean controleAcessoBean;

	private ExercicioPadrao exercicioPadrao;

	public ExercicioBean()
	{
		super(Exercicio.class, "Exercício");
		urlCadastro = "/administracao/conteudo/exercicio/form.xhtml";
		urlLista    = "/administracao/conteudo/exercicio/list.xhtml";
	}

	@PostConstruct
	public void postConstruct()
	{
		if(tabState.hasState(FiltroExercicio.class))
			filtro = tabState.getState(FiltroExercicio.class);
	}

	/** Chamado de verExercicio.xhtml via f:viewAction após f:viewParam setar o id. */
	public void init()
	{
		if(id != null)
			entidade = exercicioService.carrega(id);
	}

	public Exercicio getExercicio() { return entidade; }
	public void setExercicio(Exercicio exercicio) { this.entidade = exercicio; }

	public void filtrar()
	{
		this.lista = entidadeDAO.buscar(filtro);
		tabState.putState(filtro);
	}

	public void filtrarInit()
	{
		filtro.limpar();
		filtrar();
	}

	@Override
	public String adicionar(boolean fica)
	{
		LOG.debug("adicionar");
		try
		{
			exercicioService.construirExercicio(entidade);
			carregarPermissao();
			Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_INFO, nome + " adicionado(a) com sucesso.");
			if(fica)
				Navegacao.redirect(urlCadastro + "?chave=" + Cripto.criptografar(String.valueOf(entidade.getId())));
			else
				Navegacao.redirect(urlLista);
		}
		catch(ClassNotFoundException | NoSuchMethodException e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Tipo de exercício não encontrado.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível adicionar o " + nome);
		}
		return "";
	}

	/** Chamado pelo remoteCommand em verExercicio.xhtml para persistir estado da resposta. */
	public void update()
	{
		entidadeDAO.salvar(entidade);
	}

	public String criarExercicio(ExercicioPadrao exercicioPadrao)
	{
		if(controleAcessoBean.verificaEstaLogado())
		{
			entidade = new Exercicio();
			entidade.setExercicioPadrao(exercicioPadrao);
			entidade.setUsuario(Sessao.getUsuarioLogado());

			try
			{
				exercicioService.construirExercicio(entidade);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível criar o exercício.");
				return "";
			}

			Navegacao.redirect("/exercicio/exercicio/verExercicio.xhtml?id=" + entidade.getId());
		}
		return "";
	}

	public String responder(Conta conta)
	{
		exercicioService.registrarResposta(entidade, conta);
		return "";
	}

	public StreamedContent download(boolean criarNovo)
	{
		if(criarNovo)
		{
			entidade = new Exercicio();
			entidade.setExercicioPadrao(exercicioPadrao);
			entidade.setUsuario(Sessao.getUsuarioLogado());

			try
			{
				exercicioService.construirExercicio(entidade);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}

		Diretorio diretorio = diretorioService.criarDiretorio();
		StreamedContent file = exercicioService.gerarPdfExercicio(entidade, configDownload, diretorio, Sessao.getUsuarioLogado());
		controleAcessoBean.registrarDownload(diretorio.getEnderecoPdf());
		diretorioService.freeDiretorio(diretorio);
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
			if(controleAcessoBean.podeFazerDownload())
				PrimeFaces.current().executeScript("PF('downloadExercicioWidget').show()");
			else
				controleAcessoBean.showUpgrade("Limite mensal de páginas baixadas foi excedido."
				+ "\nPor favor faça o upgrade de sua conta.");
		}
	}

	public List<Exercicio> getMeusExercicios(Boolean realizada)
	{
		return exercicioService.meusExercicios(Sessao.getUsuarioLogado(), realizada);
	}

	public Long numeroMeusExercicios(Boolean realizado)
	{
		return exercicioService.numeroMeusExercicios(Sessao.getUsuarioLogado(), realizado);
	}
}
