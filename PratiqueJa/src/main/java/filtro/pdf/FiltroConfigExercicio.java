package filtro.pdf;

import java.io.Serializable;

import lombok.Data;
import modelo.exercicio.Nivel;
import modelo.pdf.VisibilidadePdf;

@Data
public class FiltroConfigExercicio implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Nivel nivel;

	private VisibilidadePdf visibilidade;

	public void limpar()
	{
		nivel       = null;
		visibilidade = null;
	}
}
