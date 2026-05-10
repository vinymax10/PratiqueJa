package filtro.usuario;

import java.io.Serializable;
import java.time.LocalDate;

public class FiltroControleAcesso implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String nomeUsuario;

	private LocalDate inicio;
	private LocalDate termino;

	public String getNomeUsuario()
	{
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario)
	{
		this.nomeUsuario = nomeUsuario;
	}

	public LocalDate getInicio()
	{
		return inicio;
	}

	public void setInicio(LocalDate inicio)
	{
		this.inicio = inicio;
	}

	public LocalDate getTermino()
	{
		return termino;
	}

	public void setTermino(LocalDate termino)
	{
		this.termino = termino;
	}

	@Override
	public String toString()
	{
		return (nomeUsuario != null ? "nomeUsuario=" + nomeUsuario + ", " : "")
		+ (inicio != null ? "inicio=" + inicio + ", " : "") + (termino != null ? "termino=" + termino : "");
	}

}
