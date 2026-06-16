package bean.avaliacao;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.util.Mensagem;
import dao.academico.AssuntoDAO;
import dao.avaliacao.ConfigPedidoAvaliacaoDAO;
import dao.avaliacao.ItemPedidoAvaliacaoDAO;
import dao.avaliacao.PedidoAvaliacaoDAO;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
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
import modelo.usuario.PerfilUsuario;
import modelo.usuario.Usuario;
import service.avaliacao.MontadorPedidoAvaliacaoService;
import web.session.Sessao;

@Data
@Named
@ViewScoped
public class PedidoAvaliacaoBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(PedidoAvaliacaoBean.class);

	// ── Estado da view ────────────────────────────────────────────────

	private PedidoAvaliacao pedido;
	private ItemPedidoAvaliacao novoItem;
	private ItemPedidoAvaliacao itemEditando;
	private List<PedidoAvaliacao> historico;

	private List<Assunto> assuntos;
	private ConfigPedidoAvaliacao config;

	private int avaliacoesUsadasNoMes;

	// ── Injeções ──────────────────────────────────────────────────────

	@Inject
	private PedidoAvaliacaoDAO pedidoAvaliacaoDAO;

	@Inject
	private ItemPedidoAvaliacaoDAO itemPedidoAvaliacaoDAO;

	@Inject
	private ConfigPedidoAvaliacaoDAO configPedidoAvaliacaoDAO;

	@Inject
	private AssuntoDAO assuntoDAO;

	@Inject
	private MontadorPedidoAvaliacaoService montadorService;

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
	}

	private void novoPedido()
	{
		pedido = new PedidoAvaliacao();
		pedido.setUsuario(getUsuarioLogado());
		novoItem = new ItemPedidoAvaliacao();
	}

	// ── Itens da avaliação ────────────────────────────────────────────

	public void prepararNovoItem()
	{
		itemEditando = null;
		novoItem = new ItemPedidoAvaliacao();
	}

	public void prepararEdicaoItem(ItemPedidoAvaliacao item)
	{
		itemEditando = item;
		novoItem = new ItemPedidoAvaliacao();
		novoItem.setAssunto(item.getAssunto());
		novoItem.setNivel(item.getNivel());
		novoItem.setFormato(item.getFormato());
		novoItem.setQuantidade(item.getQuantidade());
	}

	public boolean isEditando()
	{
		return itemEditando != null;
	}

	public void salvarItem()
	{
		int qtdAtualDoItem = itemEditando != null ? itemEditando.getQuantidade() : 0;
		if(totalExercicios() - qtdAtualDoItem + novoItem.getQuantidade() > config.getMaxExerciciosPorAvaliacao())
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_WARN, "Limite de " + config.getMaxExerciciosPorAvaliacao() + " exercícios por avaliação atingido.");
			return;
		}

		if(itemEditando != null)
		{
			itemEditando.setAssunto(novoItem.getAssunto());
			itemEditando.setNivel(novoItem.getNivel());
			itemEditando.setFormato(novoItem.getFormato());
			itemEditando.setQuantidade(novoItem.getQuantidade());
			itemEditando = null;
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Assunto atualizado.");
		}
		else
		{
			novoItem.setPedidoAvaliacao(pedido);
			novoItem.setOrdem(pedido.getItens().size() + 1);
			pedido.getItens().add(novoItem);
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Assunto adicionado.");
		}
		novoItem = new ItemPedidoAvaliacao();
	}

	public void removerItem(ItemPedidoAvaliacao item)
	{
		int idx = indiceDoItem(item);
		if(idx >= 0)
		{
			pedido.getItens().remove(idx);
			reordenar();
		}
	}

	public void subirItem(ItemPedidoAvaliacao item)
	{
		int idx = indiceDoItem(item);
		if(idx > 0)
		{
			List<ItemPedidoAvaliacao> itens = pedido.getItens();
			itens.remove(idx);
			itens.add(idx - 1, item);
			reordenar();
		}
	}

	public void descerItem(ItemPedidoAvaliacao item)
	{
		List<ItemPedidoAvaliacao> itens = pedido.getItens();
		int idx = indiceDoItem(item);
		if(idx >= 0 && idx < itens.size() - 1)
		{
			itens.remove(idx);
			itens.add(idx + 1, item);
			reordenar();
		}
	}

	private int indiceDoItem(ItemPedidoAvaliacao item)
	{
		List<ItemPedidoAvaliacao> itens = pedido.getItens();
		for(int i = 0; i < itens.size(); i++)
		{
			if(itens.get(i) == item)
				return i;
		}
		return -1;
	}

	private void reordenar()
	{
		List<ItemPedidoAvaliacao> itens = pedido.getItens();
		for(int i = 0; i < itens.size(); i++)
			itens.get(i).setOrdem(i + 1);
	}

	// ── Validação e solicitação ───────────────────────────────────────

	public void solicitar()
	{
		if(!validar())
			return;

		pedido.setCodigoBatch(MontadorPedidoAvaliacaoService.gerarCodigoBatch());
		pedido.setStatus(StatusPedidoAvaliacao.AGUARDANDO);
		pedido.setProgresso(0);
		pedido.setDataSolicitacao(LocalDateTime.now());
		pedido.setDataExpiracao(LocalDateTime.now().plusDays(config.getDiasRetencaoPdf()));
		pedido.setUsuario(getUsuarioLogado());

		pedidoAvaliacaoDAO.salvar(pedido);
		montadorService.montar(pedido.getId());

		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Solicitação registrada! Código: " + pedido.getCodigoBatch() + ". Acompanhe o progresso abaixo.");

		carregarHistorico();
		calcularUsoMensal();
		novoPedido();
	}

	private boolean validar()
	{
		if(pedido.getTitulo() == null || pedido.getTitulo().isBlank())
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Informe o título da avaliação.");
			return false;
		}

		if(pedido.getItens().isEmpty())
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Adicione ao menos um assunto.");
			return false;
		}

		if(pedido.getQuantidade() < 1 || pedido.getQuantidade() > config.getMaxAvaliacoesPorSolicitacao())
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
		if(pedido.getQuantidade() > limiteRestante)
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
				PedidoAvaliacao atualizado = pedidoAvaliacaoDAO.carrega(p.getId());
				p.setProgresso(atualizado.getProgresso());
				p.setStatus(atualizado.getStatus());
				p.setCaminhoArquivo(atualizado.getCaminhoArquivo());
				p.setNomeDownload(atualizado.getNomeDownload());
			}
		}
	}

	public List<PedidoAvaliacao> getPedidosEmAndamento()
	{
		if(historico == null)
			return java.util.Collections.emptyList();
		return historico.stream()
			.filter(p -> p.getStatus() == StatusPedidoAvaliacao.GERANDO
			          || p.getStatus() == StatusPedidoAvaliacao.AGUARDANDO)
			.toList();
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
		return pedido.getItens().stream().mapToInt(ItemPedidoAvaliacao::getQuantidade).sum();
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
		historico = pedidoAvaliacaoDAO.buscarPorUsuario(getUsuarioLogado());
	}

	private void calcularUsoMensal()
	{
		LocalDateTime inicioMes = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
		LocalDateTime inicioProximo = inicioMes.plusMonths(1);
		avaliacoesUsadasNoMes = pedidoAvaliacaoDAO.somarAvaliacoesNoMes(getUsuarioLogado(), inicioMes, inicioProximo);
	}

	private Usuario getUsuarioLogado()
	{
		return Sessao.getUsuarioLogado();
	}
}
