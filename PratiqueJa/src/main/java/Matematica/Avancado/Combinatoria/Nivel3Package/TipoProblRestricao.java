package Matematica.Avancado.Combinatoria.Nivel3Package;

public enum TipoProblRestricao
{
	PermuPosRestrita(0),
	CombExclusao(1),
	PrincMultRestricao(2),
	ArranjoObrigacao(3);

	private int tipo;

	TipoProblRestricao(int tipo)
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

	public boolean equals(TipoProblRestricao x)
	{
		TipoProblRestricao tipoProporcao = (TipoProblRestricao) x;
		return this.tipo == tipoProporcao.tipo;
	}
}
