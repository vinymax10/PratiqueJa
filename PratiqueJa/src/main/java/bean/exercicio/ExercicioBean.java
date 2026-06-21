package bean.exercicio;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.PaiBean;
import bean.usuario.ControleAcessoBean;
import bean.util.Mensagem;
import exceptions.RelacaoException;
import modelo.auditoria.TipoEvento;
import dao.exercicio.ExercicioDAO;
import filtro.exercicio.FiltroExercicio;
import infra.Cripto;
import infra.Navegacao;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import service.exercicio.ExercicioService.ResultadoLote;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.EqualsAndHashCode;
import matematica.ExercicioFactory;
import modelo.academico.Assunto;
import modelo.exercicio.Exercicio;
import modelo.exercicio.ExercicioPadrao;
import modelo.pdf.Visibilidade;
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

	/** Seleção múltipla de exercícios padrão no cadastro. */
	private List<ExercicioPadrao> exerciciosPadrao = new ArrayList<>();

	/** Usado apenas na tela de cadastro (não persiste na entidade). */
	private boolean global=true;

	/** Quantidade de exercícios a gerar no cadastro. */
	private int quantidade = 1;

	/** Exercícios do assunto exibidos na aba "Exercícios" (matematica/exercicio.xhtml). */
	private List<Exercicio> exercicios;

	/** Assunto corrente carregado pela aba de exercícios (matematica/exercicio.xhtml). */
	private Assunto assuntoAtual;

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

	/**
	 * Cadastro admin: gera o Exercício a partir do Exercício Padrão escolhido
	 * (usa getClasse() para instanciar o gerador) + valor global, persiste e
	 * redireciona para a tela de edição (form.xhtml).
	 */
	public String adicionarDoPadrao()
	{
		try
		{
			int total = 0;
			for(ExercicioPadrao padrao : exerciciosPadrao)
			{
				for(int i = 0; i < quantidade; i++)
				{
					Exercicio exercicio = ExercicioFactory.gerar(padrao.getClasse(), i + 1);
					exercicio.setAssunto(padrao.getAssunto());
					exercicio.setNivel(padrao.getNivel());
					exercicio.setGlobal(global);

					exercicioService.construirExercicio(exercicio);
					total++;
				}
			}

			Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_INFO,
			total + " " + nome + "(s) adicionado(s) com sucesso.");
			Navegacao.redirect(urlLista);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível adicionar o " + nome);
		}
		return "";
	}

	public String criarExercicio(ExercicioPadrao exercicioPadrao)
	{
		if(controleAcessoBean.verificaEstaLogado())
		{
			entidade = ExercicioFactory.gerar(exercicioPadrao.getClasse(), 0);
			entidade.setAssunto(exercicioPadrao.getAssunto());
			entidade.setNivel(exercicioPadrao.getNivel());

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

	public String responder()
	{
		exercicioService.registrarResposta(entidade);
		return "";
	}

	/** Carrega os exercícios do assunto para a aba "Exercícios". */
	public void filtrarPorAssunto(Assunto assunto)
	{
		if(assunto != null)
		{
			assuntoAtual = assunto;
			exercicios = entidadeDAO.buscarPorAssunto(assunto);
		}
	}

	/** Registra a resposta de um exercício específico (cards da aba). */
	public String responder(Exercicio exercicio)
	{
		if(bloqueado(exercicio))
			return "";

		exercicioService.registrarResposta(exercicio);
		return "";
	}

	/**
	 * Exibe/oculta a resolução comentada de um exercício específico (cards da aba),
	 * barrando exercícios Premium para usuários do plano básico.
	 */
	public void toogleResolucaoComentada(Exercicio exercicio)
	{
		if(bloqueado(exercicio))
			return;

		exercicio.toogleResolucaoComentada();
	}

	/**
	 * Exercício Premium indisponível para o usuário atual (não logado ou plano básico).
	 * Usado tanto para barrar a ação no servidor quanto para renderizar o aviso de upgrade.
	 */
	public boolean bloqueado(Exercicio exercicio)
	{
		return exercicio != null
			&& exercicio.getVisibilidade() == Visibilidade.Premium
			&& !controleAcessoBean.podeAcessarPremium();
	}

	public void toogleResolucao()
	{
		entidade.toogleResolucaoComentada();
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

	public void remover(Exercicio exercicio)
	{
		try
		{
			this.entidade = exercicio;
			carregarPermissao();
			validar(!permissao.isPodeRemover(), Mensagem.messagePermissaoNegada());
			podeRemover(exercicio);
			if(auditoriasAtivas.contains(TipoEvento.EXCLUSAO))
				auditoriaService.registrarExclusao(classe, exercicio.getId(), exercicio);
			if(lista != null)
				lista.remove(exercicio);
			getListaTudo().remove(exercicio);
			entidadeDAO.remover(exercicio);
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " removido(a) com sucesso.");
		}
		catch(RelacaoException e)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, e.getMessage());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível remover o(a) " + nome);
		}
	}

	public void gerarTodos()
	{
		try
		{
			ResultadoLote resultado = exercicioService.gerarTodos();

			String msg = resultado.getGerados() + " exercício(s) gerado(s)";
			if(resultado.getIgnorados() > 0)
				msg += ", " + resultado.getIgnorados() + " ignorado(s) (sem exercício padrão)";
			if(resultado.getErros() > 0)
				msg += ", " + resultado.getErros() + " erro(s)";
			msg += ".";

			FacesMessage.Severity severity = resultado.getErros() > 0 ? FacesMessage.SEVERITY_WARN : FacesMessage.SEVERITY_INFO;
			Mensagem.send("growl", severity, msg);
			filtrar();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Erro ao gerar exercícios: " + e.getMessage());
		}
	}

	public void removerTodos()
	{
		if(lista == null || lista.isEmpty())
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_WARN, "Nenhum exercício para remover.");
			return;
		}
		try
		{
			this.entidade = lista.get(0);
			carregarPermissao();
			validar(!permissao.isPodeRemover(), Mensagem.messagePermissaoNegada());

			List<Exercicio> copia = new ArrayList<>(lista);
			for(Exercicio exercicio : copia)
			{
				podeRemover(exercicio);
				if(auditoriasAtivas.contains(TipoEvento.EXCLUSAO))
					auditoriaService.registrarExclusao(classe, exercicio.getId(), exercicio);
				getListaTudo().remove(exercicio);
				entidadeDAO.remover(exercicio);
			}
			lista.clear();
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, copia.size() + " " + nome + "(s) removido(s) com sucesso.");
		}
		catch(RelacaoException e)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, e.getMessage());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível remover os(as) " + nome + "s");
		}
	}

}
