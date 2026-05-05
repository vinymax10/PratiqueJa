package Matematica.Basico.Conjuntos.Nivel3Package;

public enum TipoOperacaoUniao
{
//	|A \cup B|=|A|+|B|-|A\cap B|
	AUniaoB(0),
	A(1),
	AintersecB(2);

	private int tipo;

	TipoOperacaoUniao(int tipo)
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

	public boolean equals(TipoOperacaoUniao x)
	{
		TipoOperacaoUniao tipoProporcao = (TipoOperacaoUniao) x;
		return this.tipo == tipoProporcao.tipo;
	}
}
