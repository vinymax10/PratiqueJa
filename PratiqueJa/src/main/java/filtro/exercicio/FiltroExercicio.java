package filtro.exercicio;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import filtro.PeriodoPreset;
import lombok.Data;
import modelo.exercicio.ExercicioPadrao;

@Data
public class FiltroExercicio implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String nome;

	private ExercicioPadrao exercicioPadrao;

	private Boolean realizada;

	private List<LocalDate> periodo;
	private PeriodoPreset preset;

	private LocalDate inicioPrazo;
	private LocalDate terminoPrazo;

	public void limpar()
	{
		nome = null;
		exercicioPadrao = null;
		realizada = null;
		periodo = null;
		preset = null;
		inicioPrazo = null;
		terminoPrazo = null;
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
