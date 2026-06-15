package matematica.avancado.estatistica.nivel3package;

public enum TipoDispersao
{
	Variancia(0),
	DesvioPadrao(1);

	private int tipo;

	TipoDispersao(int tipo)
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

	public boolean equals(TipoDispersao x)
	{
		TipoDispersao outro = (TipoDispersao) x;
		return this.tipo == outro.tipo;
	}
}
