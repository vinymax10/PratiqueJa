package bean.publicacao;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.PaiBean;
import bean.usuario.ControleAcessoBean;
import bean.util.Mensagem;
import dao.academico.AssuntoDAO;
import dao.publicacao.PedidoPostDAO;
import dao.publicacao.ConfigPostDAO;
import modelo.publicacao.ConfigPost;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.academico.Assunto;
import modelo.publicacao.ItemPedidoPost;
import modelo.publicacao.PedidoPost;
import modelo.publicacao.PerfilCriador;
import modelo.publicacao.StatusPedidoPost;
import modelo.seguranca.PermissaoPadrao;
import modelo.usuario.Usuario;
import bean.seguranca.SessaoBean;
import service.publicacao.CreditoPostService;
import service.publicacao.MontadorPostService;
import service.publicacao.PostFormService;

@Data
@EqualsAndHashCode(callSuper = false)
@Named
@ViewScoped
public class PedidoPostBean extends PaiBean<PedidoPost, PedidoPostDAO, PermissaoPadrao<PedidoPost>>
{
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(PedidoPostBean.class);

	/** Posts grátis de teste (vitalício) para quem ainda não tem plano de conteúdo ativo. */
	public static final int LIMITE_TRIAL_GRATIS = 10;

	private List<PedidoPost> historico;
	private PedidoPost pedidoSelecionado;
	/** Lote escolhido no menu de ações para envio por e-mail (define o modo no modal de opções). */
	private PedidoPost pedidoParaEmail;
	private String codigoDestaque;

	private List<Assunto> assuntos;

	private int postsUsadosNoMes;
	private int postsUsadosTotal;

	/** Dialog de créditos insuficientes — título e mensagem definidos em validar(). */
	private String dialogCotaTitulo;
	private String dialogCotaMensagem;

	@Inject
	private AssuntoDAO assuntoDAO;

	@Inject
	private MontadorPostService montadorService;

	@Inject
	private PostFormService postFormService;

	@Inject
	private ControleAcessoBean controleAcessoBean;

	@Inject
	private SessaoBean sessaoBean;

	@Inject
	private CreditoPostService creditoPostService;

	@Inject
	private ConfigPostDAO configPostDAO;

	/**
	 * Usuário cujos posts estão sendo visualizados quando um ADMIN abre a lista com {@code ?configPost=ID}
	 * (para dar suporte). {@code null} = o próprio usuário logado (fluxo normal).
	 */
	private Usuario usuarioVisualizado;

	/** Id do {@code ?configPost} em modo suporte, para reanexar o parâmetro nas navegações (list↔form). */
	private Long idConfigSuporte;

	public PedidoPostBean()
	{
		super(PedidoPost.class, "Posts");
		urlLista = "/post/gerar/list.xhtml";
		urlCadastro = "/post/gerar/form.xhtml";
	}

	@PostConstruct
	public void init()
	{
		historico = new ArrayList<>();
		postsUsadosNoMes = 0;
		postsUsadosTotal = 0;
		resolverUsuarioVisualizado();
		try
		{
			assuntos = assuntoDAO.listarOpcoesAtivas();
		}
		catch(Exception e)
		{
			LOG.error("Falha ao listar assuntos", e);
			assuntos = new ArrayList<>();
		}
		carregarOuNovo();
		try
		{
			carregarHistorico();
		}
		catch(Exception e)
		{
			LOG.error("Falha ao carregar histórico de posts", e);
		}
		try
		{
			calcularUso();
		}
		catch(Exception e)
		{
			LOG.error("Falha ao calcular uso de posts", e);
		}
		try
		{
			Object novo = FacesContext.getCurrentInstance().getExternalContext().getFlash().get("postNovoCodigo");
			if(novo != null)
				codigoDestaque = novo.toString();
		}
		catch(Exception e)
		{
			LOG.error("Falha ao ler código em destaque", e);
		}
	}

	private void novoPedido()
	{
		entidade = new PedidoPost();
		// Modo suporte: o pedido pertence ao usuário atendido (dono do configPost), não ao admin logado.
		Usuario usuario = usuarioAlvo();
		entidade.setUsuario(usuario);
		if(usuario != null)
			entidade.setConfigPost(usuario.getConfigPost());
	}

	/** Abre um rascunho existente (parâmetro {@code rascunho}) para continuar a configuração, ou um novo pedido. */
	private void carregarOuNovo()
	{
		String idParam = FacesContext.getCurrentInstance().getExternalContext()
			.getRequestParameterMap().get("rascunho");
		if(idParam != null && !idParam.isBlank())
		{
			try
			{
				PedidoPost rascunho = entidadeDAO.carrega(Long.valueOf(idParam));
				// Posse pelo usuário-alvo: em modo suporte o admin edita o rascunho do usuário atendido.
				Usuario usuario = usuarioAlvo();
				if(rascunho != null && usuario != null && rascunho.getUsuario() != null
					&& usuario.getId().equals(rascunho.getUsuario().getId())
					&& rascunho.getStatus() == StatusPedidoPost.RASCUNHO)
				{
					entidade = rascunho;
					return;
				}
			}
			catch(Exception e)
			{
				LOG.error("Falha ao carregar rascunho", e);
			}
		}
		novoPedido();
	}

	public int totalCreditos()
	{
		return entidade.getItens().stream().mapToInt(ItemPedidoPost::getCreditos).sum();
	}

	// ── Solicitação ───────────────────────────────────────────────────

	/**
	 * Início da geração (botão "Gerar posts" na list). Exige o login ANTES de abrir o formulário:
	 * assim o usuário deslogado não preenche a configuração toda para só ser barrado na geração e
	 * perder o trabalho. Deslogado: abre o login e permanece na list (após logar, clica de novo).
	 * Logado: navega para o formulário.
	 */
	public String novoPost()
	{
		if(!controleAcessoBean.verificaEstaLogado())
			return null;

		return "/post/gerar/form?faces-redirect=true" + sufixoSuporte();
	}

	/**
	 * Sufixo {@code &configPost=ID} para reanexar o modo suporte do admin nas navegações (list→form,
	 * form→list); vazio no fluxo normal. Sem isso o parâmetro se perde no redirect e o admin volta a
	 * gerenciar a própria config em vez da do usuário atendido.
	 */
	private String sufixoSuporte()
	{
		return (usuarioVisualizado != null && idConfigSuporte != null) ? "&configPost=" + idConfigSuporte : "";
	}

	public String solicitar()
	{
		if(!controleAcessoBean.verificaEstaLogado())
			return null;

		if(!validar())
			return null;

		postFormService.solicitarGeracao(entidade, usuarioAlvo());

		Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_INFO,
			"Geração iniciada! Código: " + entidade.getCodigoBatch() + ". Acompanhe o progresso na lista.");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("postNovoCodigo", entidade.getCodigoBatch());

		return urlLista + "?faces-redirect=true" + sufixoSuporte();
	}

	private boolean validar()
	{
		Usuario usuario = usuarioAlvo();

		if(usuario == null || usuario.getConfigPost() == null)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR,
				"Configure seu perfil de posts (logo, cores) antes de gerar conteúdo.");
			return false;
		}

		// Campos obrigatórios da configuração — sem eles a geração produziria post inválido
		// (a logo, por ex., é usada direto no layout: configPost.getLogo() daria NullPointer).
		ConfigPost config = usuario.getConfigPost();
		List<String> faltando = new ArrayList<>();
		if(config.getLogo() == null)
			faltando.add("logomarca");
		if(config.getEstilo() == null)
			faltando.add("estilo do post");
		if(config.getCorDestaque() == null || config.getCorDestaque().isBlank())
			faltando.add("cor de destaque");
		if(config.getFinalidadeCta() == null)
			faltando.add("finalidade do CTA");

		if(!faltando.isEmpty())
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR,
				"Complete a configuração de posts antes de gerar. Faltando: " + String.join(", ", faltando) + ".");
			return false;
		}

		if(entidade.getItens().isEmpty())
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Adicione ao menos um assunto.");
			return false;
		}

		int total = totalCreditos();
		if(total < 1)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "O pedido não tem posts a gerar.");
			return false;
		}

		int disponiveis = creditosDisponiveis();
		if(total > disponiveis)
		{
			abrirDialogCota(
				disponiveis <= 0
					? "Créditos esgotados"
					: "Créditos insuficientes",
				disponiveis <= 0
					? "Você não tem posts disponíveis. Assine um plano para continuar gerando conteúdo."
					: "Você solicitou mais posts do que o disponível. Você tem " + disponiveis + " post(s) disponível(is)."
			);
			return false;
		}

		return true;
	}

	private void abrirDialogCota(String titulo, String mensagem)
	{
		dialogCotaTitulo = titulo;
		dialogCotaMensagem = mensagem;
		PrimeFaces.current().executeScript("PF('dlgCota').show()");
	}

	/** Usuário em teste grátis: plano não renovável (cota total fixa). */
	public boolean isModoTrial()
	{
		Usuario usuario = usuarioAlvo();
		if(usuario == null)
			return false;
		return !usuario.getPerfilCriador().isRenovavel();
	}

	public int trialRestante()
	{
		return creditoPostService.creditosRestantes(usuarioAlvo(), planoAtual());
	}

	/** Salva o pedido como rascunho (sem gerar), para o usuário continuar a configuração depois. */
	public String salvarRascunho()
	{
		if(!controleAcessoBean.verificaEstaLogado())
			return null;

		entidade = postFormService.salvarRascunho(entidade, usuarioAlvo());

		Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_INFO,
			"Rascunho salvo. Gere quando terminar de configurar.");
		return urlLista + "?faces-redirect=true" + sufixoSuporte();
	}

	/** Gera um rascunho salvo direto do histórico (valida cota e dispara a geração). */
	public String gerarRascunho(PedidoPost rascunho)
	{
		entidade = rascunho;
		return solicitar();
	}

	// ── Progresso / download ──────────────────────────────────────────

	public void atualizarProgresso()
	{
		if(historico == null)
			return;
		for(PedidoPost pedido : historico)
		{
			if(pedido.getStatus() == StatusPedidoPost.GERANDO || pedido.getStatus() == StatusPedidoPost.AGUARDANDO)
			{
				PedidoPost atualizado = entidadeDAO.carrega(pedido.getId());
				pedido.sincronizarProgresso(atualizado);
			}
		}
	}

	public boolean temGerandoEmProgresso()
	{
		if(historico == null)
			return false;
		return historico.stream().anyMatch(p -> p.getStatus() == StatusPedidoPost.GERANDO
			|| p.getStatus() == StatusPedidoPost.AGUARDANDO);
	}

	public boolean estaExpirado(PedidoPost p)
	{
		return p.getDataExpiracao() != null && p.getDataExpiracao().isBefore(LocalDateTime.now());
	}

	/** Carrega o pedido (com os itens) para exibição read-only no detalhe. */
	public void verPedido(PedidoPost pedido)
	{
		pedidoSelecionado = entidadeDAO.carrega(pedido.getId());
	}

	/** Abre o modal de opções de envio por e-mail (e-mail único com ZIP × um e-mail por post). */
	public void prepararEnvioEmail(PedidoPost pedido)
	{
		pedidoParaEmail = pedido;
	}

	/** Envia tudo num único e-mail, com o ZIP anexado (modo escolhido no modal). */
	public void enviarEmailZip()
	{
		enviarEmail(pedidoParaEmail);
	}

	/** Envia um e-mail por post, no mesmo padrão dos e-mails da programação diária. */
	public void enviarEmailPorPost()
	{
		if(pedidoParaEmail == null || pedidoParaEmail.getCaminhoArquivo() == null)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_WARN, "Este lote não está mais disponível para envio.");
			return;
		}
		try
		{
			montadorService.reenviarPorPost(pedidoParaEmail.getId());
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO,
				"Estamos preparando um e-mail para cada post. Eles chegarão em instantes.");
		}
		catch(Exception e)
		{
			LOG.error("Falha ao enviar posts por e-mail", e);
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível enviar os e-mails.");
		}
	}

	/** Envia o ZIP de um lote já gerado para o e-mail do usuário. */
	public void enviarEmail(PedidoPost pedido)
	{
		if(pedido == null || pedido.getCaminhoArquivo() == null)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_WARN, "Este lote não está mais disponível para envio.");
			return;
		}
		try
		{
			montadorService.reenviarPorEmail(pedido.getId());
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Lote enviado para o seu e-mail.");
		}
		catch(Exception e)
		{
			LOG.error("Falha ao enviar lote por e-mail", e);
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível enviar o e-mail.");
		}
	}

	public DefaultStreamedContent download(PedidoPost p) throws IOException
	{
		if(p.getCaminhoArquivo() == null)
			return null;

		byte[] bytes = Files.readAllBytes(Path.of(p.getCaminhoArquivo()));

		return DefaultStreamedContent.builder()
			.name(p.getNomeDownload()).contentType("application/zip")
			.stream(() -> new ByteArrayInputStream(bytes)).build();
	}

	// ── Cota / créditos ───────────────────────────────────────────────

	private PerfilCriador planoAtual()
	{
		Usuario usuario = usuarioAlvo();
		return usuario != null ? usuario.getPerfilCriador() : PerfilCriador.Basico;
	}

	public String getNomePlano()
	{
		return planoAtual().getNome();
	}

	public int getCreditosMensais()
	{
		return planoAtual().getCreditosMensais();
	}

	public int cotaDisponivel()
	{
		Usuario usuario = usuarioAlvo();
		int rollover = usuario != null ? usuario.getCreditoRolloverPost() : 0;
		return getCreditosMensais() + rollover;
	}

	public int creditosRestantes()
	{
		return Math.max(0, cotaDisponivel() - postsUsadosNoMes);
	}

	/** Créditos disponíveis independente do modo (trial ou plano ativo). */
	public int creditosDisponiveis()
	{
		return creditoPostService.creditosRestantes(usuarioAlvo(), planoAtual());
	}

	public String descricaoPlano()
	{
		if(isModoTrial())
			return "Teste grátis (" + trialRestante() + " de " + planoAtual().getCreditosMensais() + " restantes)";
		return planoAtual().getNome() + " (" + getCreditosMensais() + " posts/mês)";
	}

	public int getLimiteTrialGratis()
	{
		return LIMITE_TRIAL_GRATIS;
	}

	// ── Utilitários ───────────────────────────────────────────────────

	private void carregarHistorico()
	{
		historico = entidadeDAO.buscarPorUsuario(usuarioAlvo());
	}

	/** Usuário efetivo da lista: o visualizado pelo admin (se houver) ou o próprio logado. */
	private Usuario usuarioAlvo()
	{
		return usuarioVisualizado != null ? usuarioVisualizado : sessaoBean.getUsuario();
	}

	/**
	 * Resolve o {@code ?configPost=ID} do request: se o usuário logado é admin, carrega o ConfigPost
	 * e passa a exibir a lista do dono dele. Fora disso (não-admin ou sem parâmetro), fica no fluxo
	 * normal (o próprio logado). Evita que um não-admin veja posts de terceiros forjando o parâmetro.
	 */
	private void resolverUsuarioVisualizado()
	{
		usuarioVisualizado = null;
		idConfigSuporte = null;
		Usuario logado = sessaoBean.getUsuario();
		if(logado == null || !logado.isAdmin())
			return;

		String cfg = FacesContext.getCurrentInstance().getExternalContext()
			.getRequestParameterMap().get("configPost");
		if(cfg == null || cfg.isBlank())
			return;

		try
		{
			ConfigPost configPost = configPostDAO.getConfigPost(Long.valueOf(cfg));
			// Só marca "modo suporte" quando o dono é OUTRO usuário (logado != detentor do configPost).
			if(configPost != null && configPost.getUsuario() != null
				&& !configPost.getUsuario().getId().equals(logado.getId()))
			{
				usuarioVisualizado = configPost.getUsuario();
				idConfigSuporte = configPost.getId();
			}
		}
		catch(Exception e)
		{
			LOG.error("Falha ao resolver usuário do configPost para visualização admin", e);
		}
	}

	/** Usuário que o admin está visualizando (null quando é a própria lista do logado). Para banner na UI. */
	public Usuario getUsuarioVisualizado()
	{
		return usuarioVisualizado;
	}

	private void calcularUso()
	{
		LocalDateTime inicioMes = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
		LocalDateTime inicioProximo = inicioMes.plusMonths(1);
		postsUsadosNoMes = entidadeDAO.somarPostsNoMes(usuarioAlvo(), inicioMes, inicioProximo);
		postsUsadosTotal = entidadeDAO.somarPostsTotal(usuarioAlvo());
	}
}
