package matematica.avancado.combinatoria.nivel2package;

public enum TipoPascal
{
	Subconjuntos(0),
	Binomial(1);

	private int tipo;

	TipoPascal(int tipo)
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

	public boolean equals(TipoPascal x)
	{
		TipoPascal outro = (TipoPascal) x;
		return this.tipo == outro.tipo;
	}
}
