package filtro.exercicio;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import filtro.PeriodoPreset;
import lombok.Data;
import modelo.academico.AssuntoCurso;
import modelo.academico.Modulo;
import modelo.exercicio.Nivel;
import modelo.exercicio.TipoExercicio;
import modelo.usuario.Usuario;

@Data
public class FiltroResultadoExercicio implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String nomeUsuario;

	private AssuntoCurso assuntoCurso;

	private Modulo modulo;

	private Nivel nivel;

	private TipoExercicio tipoExercicio;

	private String nomeEnunciadoDescricao;

	private Usuario usuario;

	private List<LocalDate> periodo;

	private PeriodoPreset preset;

	public void limpar()
	{
		nomeUsuario = null;
		assuntoCurso = null;
		modulo = null;
		nivel = null;
		tipoExercicio = null;
		nomeEnunciadoDescricao = null;
		usuario = null;
		periodo = null;
		preset = null;
	}

	public void resetPreset()
	{
		preset = PeriodoPreset.PERSONALIZADO;
	}

	public void aplicarPreset()
	{
		if(preset == null)
			periodo = null;
		else if(preset != PeriodoPreset.PERSONALIZADO)
			periodo = preset.calcularIntervalo();
	}
}
