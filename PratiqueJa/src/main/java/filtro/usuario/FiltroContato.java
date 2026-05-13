package filtro.usuario;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import filtro.PeriodoPreset;
import lombok.Data;

@Data
public class FiltroContato implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long id;

	private String nomeUsuario;
	private String mensagem;
	private String assunto;

	private List<LocalDate> periodo;
	private PeriodoPreset preset;

	private Boolean respondido;

	private String email;
	
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
		id = null;
		nomeUsuario = null;
		mensagem = null;
		assunto = null;
		email = null;
		periodo = null;
		preset = null;
		respondido = null;
	}

}
