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
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.academico.Assunto;
import modelo.exercicio.Nivel;
import modelo.publicacao.Background;
import modelo.publicacao.FormatoPost;
import modelo.publicacao.ImagemPost;
import modelo.publicacao.ItemPedidoPost;
import modelo.publicacao.PedidoPost;
import modelo.publicacao.PerfilCriador;
import modelo.publicacao.StatusPedidoPost;
import modelo.seguranca.PermissaoPadrao;
import modelo.usuario.Usuario;
import bean.seguranca.SessaoBean;
import service.publicacao.CreditoPostService;
import service.publicacao.FilaGeracaoPostService;
import service.publicacao.MontadorPostService;

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

	/** Item em edição (assunto + nível + formato + fundo + quantidade). */
	private ItemPedidoPost item;

	/** true = formulário inline em modo de adição; false = edição de um item existente. */
	private boolean cadastroItem = true;

	/** true = o formulário inline de cadastro de item está aberto (substitui a lista). */
	private boolean editandoItem = false;

	/** Item da lista sendo editado (quando cadastroItem = false). */
	private ItemPedidoPost itemOriginal;

	private int postsUsadosNoMes;
	private int postsUsadosTotal;

	/** Dialog de créditos insuficientes — título e mensagem definidos em validar(). */
	private String dialogCotaTitulo;
	private String dialogCotaMensagem;

	@Inject
	private AssuntoDAO assuntoDAO;

	@Inject
	private ImagemPostBean imagemPostBean;

	@Inject
	private MontadorPostService montadorService;

	@Inject
	private FilaGeracaoPostService filaGeracaoService;

	@Inject
	private ControleAcessoBean controleAcessoBean;

	@Inject
	private SessaoBean sessaoBean;

	@Inject
	private CreditoPostService creditoPostService;

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
		novoItem();
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
		Usuario usuario = sessaoBean.getUsuario();
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
				Usuario usuario = sessaoBean.getUsuario();
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

	private void novoItem()
	{
		item = new ItemPedidoPost();
		item.setFormato(FormatoPost.Feed);
		item.setQuantidade(1);
		// Já deixa um nível e um assunto selecionados para não adicionar item incompleto.
		item.getNiveis().add(Nivel.Nivel1);
		if(assuntos != null && !assuntos.isEmpty())
			item.setAssunto(assuntos.get(0));
	}

	// ── Itens (formulário inline) ─────────────────────────────────────

	/** Abre o formulário inline em modo de adição. */
	public void cadastrarItem()
	{
		cadastroItem = true;
		editandoItem = true;
		itemOriginal = null;
		novoItem();
	}

	/** Abre o formulário inline em modo de edição, carregando uma cópia do item selecionado. */
	public void editarItem(ItemPedidoPost alvo)
	{
		cadastroItem = false;
		editandoItem = true;
		itemOriginal = alvo;
		item = alvo.copia();
	}

	/** Fecha o formulário inline sem salvar. */
	public void cancelarItem()
	{
		editandoItem = false;
		itemOriginal = null;
		novoItem();
	}

	/** Confirma: adiciona um novo item ou aplica a edição no item original, e fecha o formulário. */
	public void salvarItem()
	{
		if(cadastroItem)
		{
			item.setPedidoPost(entidade);
			item.setOrdem(entidade.getItens().size());
			entidade.getItens().add(item);
		}
		else if(itemOriginal != null)
		{
			itemOriginal.aplicarEdicao(item);
		}
		editandoItem = false;
		itemOriginal = null;
		novoItem();
	}

	/** Duplica um item da lista. */
	public void duplicarItem(ItemPedidoPost alvo)
	{
		ItemPedidoPost copia = alvo.copia();
		copia.setPedidoPost(entidade);
		copia.setOrdem(entidade.getItens().size());
		entidade.getItens().add(copia);
	}

	public void removerItem(ItemPedidoPost alvo)
	{
		entidade.getItens().removeIf(i -> i == alvo);
		reordenar();
	}

	private void reordenar()
	{
		List<ItemPedidoPost> itens = entidade.getItens();
		for(int i = 0; i < itens.size(); i++)
			itens.get(i).setOrdem(i);
	}

	public int totalCreditos()
	{
		return entidade.getItens().stream().mapToInt(ItemPedidoPost::getCreditos).sum();
	}

	// ── Solicitação ───────────────────────────────────────────────────

	public String solicitar()
	{
		if(!controleAcessoBean.verificaEstaLogado())
			return null;

		if(!validar())
			return null;

		prepararPedido();
		entidade.setCodigoBatch(MontadorPostService.gerarCodigoBatch());
		entidade.setDataSolicitacao(LocalDateTime.now());
		entidade.setStatus(StatusPedidoPost.AGUARDANDO);

		entidadeDAO.salvar(entidade);
		filaGeracaoService.enfileirar(entidade.getId());

		Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_INFO,
			"Geração iniciada! Código: " + entidade.getCodigoBatch() + ". Acompanhe o progresso na lista.");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("postNovoCodigo", entidade.getCodigoBatch());

		return urlLista + "?faces-redirect=true";
	}

	private boolean validar()
	{
		Usuario usuario = sessaoBean.getUsuario();

		if(usuario == null || usuario.getConfigPost() == null)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR,
				"Configure seu perfil de posts (logo, cores) antes de gerar conteúdo.");
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
		Usuario usuario = sessaoBean.getUsuario();
		if(usuario == null)
			return false;
		return !usuario.getPerfilCriador().isRenovavel();
	}

	public int trialRestante()
	{
		return creditoPostService.creditosRestantes(sessaoBean.getUsuario(), planoAtual());
	}

	/** Salva o pedido como rascunho (sem gerar), para o usuário continuar a configuração depois. */
	public String salvarRascunho()
	{
		if(!controleAcessoBean.verificaEstaLogado())
			return null;

		prepararPedido();
		entidade.setStatus(StatusPedidoPost.RASCUNHO);

		entidade = entidadeDAO.salvar(entidade);

		Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_INFO,
			"Rascunho salvo. Gere quando terminar de configurar.");
		return urlLista + "?faces-redirect=true";
	}

	/** Gera um rascunho salvo direto do histórico (valida cota e dispara a geração). */
	public String gerarRascunho(PedidoPost rascunho)
	{
		entidade = rascunho;
		return solicitar();
	}

	/**
	 * Preenche os campos comuns antes de salvar ou solicitar: usuário, configPost, quantidade,
	 * progresso, e garante codigoBatch e dataSolicitacao quando ainda não definidos.
	 */
	private void prepararPedido()
	{
		Usuario usuario = sessaoBean.getUsuario();
		entidade.setUsuario(usuario);
		entidade.setConfigPost(usuario.getConfigPost());
		entidade.setQuantidade(totalCreditos());
		entidade.setProgresso(0);
		if(entidade.getCodigoBatch() == null)
			entidade.setCodigoBatch(MontadorPostService.gerarCodigoBatch());
		if(entidade.getDataSolicitacao() == null)
			entidade.setDataSolicitacao(LocalDateTime.now());
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
		Usuario usuario = sessaoBean.getUsuario();
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
		Usuario usuario = sessaoBean.getUsuario();
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
		return creditoPostService.creditosRestantes(sessaoBean.getUsuario(), planoAtual());
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

	// ── Selects ───────────────────────────────────────────────────────

	public FormatoPost[] getFormatos()
	{
		return FormatoPost.values();
	}

	public modelo.exercicio.Nivel[] getNiveis()
	{
		return modelo.exercicio.Nivel.values();
	}

	/** Qtd de imagens personalizadas do formato em edição (habilita a opção "Personalizada"). */
	public int getQtdImagensPersonalizadas()
	{
		boolean feed = item != null && item.getFormato() == FormatoPost.Feed;
		return imagemPostBean.imagemPost(feed).size();
	}

	/**
	 * Em "Específica", garante que haja sempre uma imagem do pool atual selecionada
	 * (evita adicionar item sem imagem). Em "Aleatória", a imagem é sorteada na geração.
	 */
	public void ajustarImagemFundo()
	{
		if(item == null || item.isBackgroundAleatorio())
			return;

		boolean feed = item.getFormato() == FormatoPost.Feed;
		if(item.isBasePadrao())
		{
			if(item.getPadrao() == null)
			{
				List<Background> padroes = feed ? imagemPostBean.getImagensFeed() : imagemPostBean.getImagensReel();
				if(padroes != null && !padroes.isEmpty())
					item.setPadrao(padroes.get(0));
			}
		}
		else
		{
			if(item.getBackground() == null)
			{
				List<ImagemPost> personalizadas = imagemPostBean.imagemPost(feed);
				if(personalizadas != null && !personalizadas.isEmpty())
					item.setBackground(personalizadas.get(0));
			}
		}
	}

	/** Ao trocar o formato, a imagem específica é de outro pool (Feed x Reel): limpa e re-seleciona. */
	public void mudarFormatoItem()
	{
		if(item == null)
			return;
		item.setBackground(null);
		item.setPadrao(null);
		ajustarImagemFundo();
	}

	// ── Utilitários ───────────────────────────────────────────────────

	private void carregarHistorico()
	{
		historico = entidadeDAO.buscarPorUsuario(sessaoBean.getUsuario());
	}

	private void calcularUso()
	{
		LocalDateTime inicioMes = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
		LocalDateTime inicioProximo = inicioMes.plusMonths(1);
		postsUsadosNoMes = entidadeDAO.somarPostsNoMes(sessaoBean.getUsuario(), inicioMes, inicioProximo);
		postsUsadosTotal = entidadeDAO.somarPostsTotal(sessaoBean.getUsuario());
	}
}
