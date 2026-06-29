package filtro.usuario;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import filtro.PeriodoPreset;
import lombok.Data;
import modelo.usuario.Produto;

@Data
public class FiltroPagamento implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long id;

	private String nomeUsuario;

	private Produto produto;

	private Double valor;

	private List<LocalDate> periodo;
	private PeriodoPreset preset;

	private Boolean pago;

	public void limpar()
	{
		id = null;
		nomeUsuario = null;
		produto = null;
		valor = null;
		periodo = null;
		preset = null;
		pago = null;
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
