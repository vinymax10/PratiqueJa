package Bean.Usuario.Enum;

public enum Periodo
{
	semanal(0),
	mensal(1),
	anual(2);

	private int valor;

	Periodo(int valor)
	{
		this.valor = valor;
	}

	public int getValor()
	{
		return valor;
	}

	public void setValor(int valor)
	{
		this.valor = valor;
	}

	public boolean equals(Periodo x)
	{
		Periodo modulo = (Periodo) x;
		return this.valor==modulo.valor;
	}

}
