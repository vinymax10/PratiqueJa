package Matematica.Avancado.LeiSenoCosseno.Config;

public enum TipoDado
{
	AB(0),
	AC(1),
	BC(2);

	private int tipo;

	TipoDado(int tipo)
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

	public boolean equals(TipoDado x)
	{
		TipoDado tipoProporcao = (TipoDado) x;
		return this.tipo == tipoProporcao.tipo;
	}
}
