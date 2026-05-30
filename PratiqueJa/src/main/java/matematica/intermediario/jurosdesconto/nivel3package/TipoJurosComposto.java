package matematica.intermediario.jurosdesconto.nivel3package;

public enum TipoJurosComposto
{
	XCIT(0),
	MXIT(1),
	MCIX(2),
	XXCIT(3),
	XMXIT(4);

	private int tipo;

	TipoJurosComposto(int tipo)
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

	public boolean equals(TipoJurosComposto x)
	{
		TipoJurosComposto tipoProporcao = (TipoJurosComposto) x;
		return this.tipo == tipoProporcao.tipo;
	}
}
