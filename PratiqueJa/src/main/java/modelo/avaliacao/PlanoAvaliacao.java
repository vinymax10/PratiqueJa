package modelo.avaliacao;

public enum PlanoAvaliacao
{
	BASICO("Básico", 100),
	PRO("Pro", 500),
	MASTER("Master", 2000);

	private final String nome;
	private final int limiteMensal;

	PlanoAvaliacao(String nome, int limiteMensal)
	{
		this.nome = nome;
		this.limiteMensal = limiteMensal;
	}

	public String getNome()
	{
		return nome;
	}

	public int getLimiteMensal()
	{
		return limiteMensal;
	}
}
