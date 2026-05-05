package Matematica;

public enum Operador
{
	Adicao(0),
	Subtracao(1),
	Multiplicacao(2),
	Divisao(3);

	private int tipo;

	Operador(int tipo)
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

	public boolean equals(Operador x)
	{
		Operador operador = (Operador) x;
		return this.tipo == operador.tipo;
	}
}
