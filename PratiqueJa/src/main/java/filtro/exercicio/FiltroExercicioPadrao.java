package filtro.exercicio;

import java.io.Serializable;

import lombok.Data;
import modelo.academico.Assunto;
import modelo.academico.Modulo;
import modelo.exercicio.Nivel;
import modelo.exercicio.TipoExercicio;

@Data
public class FiltroExercicioPadrao implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Assunto assunto;

	private Modulo modulo;

	private Nivel nivel;

	private String nomeEnunciadoDescricao;

	private TipoExercicio tipoExercicio;

	private int quantidade;

	private Boolean mostrarResolucao;

	public void limpar()
	{
		assunto = null;
		modulo = null;
		nivel = null;
		nomeEnunciadoDescricao = null;
		tipoExercicio = null;
		quantidade = 0;
		mostrarResolucao = null;
	}

}
