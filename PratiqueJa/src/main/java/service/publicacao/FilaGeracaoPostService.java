package service.publicacao;

import java.io.Serializable;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.ConcurrencyManagement;
import jakarta.ejb.ConcurrencyManagementType;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;

import dao.publicacao.PedidoPostDAO;
import modelo.publicacao.PedidoPost;

/**
 * Fila única de geração de posts para toda a aplicação: não importa quantos usuários cliquem
 * em "gerar" ao mesmo tempo, apenas um {@code PedidoPost} é montado (imagens + legendas) por
 * vez — os demais aguardam em ordem de chegada (FIFO), evitando que picos de acesso sobrecarreguem
 * CPU/memória/conexões do servidor. Mesma estratégia usada em {@code FilaGeracaoAvaliacaoService}.
 */
@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class FilaGeracaoPostService implements Serializable
{
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(FilaGeracaoPostService.class.getName());

	@Inject
	private GeracaoPostWorkerService worker;

	@Inject
	private PedidoPostDAO pedidoPostDAO;

	private final Queue<Long> fila = new ConcurrentLinkedQueue<>();

	/** true enquanto o worker está consumindo a fila; garante processamento de um pedido por vez. */
	private final AtomicBoolean processando = new AtomicBoolean(false);

	/** Reenfileira pedidos que ficaram parados em AGUARDANDO/GERANDO após um restart do servidor
	 *  (a fila em memória se perde no restart, o status no banco não). */
	@PostConstruct
	public void recuperarPendentes()
	{
		List<PedidoPost> pendentes = pedidoPostDAO.buscarPendentes();
		for(PedidoPost pedido : pendentes)
			fila.add(pedido.getId());

		if(!pendentes.isEmpty())
		{
			logger.info(pendentes.size() + " pedido(s) de posts recuperado(s) na fila após início da aplicação.");
			disparar();
		}
	}

	/** Adiciona o pedido ao final da fila única e dispara o processamento se estiver ocioso. */
	public void enfileirar(Long pedidoId)
	{
		fila.add(pedidoId);
		disparar();
	}

	/** Posição do pedido na fila (1 = próximo a ser processado), ou 0 se não estiver mais esperando
	 *  (já em processamento, ou concluído). */
	public int posicaoNaFila(Long pedidoId)
	{
		int posicao = 0;
		for(Long id : fila)
		{
			posicao++;
			if(id.equals(pedidoId))
				return posicao;
		}
		return 0;
	}

	private void disparar()
	{
		if(processando.compareAndSet(false, true))
			worker.processar(this);
	}

	/** Chamado só pelo worker: retira o próximo pedido da fila, ou null se estiver vazia. */
	Long proximoDaFila()
	{
		return fila.poll();
	}

	/** Chamado só pelo worker ao terminar: libera o processamento e relança caso algo tenha
	 *  sido enfileirado na janela entre o último poll() vazio e esta chamada. */
	void marcarOcioso()
	{
		processando.set(false);
		if(!fila.isEmpty())
			disparar();
	}
}
