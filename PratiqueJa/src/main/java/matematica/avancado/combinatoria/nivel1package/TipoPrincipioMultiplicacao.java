package matematica.avancado.combinatoria.nivel1package;

public enum TipoPrincipioMultiplicacao
{
	Duas(0),
	Tres(1);

	private int tipo;

	TipoPrincipioMultiplicacao(int tipo)
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

	public boolean equals(TipoPrincipioMultiplicacao x)
	{
		TipoPrincipioMultiplicacao tipoProporcao = (TipoPrincipioMultiplicacao) x;
		return this.tipo == tipoProporcao.tipo;
	}
}
