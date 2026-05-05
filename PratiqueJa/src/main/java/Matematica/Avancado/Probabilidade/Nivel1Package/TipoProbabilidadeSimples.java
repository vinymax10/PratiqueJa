package Matematica.Avancado.Probabilidade.Nivel1Package;

public enum TipoProbabilidadeSimples
{
	AX(0),
	XB(1),
	ABX(2),
	AXC(3),
	XBC(4),
	XXC(5),
	XBX(6),
	AXX(7);

	private int tipo;

	TipoProbabilidadeSimples(int tipo)
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

	public boolean equals(TipoProbabilidadeSimples x)
	{
		TipoProbabilidadeSimples tipoProporcao = (TipoProbabilidadeSimples) x;
		return this.tipo == tipoProporcao.tipo;
	}
}
