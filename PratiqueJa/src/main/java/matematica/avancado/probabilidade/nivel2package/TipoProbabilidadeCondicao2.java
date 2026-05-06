package matematica.avancado.probabilidade.nivel2package;

public enum TipoProbabilidadeCondicao2
{
	AB(0),
	BA(1);

	private int tipo;

	TipoProbabilidadeCondicao2(int tipo)
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

	public boolean equals(TipoProbabilidadeCondicao2 x)
	{
		TipoProbabilidadeCondicao2 tipoProporcao = (TipoProbabilidadeCondicao2) x;
		return this.tipo == tipoProporcao.tipo;
	}
}
