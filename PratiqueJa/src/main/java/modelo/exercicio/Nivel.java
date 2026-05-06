package modelo.exercicio;

public enum Nivel
{
	Nivel1("Nível 1"),
	Nivel2("Nível 2"),
	Nivel3("Nível 3");

	private String nome;

	Nivel(String nome)
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

	public boolean equals(Nivel x)
	{
		Nivel nivel = (Nivel) x;
		return this.nome.equals(nivel.nome);
	}

}
