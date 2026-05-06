package matematica.basico.mmcmdc;

public enum TipoProblema
{
	MmcDuas(0),
	MmcTres(1),
	MdcDuas(2),
	MdcTres(3);

	private int tipo;

	TipoProblema(int tipo)
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

	public boolean equals(TipoProblema x)
	{
		TipoProblema tipoProporcao = (TipoProblema) x;
		return this.tipo == tipoProporcao.tipo;
	}
}
