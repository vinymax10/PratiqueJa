package scraping;

import java.io.Serializable;

public class Banca implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String nome;
	private String sigla;

	public Banca(String nome, String sigla)
	{
		super();
		this.nome = nome;
		this.sigla = sigla;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getSigla()
	{
		return sigla;
	}

	public void setSigla(String sigla)
	{
		this.sigla = sigla;
	}

	@Override
	public String toString()
	{
		return "Banca [nome=" + nome + ", sigla=" + sigla + "]";
	}

}
