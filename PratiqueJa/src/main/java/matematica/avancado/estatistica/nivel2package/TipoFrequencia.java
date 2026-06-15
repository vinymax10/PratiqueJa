package matematica.avancado.estatistica.nivel2package;

public enum TipoFrequencia
{
	AteNoMaximo(0),
	PeloMenos(1);

	private int tipo;

	TipoFrequencia(int tipo)
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

	public boolean equals(TipoFrequencia x)
	{
		TipoFrequencia outro = (TipoFrequencia) x;
		return this.tipo == outro.tipo;
	}
}
