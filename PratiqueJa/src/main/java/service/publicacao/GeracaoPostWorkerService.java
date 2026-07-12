package service.publicacao;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.Asynchronous;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;

/**
 * Worker assíncrono da fila única de geração de posts. Existe como bean separado de
 * {@link FilaGeracaoPostService} para que o {@code @Asynchronous} funcione de verdade (chamada
 * de método dentro do mesmo bean não passa pelo proxy do container e ignoraria a anotação,
 * rodando de forma síncrona na thread de quem enfileirou).
 */
@Stateless
public class GeracaoPostWorkerService implements Serializable
{
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(GeracaoPostWorkerService.class.getName());

	@Inject
	private MontadorPostService montadorService;

	/**
	 * Consome a fila até esvaziá-la, montando um pedido de posts por vez (nunca dois em
	 * paralelo, para toda a aplicação). Ao terminar, libera a fila e relança o processamento
	 * caso algo tenha sido enfileirado durante a última iteração.
	 */
	@Asynchronous
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void processar(FilaGeracaoPostService fila)
	{
		try
		{
			Long pedidoId;
			while((pedidoId = fila.proximoDaFila()) != null)
			{
				try
				{
					montadorService.montar(pedidoId);
				}
				catch(Exception e)
				{
					logger.log(Level.SEVERE, "Falha ao gerar o pedido de posts " + pedidoId, e);
				}
			}
		}
		finally
		{
			fila.marcarOcioso();
		}
	}
}
