package service.pdf;

/**
 * Sinaliza uma falha de validação esperada na geração de listas de
 * exercícios em PDF (ex.: exercício padrão inexistente, ConfigLatex não
 * configurada). A camada de apresentação traduz para uma mensagem ao usuário.
 *
 * O atributo {@code erro} indica a severidade: {@code true} para erro,
 * {@code false} para apenas um aviso.
 */
public class ListaExerciciosPdfException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	private final boolean erro;

	public ListaExerciciosPdfException(String mensagem)
	{
		this(mensagem, false);
	}

	public ListaExerciciosPdfException(String mensagem, boolean erro)
	{
		super(mensagem);
		this.erro = erro;
	}

	public boolean isErro()
	{
		return erro;
	}
}
