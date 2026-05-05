package Matematica.Avancado.Probabilidade.Nivel2Package;

public enum TipoProbabilidadeUniao
{
	AUB(0),
	AUBC(1),
	AMB(2),
	BMA(3);

	private int tipo;

	TipoProbabilidadeUniao(int tipo)
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

	public boolean equals(TipoProbabilidadeUniao x)
	{
		TipoProbabilidadeUniao tipoProporcao = (TipoProbabilidadeUniao) x;
		return this.tipo == tipoProporcao.tipo;
	}
}
