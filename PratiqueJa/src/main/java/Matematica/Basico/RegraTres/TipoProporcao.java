package Matematica.Basico.RegraTres;

public enum TipoProporcao
{
	XABC(0),
	AXBC(1),
	ABXC(2),
	ABCX(3),
	IABCX(4),
	IABXC(5),
	IABCDEX(6),
	IABCDXE(7),
	IABCXDE(8);

	private int tipo;

	TipoProporcao(int tipo)
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

	public boolean equals(TipoProporcao x)
	{
		TipoProporcao tipoProporcao = (TipoProporcao) x;
		return this.tipo == tipoProporcao.tipo;
	}
}
