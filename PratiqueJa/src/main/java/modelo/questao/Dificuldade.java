package modelo.questao;

public enum Dificuldade
{
	Facil("Fácil"),
	Medio("Médio"),
	Dificil("Difícil");

	private String nome;

	Dificuldade(String nome)
	{
		this.nome = nome;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public boolean equals(Dificuldade x)
	{
		Dificuldade modulo = (Dificuldade) x;
		return this.nome.equals(modulo.nome);
	}

}
