package filtro.exercicio;

import java.io.Serializable;

import lombok.Data;
import modelo.academico.Assunto;
import modelo.exercicio.Nivel;
import modelo.pdf.Visibilidade;

@Data
public class FiltroExercicio implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Assunto assunto;
	private Nivel nivel;
	private Boolean global;
	private Visibilidade visibilidade;
	private String texto;

	public void limpar()
	{
		assunto = null;
		nivel = null;
		global = null;
		visibilidade = null;
		texto = null;
	}
}
