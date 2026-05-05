package Matematica.Avancado.JurosDesconto.Nivel3Package;

public enum TipoDescontoComposto
{
	XNIT(0), //ok
	AXIT(1),//ok
	ANIX(2),//ok
	XXNIT(3),//ok
	XAXIT(4);

	private int tipo;

	TipoDescontoComposto(int tipo)
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

	public boolean equals(TipoDescontoComposto x)
	{
		TipoDescontoComposto tipoProporcao = (TipoDescontoComposto) x;
		return this.tipo == tipoProporcao.tipo;
	}
}
