package filtro.exercicio;

import java.io.Serializable;
import java.time.LocalDate;

import modelo.exercicio.ExercicioPadrao;
import modelo.usuario.Usuario;

public class FiltroExercicio implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	private ExercicioPadrao exercicioPadrao;

	private Boolean realizada;

	private LocalDate inicioRealizacao;
	private LocalDate terminoRealizacao;

	private LocalDate inicioPrazo;
	private LocalDate terminoPrazo;

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	public ExercicioPadrao getExercicioPadrao()
	{
		return exercicioPadrao;
	}

	public void setExercicioPadrao(ExercicioPadrao exercicioPadrao)
	{
		this.exercicioPadrao = exercicioPadrao;
	}

	public Boolean getRealizada()
	{
		return realizada;
	}

	public void setRealizada(Boolean realizada)
	{
		this.realizada = realizada;
	}

	public LocalDate getInicioRealizacao()
	{
		return inicioRealizacao;
	}

	public void setInicioRealizacao(LocalDate inicioRealizacao)
	{
		this.inicioRealizacao = inicioRealizacao;
	}

	public LocalDate getTerminoRealizacao()
	{
		return terminoRealizacao;
	}

	public void setTerminoRealizacao(LocalDate terminoRealizacao)
	{
		this.terminoRealizacao = terminoRealizacao;
	}

	public LocalDate getInicioPrazo()
	{
		return inicioPrazo;
	}

	public void setInicioPrazo(LocalDate inicioPrazo)
	{
		this.inicioPrazo = inicioPrazo;
	}

	public LocalDate getTerminoPrazo()
	{
		return terminoPrazo;
	}

	public void setTerminoPrazo(LocalDate terminoPrazo)
	{
		this.terminoPrazo = terminoPrazo;
	}

}
