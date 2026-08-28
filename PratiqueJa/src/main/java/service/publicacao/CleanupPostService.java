package service.publicacao;

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

import dao.publicacao.PedidoPostDAO;
import modelo.publicacao.PedidoPost;

/**
 * Remove diariamente os ZIPs de lotes de posts expirados do disco.
 *
 * <p><b>Um pedido, uma transação.</b> A classe é {@code NOT_SUPPORTED} de propósito: sem isso o
 * método do timer roda no default {@code REQUIRED} e o lote inteiro vira um commit só. Bastava um
 * pedido dar erro para a transação ficar marcada para rollback — o {@code catch} de dentro do laço
 * engolia, os demais seguiam "com sucesso" e no fim <b>nenhuma</b> limpeza era gravada, embora os
 * arquivos já tivessem sido apagados do disco. Resultado: registros apontando para arquivos que não
 * existem mais, em silêncio.</p>
 *
 * <p>A ordem — apagar do disco e só então gravar — é mantida de propósito: {@code deleteIfExists}
 * não reclama de arquivo ausente, então se a gravação falhar a rodada de amanhã reencontra o pedido,
 * não acha o arquivo e conclui a limpeza. O lote se conserta sozinho.</p>
 */
@Singleton
@Startup
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CleanupPostService
{
	private static final Logger logger = Logger.getLogger(CleanupPostService.class.getName());

	@Inject
	private PedidoPostDAO pedidoPostDAO;

	@Schedule(hour = "2", minute = "0", second = "0", persistent = false)
	public void removerExpirados()
	{
		List<PedidoPost> expirados = pedidoPostDAO.buscarExpirados(LocalDateTime.now());
		int limpos = 0;
		int falhas = 0;

		for(PedidoPost pedido : expirados)
		{
			try
			{
				excluirArquivo(pedido.getCaminhoArquivo());
				pedidoPostDAO.limparArquivo(pedido.getId());
				limpos++;
			}
			catch(Exception e)
			{
				falhas++;
				logger.log(Level.WARNING, "Falha ao limpar o ZIP do pedido de posts id="
					+ pedido.getId() + "; os demais seguem.", e);
			}
		}

		if(limpos > 0 || falhas > 0)
			logger.info("Limpeza de posts expirados: " + limpos + " limpo(s), " + falhas + " com falha.");
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
