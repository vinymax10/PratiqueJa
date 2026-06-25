package bean.publicacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import modelo.publicacao.ConfigPost;
import modelo.publicacao.FormatoPedidoPost;
import modelo.publicacao.ItemPedidoPost;
import modelo.publicacao.PedidoPost;
import modelo.publicacao.PerfilCriador;
import modelo.publicacao.StatusPedidoPost;
import modelo.seguranca.PermissaoPadrao;
import modelo.usuario.PerfilUsuario;
import modelo.usuario.Usuario;
import service.publicacao.MontadorPostService;
import web.session.Sessao;

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

	/** Item em edição no modal (assunto + formato + quantidade). */
	private ItemPedidoPost item;

	/** true = modal em modo de adição; false = edição de um item existente. */
	private boolean cadastroItem = true;

	/** Item da lista sendo editado (quando cadastroItem = false). */
	private ItemPedidoPost itemOriginal;

	private int postsUsadosNoMes;
	private int postsUsadosTotal;

	@Inject
	private AssuntoDAO assuntoDAO;

	@Inject
	private MontadorPostService montadorService;

	@Inject
	private ControleAcessoBean controleAcessoBean;

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
		Usuario usuario = getUsuarioLogado();
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
				Usuario usuario = getUsuarioLogado();
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
		item.setFormato(FormatoPedidoPost.AMBOS);
		item.setQuantidade(1);
	}

	// ── Itens (modal) ─────────────────────────────────────────────────

	/** Abre o modal em modo de adição. */
	public void cadastrarItem()
	{
		cadastroItem = true;
		itemOriginal = null;
		novoItem();
	}

	/** Abre o modal em modo de edição, carregando uma cópia do item selecionado. */
	public void editarItem(ItemPedidoPost alvo)
	{
		cadastroItem = false;
		itemOriginal = alvo;
		item = alvo.copia();
	}

	/** Confirma o modal: adiciona um novo item ou aplica a edição no item original. */
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
			itemOriginal.setAssunto(item.getAssunto());
			itemOriginal.setFormato(item.getFormato());
			itemOriginal.setQuantidade(item.getQuantidade());
		}
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

		entidade.setCodigoBatch(MontadorPostService.gerarCodigoBatch());
		entidade.setStatus(StatusPedidoPost.AGUARDANDO);
		entidade.setProgresso(0);
		entidade.setDataSolicitacao(LocalDateTime.now());
		entidade.setQuantidade(totalCreditos());

		Usuario usuario = getUsuarioLogado();
		entidade.setUsuario(usuario);
		entidade.setConfigPost(usuario.getConfigPost());

		entidadeDAO.salvar(entidade);
		montadorService.montar(entidade.getId());

		Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_INFO,
			"Geração iniciada! Código: " + entidade.getCodigoBatch() + ". Acompanhe o progresso na lista.");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("postNovoCodigo", entidade.getCodigoBatch());

		return urlLista + "?faces-redirect=true";
	}

	private boolean validar()
	{
		Usuario usuario = getUsuarioLogado();

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

		if(usuario.getPerfil() == PerfilUsuario.Admin)
			return true;

		// Sem plano ativo: usa a cota grátis de teste (vitalícia), sem cartão.
		if(isModoTrial())
		{
			int trialRestante = trialRestante();
			if(trialRestante <= 0)
			{
				Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Seus " + LIMITE_TRIAL_GRATIS
					+ " posts grátis acabaram. Assine um plano para continuar gerando conteúdo.");
				return false;
			}
			if(total > trialRestante)
			{
				Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Cota grátis insuficiente. Restam "
					+ trialRestante + " posts de teste. Assine um plano para gerar mais.");
				return false;
			}
			return true;
		}

		// Plano ativo: cota mensal = créditos do plano + rollover − usados no mês.
		int restante = creditosRestantes();
		if(total > restante)
		{
			String extra = usuario.getCreditoRolloverPost() > 0
				? " (já incluído o crédito acumulado de " + usuario.getCreditoRolloverPost() + ")"
				: "";
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Créditos insuficientes. Restam "
				+ Math.max(0, restante) + " posts neste mês" + extra + ".");
			return false;
		}

		return true;
	}

	/** Usuário em teste grátis: logado, fora do perfil Admin e sem plano de conteúdo ativo. */
	public boolean isModoTrial()
	{
		Usuario usuario = getUsuarioLogado();
		if(usuario == null || usuario.getPerfil() == PerfilUsuario.Admin)
			return false;
		return usuario.getValidadePlano() == null || usuario.getValidadePlano().isBefore(LocalDate.now());
	}

	public int trialRestante()
	{
		return Math.max(0, LIMITE_TRIAL_GRATIS - postsUsadosTotal);
	}

	/** Salva o pedido como rascunho (sem gerar), para o usuário continuar a configuração depois. */
	public String salvarRascunho()
	{
		if(!controleAcessoBean.verificaEstaLogado())
			return null;

		Usuario usuario = getUsuarioLogado();
		entidade.setUsuario(usuario);
		entidade.setConfigPost(usuario.getConfigPost());
		entidade.setStatus(StatusPedidoPost.RASCUNHO);
		entidade.setProgresso(0);
		entidade.setQuantidade(totalCreditos());
		if(entidade.getCodigoBatch() == null)
			entidade.setCodigoBatch(MontadorPostService.gerarCodigoBatch());
		if(entidade.getDataSolicitacao() == null)
			entidade.setDataSolicitacao(LocalDateTime.now());

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

	// ── Progresso / download ──────────────────────────────────────────

	public void atualizarProgresso()
	{
		if(historico == null)
			return;
		for(PedidoPost p : historico)
		{
			if(p.getStatus() == StatusPedidoPost.GERANDO || p.getStatus() == StatusPedidoPost.AGUARDANDO)
			{
				PedidoPost atualizado = entidadeDAO.carrega(p.getId());
				p.setProgresso(atualizado.getProgresso());
				p.setStatus(atualizado.getStatus());
				p.setCaminhoArquivo(atualizado.getCaminhoArquivo());
				p.setNomeDownload(atualizado.getNomeDownload());
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

	public org.primefaces.model.DefaultStreamedContent download(PedidoPost p) throws java.io.IOException
	{
		if(p.getCaminhoArquivo() == null)
			return null;

		java.nio.file.Path arquivo = java.nio.file.Path.of(p.getCaminhoArquivo());
		byte[] bytes = java.nio.file.Files.readAllBytes(arquivo);

		return org.primefaces.model.DefaultStreamedContent.builder()
			.name(p.getNomeDownload()).contentType("application/zip")
			.stream(() -> new java.io.ByteArrayInputStream(bytes)).build();
	}

	// ── Cota / créditos ───────────────────────────────────────────────

	private PerfilCriador planoAtual()
	{
		Usuario usuario = getUsuarioLogado();
		ConfigPost configPost = usuario != null ? usuario.getConfigPost() : null;
		return configPost != null ? configPost.getPerfilCriador() : PerfilCriador.Basico;
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
		Usuario usuario = getUsuarioLogado();
		int rollover = usuario != null ? usuario.getCreditoRolloverPost() : 0;
		return getCreditosMensais() + rollover;
	}

	public int creditosRestantes()
	{
		return cotaDisponivel() - postsUsadosNoMes;
	}

	public String descricaoPlano()
	{
		if(isModoTrial())
			return "Teste grátis (" + trialRestante() + " de " + LIMITE_TRIAL_GRATIS + " restantes)";
		return planoAtual().getNome() + " (" + getCreditosMensais() + " posts/mês)";
	}

	public int getLimiteTrialGratis()
	{
		return LIMITE_TRIAL_GRATIS;
	}

	// ── Selects ───────────────────────────────────────────────────────

	public FormatoPedidoPost[] getFormatos()
	{
		return FormatoPedidoPost.values();
	}

	public modelo.exercicio.Nivel[] getNiveis()
	{
		return modelo.exercicio.Nivel.values();
	}

	// ── Utilitários ───────────────────────────────────────────────────

	private void carregarHistorico()
	{
		historico = entidadeDAO.buscarPorUsuario(getUsuarioLogado());
	}

	private void calcularUso()
	{
		LocalDateTime inicioMes = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
		LocalDateTime inicioProximo = inicioMes.plusMonths(1);
		postsUsadosNoMes = entidadeDAO.somarPostsNoMes(getUsuarioLogado(), inicioMes, inicioProximo);
		postsUsadosTotal = entidadeDAO.somarPostsTotal(getUsuarioLogado());
	}

	private Usuario getUsuarioLogado()
	{
		return Sessao.getUsuarioLogado();
	}
}
