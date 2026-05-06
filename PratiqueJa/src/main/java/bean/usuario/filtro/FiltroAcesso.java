package bean.usuario.filtro;

import java.io.Serializable;
import java.time.LocalDate;

public class FiltroAcesso implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String nomeUsuario;

	private LocalDate inicio;
	private LocalDate termino;

	private int minutosMinimo;
	private int minutosMaximo;

	private String idSessao;

	private Boolean finalizado;

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

	public int getMinutosMinimo()
	{
		return minutosMinimo;
	}

	public void setMinutosMinimo(int minutosMinimo)
	{
		this.minutosMinimo = minutosMinimo;
	}

	public int getMinutosMaximo()
	{
		return minutosMaximo;
	}

	public void setMinutosMaximo(int minutosMaximo)
	{
		this.minutosMaximo = minutosMaximo;
	}

	public String getIdSessao()
	{
		return idSessao;
	}

	public void setIdSessao(String idSessao)
	{
		this.idSessao = idSessao;
	}

	public Boolean getFinalizado()
	{
		return finalizado;
	}

	public void setFinalizado(Boolean finalizado)
	{
		this.finalizado = finalizado;
	}

	@Override
	public String toString()
	{
		return "FiltroAcesso:\n" + (nomeUsuario != null ? "nomeUsuario: " + nomeUsuario + "\n" : "") + (inicio != null ? "inicio: " + inicio + "\n" : "")
		+ (termino != null ? "termino: " + termino + "\n" : "") + "minutosMinimo: " + minutosMinimo + "\nminutosMaximo: " + minutosMaximo + "\n"
		+ (idSessao != null ? "idSessao: " + idSessao + "\n" : "") + (finalizado != null ? "finalizado: " + finalizado : "");
	}

}
