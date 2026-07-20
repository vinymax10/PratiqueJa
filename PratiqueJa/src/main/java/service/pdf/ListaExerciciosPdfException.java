package service.pdf;

/**
 * Falha de validação esperada na geração de listas de exercícios em PDF
 * (ex.: exercício padrão inexistente, Config não configurada). Ver {@link ListaPdfException}.
 */
public class ListaExerciciosPdfException extends ListaPdfException
{
	private static final long serialVersionUID = 1L;

	public ListaExerciciosPdfException(String mensagem)
	{
		super(mensagem);
	}

	public ListaExerciciosPdfException(String mensagem, boolean erro)
	{
		super(mensagem, erro);
	}
}
