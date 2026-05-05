package Matematica.Avancado.Probabilidade.Nivel3Package;

public enum TipoProbabilidadeCondicao
{
	AB(0),
	BA(1),
	AA(2),
	BB(3);

	private int tipo;

	TipoProbabilidadeCondicao(int tipo)
	{
		this.tipo = tipo;
	}

	public int getTipo()
	{
		return tipo;
	}

	public void setTipo(int tipo)
	{
		this.tipo = tipo;
	}

	public boolean equals(TipoProbabilidadeCondicao x)
	{
		TipoProbabilidadeCondicao tipoProporcao = (TipoProbabilidadeCondicao) x;
		return this.tipo == tipoProporcao.tipo;
	}
}
