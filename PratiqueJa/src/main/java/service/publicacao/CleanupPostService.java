package service.publicacao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

import dao.publicacao.PedidoPostDAO;
import modelo.publicacao.PedidoPost;

/**
 * Remove diariamente os ZIPs de lotes de posts expirados do disco.
 */
@Singleton
@Startup
public class CleanupPostService
{
	@Inject
	private PedidoPostDAO pedidoPostDAO;

	@Schedule(hour = "2", minute = "0", second = "0", persistent = false)
	public void removerExpirados()
	{
		List<PedidoPost> expirados = pedidoPostDAO.buscarExpirados(LocalDateTime.now());
		for(PedidoPost pedido : expirados)
		{
			try
			{
				excluirArquivo(pedido.getCaminhoArquivo());
				pedido.setCaminhoArquivo(null);
				pedido.setNomeDownload(null);
				pedidoPostDAO.salvar(pedido);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	private void excluirArquivo(String caminho) throws IOException
	{
		if(caminho == null || caminho.isBlank())
			return;
		Path arquivo = Path.of(caminho);
		Files.deleteIfExists(arquivo);

		Path dir = arquivo.getParent();
		if(dir != null && Files.exists(dir) && estaVazio(dir))
			Files.delete(dir);
	}

	private boolean estaVazio(Path dir) throws IOException
	{
		try(var stream = Files.list(dir))
		{
			return stream.findAny().isEmpty();
		}
	}
}
