package filtro.exercicio;

import java.io.Serializable;

import lombok.Data;
import modelo.exercicio.ExercicioPadrao;

@Data
public class FiltroExercicio implements Serializable
{
	private static final long serialVersionUID = 1L;

	private ExercicioPadrao exercicioPadrao;
	private Boolean global;
	private String enunciado;

	public void limpar()
	{
		exercicioPadrao = null;
		global = null;
		enunciado = null;
	}
}
