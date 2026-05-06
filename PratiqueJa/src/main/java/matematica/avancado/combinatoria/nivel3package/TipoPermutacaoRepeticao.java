package matematica.avancado.combinatoria.nivel3package;

public enum TipoPermutacaoRepeticao
{
	Letra(0),
	Digitos(1);

	private int tipo;

	TipoPermutacaoRepeticao(int tipo)
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

	public boolean equals(TipoPermutacaoRepeticao x)
	{
		TipoPermutacaoRepeticao tipoProporcao = (TipoPermutacaoRepeticao) x;
		return this.tipo == tipoProporcao.tipo;
	}
}
