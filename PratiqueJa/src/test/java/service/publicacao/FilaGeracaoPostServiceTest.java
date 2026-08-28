package service.publicacao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import dao.publicacao.PedidoPostDAO;
import modelo.publicacao.PedidoPost;

/**
 * O defeito que estes testes impedem de voltar: <b>o laço de reboot</b>.
 *
 * <p>A fila em memória se perde no restart, o status no banco não — então, ao subir, a aplicação
 * reenfileirava tudo o que estava em AGUARDANDO/GERANDO. Sem contador de tentativa isso não tinha
 * fim: um pedido que derruba a JVM durante a geração (um OOM, por exemplo) voltava para a fila no
 * restart, derrubava de novo, e o servidor entrava num laço do qual não saía sozinho.</p>
 *
 * <p>O conserto é o mesmo da fila de e-mail: a tentativa é contada <b>antes</b> do trabalho e
 * confirmada ali, de modo a sobreviver à queda. Esgotadas as tentativas, o pedido vira ERRO e sai
 * do caminho — o custo é uma queda passageira gastar uma tentativa, o que é melhor que derrubar a
 * aplicação para sempre.</p>
 */
@DisplayName("Fila de geração de posts: recuperação após restart")
class FilaGeracaoPostServiceTest
{
	private FilaGeracaoPostService fila;
	private PedidoPostDAOFalso dao;
	private WorkerFalso worker;

	@BeforeEach
	void iniciar() throws Exception
	{
		fila = new FilaGeracaoPostService();
		dao = new PedidoPostDAOFalso();
		worker = new WorkerFalso();

		injetar(fila, "pedidoPostDAO", dao);
		injetar(fila, "worker", worker);
	}

	@Nested
	@DisplayName("Pedido dentro do limite de tentativas")
	class DentroDoLimite
	{
		@Test
		@DisplayName("volta para a fila e o processamento é disparado")
		void voltaParaAFila()
		{
			dao.pendentes.add(pedido(1L, FilaGeracaoPostService.LIMITE_TENTATIVA_GERACAO - 1));

			fila.recuperarPendentes();

			assertEquals(1, fila.posicaoNaFila(1L), "o pedido deveria estar esperando na fila");
			assertTrue(worker.disparado, "o processamento deveria ter sido disparado");
			assertTrue(dao.marcadosComoErro.isEmpty(), "nada deveria ter sido dado por perdido");
		}

		@Test
		@DisplayName("pedido nunca tentado também volta")
		void pedidoNovoVolta()
		{
			dao.pendentes.add(pedido(7L, 0));

			fila.recuperarPendentes();

			assertEquals(1, fila.posicaoNaFila(7L));
		}
	}

	@Nested
	@DisplayName("Pedido com as tentativas esgotadas")
	class LimiteEsgotado
	{
		@Test
		@DisplayName("não volta para a fila — é ele que derrubava a aplicação a cada restart")
		void naoVoltaParaAFila()
		{
			dao.pendentes.add(pedido(2L, FilaGeracaoPostService.LIMITE_TENTATIVA_GERACAO));

			fila.recuperarPendentes();

			assertEquals(0, fila.posicaoNaFila(2L), "o pedido não deveria ter sido reenfileirado");
			assertTrue(dao.marcadosComoErro.contains(2L), "deveria ter sido marcado como ERRO");
		}

		@Test
		@DisplayName("acima do limite também não volta")
		void acimaDoLimiteNaoVolta()
		{
			dao.pendentes.add(pedido(3L, FilaGeracaoPostService.LIMITE_TENTATIVA_GERACAO + 5));

			fila.recuperarPendentes();

			assertEquals(0, fila.posicaoNaFila(3L));
		}

		@Test
		@DisplayName("não impede a recuperação dos outros pedidos")
		void osOutrosSeguem()
		{
			dao.pendentes.add(pedido(4L, FilaGeracaoPostService.LIMITE_TENTATIVA_GERACAO));
			dao.pendentes.add(pedido(5L, 0));

			fila.recuperarPendentes();

			assertEquals(0, fila.posicaoNaFila(4L));
			assertEquals(1, fila.posicaoNaFila(5L), "o pedido sadio deveria ser o próximo da fila");
			assertTrue(worker.disparado);
		}

		@Test
		@DisplayName("falha ao marcar ERRO não reprova o deploy — isto roda no @PostConstruct")
		void falhaAoMarcarErroNaoDerrubaOStart()
		{
			dao.explodirAoMarcarErro = true;
			dao.pendentes.add(pedido(6L, FilaGeracaoPostService.LIMITE_TENTATIVA_GERACAO));

			fila.recuperarPendentes();

			assertEquals(0, fila.posicaoNaFila(6L), "o importante é não reenfileirar");
		}
	}

	@Test
	@DisplayName("sem pendentes, nada é disparado")
	void semPendentes()
	{
		fila.recuperarPendentes();

		assertTrue(!worker.disparado);
	}

	// ── Auxiliares ────────────────────────────────────────────────────

	private PedidoPost pedido(long id, int tentativas)
	{
		PedidoPost pedido = new PedidoPost();
		pedido.setId(id);
		pedido.setTentativaGeracao(tentativas);
		return pedido;
	}

	private void injetar(Object alvo, String campo, Object valor) throws Exception
	{
		Field field = alvo.getClass().getDeclaredField(campo);
		field.setAccessible(true);
		field.set(alvo, valor);
	}

	/** DAO de mentira: nada de banco, só o que a fila pergunta e o que ela manda gravar. */
	private static class PedidoPostDAOFalso extends PedidoPostDAO
	{
		private static final long serialVersionUID = 1L;

		final List<PedidoPost> pendentes = new ArrayList<>();
		final List<Long> marcadosComoErro = new ArrayList<>();
		boolean explodirAoMarcarErro = false;

		@Override
		public List<PedidoPost> buscarPendentes()
		{
			return pendentes;
		}

		@Override
		public void marcarErro(Long id)
		{
			if(explodirAoMarcarErro)
				throw new IllegalStateException("banco fora do ar no start");

			marcadosComoErro.add(id);
		}
	}

	/** Worker de mentira: só registra que foi chamado, sem gerar nada. */
	private static class WorkerFalso extends GeracaoPostWorkerService
	{
		private static final long serialVersionUID = 1L;

		boolean disparado = false;

		@Override
		public void processar(FilaGeracaoPostService fila)
		{
			disparado = true;
		}
	}
}
