package service.pdf;

/**
 * Falha de validação esperada na geração de listas de questões em PDF
 * (ex.: questões insuficientes, Config não configurada). Ver {@link ListaPdfException}.
 */
public class ListaQuestoesPdfException extends ListaPdfException
{
	private static final long serialVersionUID = 1L;

	public ListaQuestoesPdfException(String mensagem)
	{
		super(mensagem);
	}

	public ListaQuestoesPdfException(String mensagem, boolean erro)
	{
		super(mensagem, erro);
	}
}
