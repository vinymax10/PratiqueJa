package service.avaliacao;

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

import dao.avaliacao.PedidoAvaliacaoDAO;
import modelo.avaliacao.PedidoAvaliacao;

/**
 * Fila única de geração de avaliações para toda a aplicação: não importa quantos usuários
 * cliquem em "gerar" ao mesmo tempo, apenas um {@code PedidoAvaliacao} é montado (PDFs via
 * xelatex) por vez — os demais aguardam em ordem de chegada (FIFO), evitando que picos de
 * acesso sobrecarreguem CPU/memória/conexões do servidor.
 */
@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class FilaGeracaoAvaliacaoService implements Serializable
{
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(FilaGeracaoAvaliacaoService.class.getName());

	@Inject
	private GeracaoAvaliacaoWorkerService worker;

	@Inject
	private PedidoAvaliacaoDAO pedidoAvaliacaoDAO;

	/**
	 * Quantas vezes a montagem de um pedido pode ser iniciada antes de ele ser dado por perdido.
	 * A regra mora aqui, junto de quem desiste; quem conta a tentativa é o {@code Montador}.
	 */
	public static final int LIMITE_TENTATIVA_GERACAO = 3;

	private final Queue<Long> fila = new ConcurrentLinkedQueue<>();

	/** true enquanto o worker está consumindo a fila; garante processamento de um pedido por vez. */
	private final AtomicBoolean processando = new AtomicBoolean(false);

	/**
	 * Reenfileira pedidos que ficaram parados em AGUARDANDO/GERANDO após um restart do servidor
	 * (a fila em memória se perde no restart, o status no banco não).
	 *
	 * <p><b>Com teto de tentativas.</b> Antes isto reenfileirava tudo, sempre. Um pedido que
	 * derruba a JVM durante a geração — um OOM, por exemplo — voltava para a fila no restart,
	 * derrubava de novo, e o servidor entrava num laço de reboot do qual não saía sozinho. Como o
	 * {@code Montador} conta a tentativa <b>antes</b> de gerar, o contador sobrevive à queda:
	 * esgotadas as tentativas o pedido vira ERRO e sai do caminho, em vez de derrubar a aplicação
	 * para sempre. É o mesmo remédio do {@code LIMITE_TENTATIVA_ENVIO} da fila de e-mail.</p>
	 */
	@PostConstruct
	public void recuperarPendentes()
	{
		List<PedidoAvaliacao> pendentes = pedidoAvaliacaoDAO.buscarPendentes();
		int recuperados = 0;
		int desistidos = 0;

		for(PedidoAvaliacao pedido : pendentes)
		{
			if(pedido.getTentativaGeracao() >= LIMITE_TENTATIVA_GERACAO)
			{
				desistidos++;
				logger.warning("Pedido de avaliação " + pedido.getId() + " abandonado: "
					+ pedido.getTentativaGeracao() + " tentativa(s) de geração sem concluir.");

				// Isto roda no @PostConstruct de um @Startup: exceção aqui reprova o deploy da
				// aplicação inteira. Não enfileirar já resolve o laço de reboot; marcar o ERRO é
				// só para o pedido aparecer certo na tela, e não vale o risco.
				try
				{
					pedidoAvaliacaoDAO.marcarErro(pedido.getId());
				}
				catch(Exception e)
				{
					logger.log(java.util.logging.Level.WARNING,
						"Falha ao marcar ERRO no pedido " + pedido.getId(), e);
				}

				continue;
			}

			fila.add(pedido.getId());
			recuperados++;
		}

		if(desistidos > 0)
			logger.warning(desistidos + " pedido(s) de avaliação abandonado(s) por excesso de tentativas.");

		if(recuperados > 0)
		{
			logger.info(recuperados + " pedido(s) de avaliação recuperado(s) na fila após início da aplicação.");
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
