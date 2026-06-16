package modelo.avaliacao;

public enum PosicaoGabarito
{
	APOS_CADA_AVALIACAO("Após cada avaliação"),
	AGRUPADO_NO_FINAL("Agrupado no final");

	private final String nome;

	PosicaoGabarito(String nome)
	{
		this.nome = nome;
	}

	public String getNome()
	{
		return nome;
	}
}
