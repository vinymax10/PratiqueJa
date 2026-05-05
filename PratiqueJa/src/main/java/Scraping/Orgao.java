package Scraping;

import java.io.Serializable;

public class Orgao implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String nome;
	private String sigla;

	public Orgao(String nome, String sigla)
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
		return "Orgao [nome=" + nome + ", sigla=" + sigla + "]";
	}

}
