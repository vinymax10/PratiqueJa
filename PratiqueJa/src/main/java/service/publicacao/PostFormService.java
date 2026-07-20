package service.publicacao;

import java.io.Serializable;
import java.time.LocalDateTime;

import dao.publicacao.PedidoPostDAO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import modelo.publicacao.ItemPedidoPost;
import modelo.publicacao.PedidoPost;
import modelo.publicacao.StatusPedidoPost;
import modelo.usuario.Usuario;

/**
 * Regras de persistência do pedido de post, extraídas do {@code PedidoPostBean} (que deve cuidar só de
 * view/navegação/mensagens/validação). Espelha o {@code AvaliacaoFormService}: prepara, persiste e
 * enfileira o pedido; o bean só valida a cota e mostra o growl.
 */
@ApplicationScoped
public class PostFormService implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private PedidoPostDAO pedidoDAO;

	@Inject
	private FilaGeracaoPostService filaGeracaoService;

	/** Prepara e persiste o pedido para geração (status AGUARDANDO) e o coloca na fila. */
	public void solicitarGeracao(PedidoPost pedido, Usuario usuario)
	{
		prepararPedido(pedido, usuario);
		pedido.setStatus(StatusPedidoPost.AGUARDANDO);

		pedidoDAO.salvar(pedido);
		filaGeracaoService.enfileirar(pedido.getId());
	}

	/** Prepara e persiste o pedido como rascunho (sem gerar). */
	public PedidoPost salvarRascunho(PedidoPost pedido, Usuario usuario)
	{
		prepararPedido(pedido, usuario);
		pedido.setStatus(StatusPedidoPost.RASCUNHO);
		return pedidoDAO.salvar(pedido);
	}

	/**
	 * Campos comuns antes de salvar/solicitar: usuário, configPost, quantidade (soma dos créditos dos
	 * itens), progresso, e garante codigoBatch e dataSolicitacao quando ainda não definidos.
	 */
	private void prepararPedido(PedidoPost pedido, Usuario usuario)
	{
		pedido.setUsuario(usuario);
		pedido.setConfigPost(usuario.getConfigPost());
		pedido.setQuantidade(pedido.getItens().stream().mapToInt(ItemPedidoPost::getCreditos).sum());
		pedido.setProgresso(0);
		if(pedido.getCodigoBatch() == null)
			pedido.setCodigoBatch(MontadorPostService.gerarCodigoBatch());
		if(pedido.getDataSolicitacao() == null)
			pedido.setDataSolicitacao(LocalDateTime.now());
	}
}
