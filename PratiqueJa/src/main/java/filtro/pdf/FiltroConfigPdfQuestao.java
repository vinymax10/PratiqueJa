package filtro.pdf;

import java.io.Serializable;

import lombok.Data;
import modelo.pdf.Visibilidade;
import modelo.questao.Dificuldade;

@Data
public class FiltroConfigPdfQuestao implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Dificuldade dificuldade;

	private Visibilidade visibilidade;

	public void limpar()
	{
		dificuldade  = null;
		visibilidade = null;
	}
}
