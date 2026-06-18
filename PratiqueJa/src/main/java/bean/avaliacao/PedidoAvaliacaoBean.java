package bean.avaliacao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.PaiBean;
import bean.usuario.ControleAcessoBean;
import bean.util.Mensagem;
import dao.academico.AssuntoDAO;
import dao.avaliacao.ConfigPedidoAvaliacaoDAO;
import dao.avaliacao.PedidoAvaliacaoDAO;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.academico.Assunto;
import modelo.avaliacao.ConfigPedidoAvaliacao;
import modelo.avaliacao.FormatoAvaliacao;
import modelo.avaliacao.FormatoSaida;
import modelo.avaliacao.ItemPedidoAvaliacao;
import modelo.avaliacao.NomeDocumento;
import modelo.avaliacao.PedidoAvaliacao;
import modelo.avaliacao.PlanoAvaliacao;
import modelo.avaliacao.PosicaoGabarito;
import modelo.avaliacao.StatusPedidoAvaliacao;
import modelo.avaliacao.TipoGabarito;
import modelo.exercicio.Nivel;
import modelo.seguranca.PermissaoPadrao;
import modelo.usuario.PerfilUsuario;
import modelo.usuario.Usuario;
import service.avaliacao.MontadorPedidoAvaliacaoService;
import web.session.Sessao;

@Data
@EqualsAndHashCode(callSuper = false)
@Named
@ViewScoped
public class PedidoAvaliacaoBean extends PaiBean<PedidoAvaliacao, PedidoAvaliacaoDAO, PermissaoPadrao<PedidoAvaliacao>>
{
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(PedidoAvaliacaoBean.class);

	// ── Estado da view ────────────────────────────────────────────────

	private List<PedidoAvaliacao> historico;
	private PedidoAvaliacao pedidoSelecionado;

	/** Código do pedido recém-criado, para realçar a linha na list (vem do flash após o redirect). */
	private String codigoDestaque;

	private List<Assunto> assuntos;
	private ConfigPedidoAvaliacao config;

	private int avaliacoesUsadasNoMes;

	// ── Injeções ──────────────────────────────────────────────────────

	@Inject
	private ConfigPedidoAvaliacaoDAO configPedidoAvaliacaoDAO;

	@Inject
	private AssuntoDAO assuntoDAO;

	@Inject
	private MontadorPedidoAvaliacaoService montadorService;

	@Inject
	private ControleAcessoBean controleAcessoBean;

	public PedidoAvaliacaoBean()
	{
		super(PedidoAvaliacao.class, "Avaliação");
		urlLista = "/avaliacao/list.xhtml";
		urlCadastro = "/avaliacao/form.xhtml";
	}

	// ── Inicialização ─────────────────────────────────────────────────

	@PostConstruct
	public void init()
	{
		historico = new ArrayList<>();
		avaliacoesUsadasNoMes = 0;
		try
		{
			config = configPedidoAvaliacaoDAO.buscarOuCriarConfig();
		}
		catch(Exception e)
		{
			LOG.error("Falha ao carregar config de avaliação", e);
			config = new ConfigPedidoAvaliacao();
		}
		try
		{
			assuntos = assuntoDAO.listarOpcoesAtivas();
		}
		catch(Exception e)
		{
			LOG.error("Falha ao listar assuntos", e);
			assuntos = new ArrayList<>();
		}
		novoPedido();
		try
		{
			carregarHistorico();
		}
		catch(Exception e)
		{
			LOG.error("Falha ao carregar histórico de pedidos", e);
		}
		try
		{
			calcularUsoMensal();
		}
		catch(Exception e)
		{
			LOG.error("Falha ao calcular uso mensal", e);
		}
		try
		{
			Object novo = FacesContext.getCurrentInstance().getExternalContext().getFlash().get("avNovoCodigo");
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
		entidade = new PedidoAvaliacao();
		entidade.setUsuario(getUsuarioLogado());
	}

	// ── Validação e solicitação ───────────────────────────────────────

	public String solicitar()
	{
		// Não logado: abre a tela de login (igual ao fluxo de responder questão sem login).
		// Após logar, o usuário clica em Solicitar novamente.
		if(!controleAcessoBean.verificaEstaLogado())
			return null;

		if(!validar())
			return null;

		entidade.setCodigoBatch(MontadorPedidoAvaliacaoService.gerarCodigoBatch());
		entidade.setStatus(StatusPedidoAvaliacao.AGUARDANDO);
		entidade.setProgresso(0);
		entidade.setDataSolicitacao(LocalDateTime.now());
		entidade.setDataExpiracao(LocalDateTime.now().plusDays(config.getDiasRetencaoPdf()));
		entidade.setUsuario(getUsuarioLogado());

		entidadeDAO.salvar(entidade);
		montadorService.montar(entidade.getId());

		Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_INFO, "Solicitação registrada! Código: " + entidade.getCodigoBatch() + ". Acompanhe o progresso na lista.");

		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("avNovoCodigo", entidade.getCodigoBatch());

		// Redirect ajax-safe via outcome (mantém a mensagem no flash até a list)
		return urlLista + "?faces-redirect=true";
	}

	private boolean validar()
	{
		if(entidade.getTitulo() == null || entidade.getTitulo().isBlank())
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Informe o título da avaliação.");
			return false;
		}

		if(entidade.getItens().isEmpty())
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Adicione ao menos um assunto.");
			return false;
		}

		if(entidade.getQuantidade() < 1 || entidade.getQuantidade() > config.getMaxAvaliacoesPorSolicitacao())
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Quantidade deve ser entre 1 e " + config.getMaxAvaliacoesPorSolicitacao() + ".");
			return false;
		}

		Usuario usuario = getUsuarioLogado();
		if(usuario.getPerfil() == PerfilUsuario.Admin)
			return true;

		PlanoAvaliacao plano = usuario.getPlanoAvaliacao();
		if(plano == null)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Você não possui um plano ativo para confecção de avaliações.");
			return false;
		}

		int limiteRestante = plano.getLimiteMensal() - avaliacoesUsadasNoMes;
		if(entidade.getQuantidade() > limiteRestante)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Cota mensal insuficiente. Restam " + limiteRestante + " avaliações neste mês.");
			return false;
		}

		return true;
	}

	// ── Progresso ─────────────────────────────────────────────────────

	public void atualizarProgresso()
	{
		if(historico == null)
			return;
		for(PedidoAvaliacao p : historico)
		{
			if(p.getStatus() == StatusPedidoAvaliacao.GERANDO
			|| p.getStatus() == StatusPedidoAvaliacao.AGUARDANDO)
			{
				PedidoAvaliacao atualizado = entidadeDAO.carrega(p.getId());
				p.setProgresso(atualizado.getProgresso());
				p.setStatus(atualizado.getStatus());
				p.setCaminhoArquivo(atualizado.getCaminhoArquivo());
				p.setNomeDownload(atualizado.getNomeDownload());
			}
		}
	}

	// ── Detalhes da solicitação ───────────────────────────────────────

	public void aoSelecionarPedido(org.primefaces.event.SelectEvent<PedidoAvaliacao> event)
	{
		pedidoSelecionado = event.getObject();
	}

	// ── Download ──────────────────────────────────────────────────────

	public org.primefaces.model.DefaultStreamedContent download(PedidoAvaliacao p) throws java.io.IOException
	{
		if(p.getCaminhoArquivo() == null)
			return null;

		java.nio.file.Path arquivo = java.nio.file.Path.of(p.getCaminhoArquivo());
		byte[] bytes = java.nio.file.Files.readAllBytes(arquivo);

		String mime = p.getNomeDownload() != null && p.getNomeDownload().endsWith(".zip") ? "application/zip" : "application/pdf";

		return org.primefaces.model.DefaultStreamedContent.builder().name(p.getNomeDownload()).contentType(mime)
		.stream(() -> new java.io.ByteArrayInputStream(bytes)).build();
	}

	// ── Cálculos auxiliares ───────────────────────────────────────────

	public int totalExercicios()
	{
		return entidade.getItens().stream().mapToInt(ItemPedidoAvaliacao::getQuantidade).sum();
	}

	public int limiteExerciciosRestante()
	{
		return config.getMaxExerciciosPorAvaliacao() - totalExercicios();
	}

	public int limiteMensalRestante()
	{
		PlanoAvaliacao plano = getUsuarioLogado().getPlanoAvaliacao();
		if(plano == null)
			return 0;
		return plano.getLimiteMensal() - avaliacoesUsadasNoMes;
	}

	public String descricaoPlano()
	{
		PlanoAvaliacao plano = getUsuarioLogado().getPlanoAvaliacao();
		if(plano == null)
			return "Sem plano";
		return plano.getNome() + " (" + plano.getLimiteMensal() + " avaliações/mês)";
	}

	public boolean temGerandoEmProgresso()
	{
		if (historico == null) return false;
		return historico.stream().anyMatch(p -> p.getStatus() == StatusPedidoAvaliacao.GERANDO
		                                    || p.getStatus() == StatusPedidoAvaliacao.AGUARDANDO);
	}

	public boolean estaExpirado(PedidoAvaliacao p)
	{
		return p.getDataExpiracao() != null && p.getDataExpiracao().isBefore(LocalDateTime.now());
	}

	// ── Dados para selects ────────────────────────────────────────────

	public NomeDocumento[] getNomesDocumento()
	{
		return NomeDocumento.values();
	}

	public FormatoAvaliacao[] getFormatosAvaliacao()
	{
		return FormatoAvaliacao.values();
	}

	public TipoGabarito[] getTiposGabarito()
	{
		return TipoGabarito.values();
	}

	public PosicaoGabarito[] getPosicoes()
	{
		return PosicaoGabarito.values();
	}

	public FormatoSaida[] getFormatosSaida()
	{
		return FormatoSaida.values();
	}

	public Nivel[] getNiveis()
	{
		return Nivel.values();
	}

	// ── Utilitários privados ──────────────────────────────────────────

	private void carregarHistorico()
	{
		historico = entidadeDAO.buscarPorUsuario(getUsuarioLogado());
	}

	private void calcularUsoMensal()
	{
		LocalDateTime inicioMes = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
		LocalDateTime inicioProximo = inicioMes.plusMonths(1);
		avaliacoesUsadasNoMes = entidadeDAO.somarAvaliacoesNoMes(getUsuarioLogado(), inicioMes, inicioProximo);
	}

	private Usuario getUsuarioLogado()
	{
		return Sessao.getUsuarioLogado();
	}
}
