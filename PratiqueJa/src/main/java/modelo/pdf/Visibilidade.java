package modelo.pdf;

public enum Visibilidade
{
	Basico("Básico"),
	Premium("Premium");

	private String nome;

	Visibilidade(String nome)
	{
		this.nome = nome;
	}

	public String getNome()
	{
		return nome;
	}
}
