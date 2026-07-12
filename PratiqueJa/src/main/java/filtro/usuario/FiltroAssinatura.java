package filtro.usuario;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import filtro.PeriodoPreset;
import lombok.Data;
import modelo.usuario.Produto;
import modelo.usuario.StatusAssinatura;

@Data
public class FiltroAssinatura implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long id;

	private String nomeUsuario;

	private Produto produto;

	private StatusAssinatura status;

	private List<LocalDate> periodo;
	private PeriodoPreset preset;

	public void limpar()
	{
		id = null;
		nomeUsuario = null;
		produto = null;
		status = null;
		periodo = null;
		preset = null;
	}

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

}
