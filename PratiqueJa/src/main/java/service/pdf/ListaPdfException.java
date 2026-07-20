package service.pdf;

/**
 * Base das falhas de validação esperadas na geração de listas em PDF (questões ou
 * exercícios). A camada de apresentação traduz para uma mensagem ao usuário; o
 * atributo {@code erro} indica a severidade ({@code true} = erro, {@code false} = aviso).
 */
public class ListaPdfException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	private final boolean erro;

	public ListaPdfException(String mensagem)
	{
		this(mensagem, false);
	}

	public ListaPdfException(String mensagem, boolean erro)
	{
		super(mensagem);
		this.erro = erro;
	}

	public boolean isErro()
	{
		return erro;
	}
}
