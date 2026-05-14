package filtro.usuario;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import filtro.PeriodoPreset;
import lombok.Data;

@Data
public class FiltroControleAcesso implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String nomeUsuario;

	private List<LocalDate> periodo;
	private PeriodoPreset preset;

	public void resetPreset()
	{
		preset=PeriodoPreset.PERSONALIZADO;
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
		periodo = null;
		preset = null;
	}

}
