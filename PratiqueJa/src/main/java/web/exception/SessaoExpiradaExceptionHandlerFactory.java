package web.exception;

import jakarta.faces.context.ExceptionHandler;
import jakarta.faces.context.ExceptionHandlerFactory;

/** Registra o {@link SessaoExpiradaExceptionHandler} — ver faces-config.xml. */
public class SessaoExpiradaExceptionHandlerFactory extends ExceptionHandlerFactory
{
	public SessaoExpiradaExceptionHandlerFactory(ExceptionHandlerFactory wrapped)
	{
		super(wrapped);
	}

	@Override
	public ExceptionHandler getExceptionHandler()
	{
		return new SessaoExpiradaExceptionHandler(getWrapped().getExceptionHandler());
	}
}
