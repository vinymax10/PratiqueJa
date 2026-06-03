package filtro.teste;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import filtro.PeriodoPreset;
import lombok.Data;
import modelo.academico.Assunto;
import modelo.academico.Modulo;
import modelo.teste.TestePadrao;
import modelo.usuario.Usuario;

@Data
public class FiltroResultadoTeste implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String nomeUsuario;

	private Assunto assunto;

	private Modulo modulo;

	private TestePadrao testePadrao;

	private Usuario usuario;

	private List<LocalDate> periodo;

	private PeriodoPreset preset;

	public void limpar()
	{
		nomeUsuario = null;
		assunto = null;
		modulo = null;
		testePadrao = null;
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
