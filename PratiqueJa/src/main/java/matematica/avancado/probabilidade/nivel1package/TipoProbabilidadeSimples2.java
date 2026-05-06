package matematica.avancado.probabilidade.nivel1package;

public enum TipoProbabilidadeSimples2
{
	AX(0),
	XB(1),
	XBT(2),
	AXT(3);

	private int tipo;

	TipoProbabilidadeSimples2(int tipo)
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

	public boolean equals(TipoProbabilidadeSimples2 x)
	{
		TipoProbabilidadeSimples2 tipoProporcao = (TipoProbabilidadeSimples2) x;
		return this.tipo == tipoProporcao.tipo;
	}
}
