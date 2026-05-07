package bean.usuario.filtro;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;

@Data
public class FiltroAcesso implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String nomeUsuario;

	private LocalDate inicio;
	private LocalDate termino;

	private int minutosMinimo;
	private int minutosMaximo;

	private String idSessao;

}
