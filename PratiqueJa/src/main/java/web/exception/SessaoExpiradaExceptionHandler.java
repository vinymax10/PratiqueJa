package web.exception;

import java.io.IOException;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.faces.FacesException;
import jakarta.faces.application.ViewExpiredException;
import jakarta.faces.context.ExceptionHandler;
import jakarta.faces.context.ExceptionHandlerWrapper;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ExceptionQueuedEvent;
import jakarta.faces.event.ExceptionQueuedEventContext;

/**
 * Sessão perdida não mostra tela de erro: manda o usuário para a tela inicial, venha de onde vier.
 *
 * <p>O {@code <error-page>} de ViewExpiredException do web.xml não resolve: o Faces propaga a
 * exceção embrulhada num FacesException e o container só desembrulha ServletException, então o
 * mapeamento que casava era o de {@code java.lang.Exception} e o usuário caía no /acesso/500.
 * Numa requisição ajax (a maioria das ações do PrimeFaces) nem isso acontecia — voltava um
 * {@code <partial-response><error>} e o erro estourava na tela.
 *
 * <p>Tratar no ExceptionHandler cobre os dois casos: {@link ExternalContext#redirect(String)}
 * emite um redirect HTTP normal na requisição comum e um {@code <partial-response><redirect/>}
 * quando é ajax — o PrimeFaces obedece e troca a página.
 */
public class SessaoExpiradaExceptionHandler extends ExceptionHandlerWrapper
{
	private static final Logger LOG = LoggerFactory.getLogger(SessaoExpiradaExceptionHandler.class);

	private static final String HOME = "/inicio.xhtml";

	public SessaoExpiradaExceptionHandler(ExceptionHandler wrapped)
	{
		super(wrapped);
	}

	@Override
	public void handle() throws FacesException
	{
		FacesContext contexto = FacesContext.getCurrentInstance();

		for(Iterator<ExceptionQueuedEvent> fila = getUnhandledExceptionQueuedEvents().iterator(); fila.hasNext();)
		{
			ExceptionQueuedEvent evento = fila.next();
			ExceptionQueuedEventContext origem = (ExceptionQueuedEventContext) evento.getSource();

			if(procurarViewExpired(origem.getException()) == null)
				continue;

			if(contexto == null || contexto.getExternalContext() == null)
				continue;

			ExternalContext externo = contexto.getExternalContext();
			try
			{
				LOG.info("Sessão expirada em '{}' — redirecionando para a tela inicial", externo.getRequestServletPath());
				externo.redirect(externo.getRequestContextPath() + HOME);
				contexto.responseComplete();
			}
			catch(IOException e)
			{
				throw new FacesException(e);
			}
			finally
			{
				// tira da fila para o handler padrão não transformar isto em tela de erro
				fila.remove();
			}
		}

		getWrapped().handle();
	}

	/** A ViewExpiredException chega embrulhada (FacesException/ServletException), daí percorrer as causas. */
	private ViewExpiredException procurarViewExpired(Throwable erro)
	{
		while(erro != null)
		{
			if(erro instanceof ViewExpiredException)
				return (ViewExpiredException) erro;
			erro = erro.getCause();
		}
		return null;
	}
}
