package bean.usuario;

public enum Periodo
{
	semanal(0),
	mensal(1),
	anual(2);

	private final int valor;

	Periodo(int valor)
	{
		this.valor = valor;
	}

	public int getValor()
	{
		return valor;
	}
}
