package filtro.seguranca;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import filtro.PeriodoPreset;
import lombok.Data;

@Data
public class FiltroAcesso implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String nomeUsuario;

	private List<LocalDate> periodo;
	private PeriodoPreset preset;

	private Integer minutosMinimo;
	private Integer minutosMaximo;

	private String idSessao;

	public void resetPreset()
	{
		preset = PeriodoPreset.PERSONALIZADO;
	}

	public void aplicarPreset()
	{
		if(this.preset == null)
			this.periodo = null;

		else if(preset != PeriodoPreset.PERSONALIZADO)
			periodo = preset.calcularIntervalo();
	}

	public void limpar()
	{
		nomeUsuario = null;
		minutosMinimo = null;
		minutosMaximo = null;
		idSessao = null;
		periodo = null;
		preset = null;
	}
}
