package filtro.email;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import filtro.PeriodoPreset;
import lombok.Data;
import modelo.email.StatusEmail;

@Data
public class FiltroEmail implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String destinatario;

	private String assunto;

	private StatusEmail status;

	private List<LocalDate> periodo;

	private PeriodoPreset preset;

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

	public void limpar()
	{
		destinatario = null;
		assunto = null;
		status = null;
		periodo = null;
		preset = null;
	}
}
