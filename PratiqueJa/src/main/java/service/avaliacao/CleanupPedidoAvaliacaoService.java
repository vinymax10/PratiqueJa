package service.avaliacao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

import dao.avaliacao.PedidoAvaliacaoDAO;
import modelo.avaliacao.PedidoAvaliacao;

/**
 * Remove diariamente os arquivos PDF/ZIP de pedidos expirados do disco.
 */
@Singleton
@Startup
public class CleanupPedidoAvaliacaoService
{
	@Inject
	private PedidoAvaliacaoDAO pedidoAvaliacaoDAO;

	@Schedule(hour = "1", minute = "0", second = "0", persistent = false)
	public void removerExpirados()
	{
		List<PedidoAvaliacao> expirados = pedidoAvaliacaoDAO.buscarExpirados(LocalDateTime.now());
		for (PedidoAvaliacao pedido : expirados)
		{
			try
			{
				excluirArquivo(pedido.getCaminhoArquivo());
				pedido.setCaminhoArquivo(null);
				pedido.setNomeDownload(null);
				pedidoAvaliacaoDAO.salvar(pedido);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
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
