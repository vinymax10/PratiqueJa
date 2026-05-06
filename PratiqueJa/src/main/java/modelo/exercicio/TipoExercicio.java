package modelo.exercicio;

public enum TipoExercicio
{
	Texto("Texto"),
	Inteiro("Inteiro"),
	Boolean("Boolean"),
	Image("Image");

	private String nome;

	TipoExercicio(String nome)
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

	public boolean equals(TipoExercicio x)
	{
		TipoExercicio nivel = (TipoExercicio) x;
		return this.nome.equals(nivel.nome);
	}

}
