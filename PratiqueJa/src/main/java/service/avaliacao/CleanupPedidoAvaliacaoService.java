package service.avaliacao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;

import dao.avaliacao.PedidoAvaliacaoDAO;
import modelo.avaliacao.PedidoAvaliacao;

/**
 * Remove diariamente os arquivos PDF/ZIP de pedidos expirados do disco.
 *
 * <p><b>Um pedido, uma transação</b>, e a limpeza do disco antes da gravação — pelos mesmos dois
 * motivos detalhados em {@code CleanupPostService}: sem o {@code NOT_SUPPORTED} da classe o lote
 * inteiro virava um commit só, que um único erro descartava em silêncio; e apagar antes de gravar é
 * o que faz a rodada seguinte terminar o serviço se a gravação se perder.</p>
 */
@Singleton
@Startup
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CleanupPedidoAvaliacaoService
{
	private static final Logger logger = Logger.getLogger(CleanupPedidoAvaliacaoService.class.getName());

	@Inject
	private PedidoAvaliacaoDAO pedidoAvaliacaoDAO;

	@Schedule(hour = "1", minute = "0", second = "0", persistent = false)
	public void removerExpirados()
	{
		List<PedidoAvaliacao> expirados = pedidoAvaliacaoDAO.buscarExpirados(LocalDateTime.now());
		int limpos = 0;
		int falhas = 0;

		for (PedidoAvaliacao pedido : expirados)
		{
			try
			{
				excluirArquivo(pedido.getCaminhoArquivo());
				pedidoAvaliacaoDAO.limparArquivo(pedido.getId());
				limpos++;
			}
			catch (Exception e)
			{
				falhas++;
				logger.log(Level.WARNING, "Falha ao limpar o arquivo do pedido de avaliação id="
					+ pedido.getId() + "; os demais seguem.", e);
			}
		}

		if (limpos > 0 || falhas > 0)
			logger.info("Limpeza de avaliações expiradas: " + limpos + " limpo(s), " + falhas + " com falha.");
	}

	private void excluirArquivo(String caminho) throws IOException
	{
		if (caminho == null || caminho.isBlank()) return;
		Path arquivo = Path.of(caminho);
		Files.deleteIfExists(arquivo);

		Path dir = arquivo.getParent();
		if (dir != null && Files.exists(dir) && estaVazio(dir))
			Files.delete(dir);
	}

	private boolean estaVazio(Path dir) throws IOException
	{
		try (var stream = Files.list(dir))
		{
			return stream.findAny().isEmpty();
		}
	}
}
