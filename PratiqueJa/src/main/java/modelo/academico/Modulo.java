package modelo.academico;

public enum Modulo
{
	Basico("Básico",0),
	Intermediario("Intermediário",1),
	Avancado("Avançado",2),
	Expert("Expert",3);

	private String nome;
	private int ordem;

	Modulo(String nome, int ordem)
	{
		this.nome = nome;
		this.ordem=ordem;
	}

	public String getNome()
	{
		return nome;
	}

	public int getOrdem()
	{
		return ordem;
	}

}
