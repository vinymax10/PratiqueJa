package matematica.intermediario.jurosdesconto.nivel2package;

public enum TipoDescontoSimples
{
	XNIT(0),
	DXIT(1),
	DNXT(2),
	DNIX(3),
	XDNIT(4);

	private int tipo;

	TipoDescontoSimples(int tipo)
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

	public boolean equals(TipoDescontoSimples x)
	{
		TipoDescontoSimples tipoProporcao = (TipoDescontoSimples) x;
		return this.tipo == tipoProporcao.tipo;
	}
}
