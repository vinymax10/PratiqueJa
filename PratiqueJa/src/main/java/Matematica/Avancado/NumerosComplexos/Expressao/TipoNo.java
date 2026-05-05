package Matematica.Avancado.NumerosComplexos.Expressao;

public enum TipoNo
{
	Operador(1), Igual(2), Numero(3), Literal(4);

	private int number;

	TipoNo(int number)
	{
		this.number = number;
	}

	public boolean equals(TipoNo x)
	{
		return this.number == x.number;
	}

}
