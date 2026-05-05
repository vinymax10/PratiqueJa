package Matematica.Basico.Conjuntos.Nivel3Package;

public enum TipoOperacaoMenos
{
//	|A-B|=|A|-|A\cap B|
	AMenosB(0),
	A(1),
	AintersecB(2);

	private int tipo;

	TipoOperacaoMenos(int tipo)
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

	public boolean equals(TipoOperacaoMenos x)
	{
		TipoOperacaoMenos tipoProporcao = (TipoOperacaoMenos) x;
		return this.tipo == tipoProporcao.tipo;
	}
}
