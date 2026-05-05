package Matematica.Intermediario.Pitagoras.Nivel3Package;

public enum TipoPitagoras
{
	XBC(0),
	AXC(1),
	ABX(2);

	private int tipo;

	TipoPitagoras(int tipo)
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

	public boolean equals(TipoPitagoras x)
	{
		TipoPitagoras tipoProporcao = (TipoPitagoras) x;
		return this.tipo == tipoProporcao.tipo;
	}
}
