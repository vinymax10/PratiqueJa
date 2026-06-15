package matematica.avancado.estatistica.nivel3package;

public enum TipoQuartil
{
	Q1(0),
	Q2(1),
	Q3(2),
	IQR(3);

	private int tipo;

	TipoQuartil(int tipo)
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

	public boolean equals(TipoQuartil x)
	{
		TipoQuartil outro = (TipoQuartil) x;
		return this.tipo == outro.tipo;
	}
}
