package Matematica.Avancado.JurosDesconto.Nivel1Package;

public enum TipoJurosSimples
{
	XCIT(0),
	JXIT(1),
	JCXT(2),
	JCIX(3),
	XJCIT(4);

	private int tipo;

	TipoJurosSimples(int tipo)
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

	public boolean equals(TipoJurosSimples x)
	{
		TipoJurosSimples tipoProporcao = (TipoJurosSimples) x;
		return this.tipo == tipoProporcao.tipo;
	}
}
