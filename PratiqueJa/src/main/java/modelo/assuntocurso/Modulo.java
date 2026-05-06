package modelo.assuntocurso;

public enum Modulo
{
	Basico("Básico"),
	Intermediario("Intermediário"),
	Avancado("Avançado"),
	Expert("Expert");

	private String nome;

	Modulo(String nome)
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

	public boolean equals(Modulo x)
	{
		Modulo modulo = (Modulo) x;
		return this.nome.equals(modulo.nome);
	}

}
