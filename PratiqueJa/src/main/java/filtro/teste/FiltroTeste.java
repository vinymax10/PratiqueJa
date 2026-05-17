package filtro.teste;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import filtro.PeriodoPreset;
import lombok.Data;
import modelo.academico.AssuntoCurso;
import modelo.teste.TestePadrao;

@Data
public class FiltroTeste implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String nome;

	private TestePadrao testePadrao;

	private Boolean realizado;

	private List<LocalDate> periodo;
	private PeriodoPreset preset;

	private Double nota;

	private AssuntoCurso assuntoCurso;

	public void limpar()
	{
		nome = "";
		testePadrao = null;
		realizado = null;
		periodo = null;
		preset = null;
		nota = null;
		assuntoCurso = null;
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
