package filtro.exercicio;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import filtro.PeriodoPreset;
import lombok.Data;
import modelo.academico.Assunto;
import modelo.academico.Modulo;
import modelo.exercicio.Nivel;
import modelo.usuario.Usuario;

@Data
public class FiltroResultadoExercicio implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String nomeUsuario;

	private Assunto assunto;

	private Modulo modulo;

	private Nivel nivel;

	private String nomeEnunciadoDescricao;

	private Usuario usuario;

	private List<LocalDate> periodo;

	private PeriodoPreset preset;

	public void limpar()
	{
		nomeUsuario = null;
		assunto = null;
		modulo = null;
		nivel = null;
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
