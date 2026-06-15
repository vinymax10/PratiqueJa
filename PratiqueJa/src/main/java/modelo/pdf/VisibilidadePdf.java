package modelo.pdf;

public enum VisibilidadePdf
{
	Basico("Básico"),
	Premium("Premium");

	private String nome;

	VisibilidadePdf(String nome)
	{
		this.nome = nome;
	}

	public String getNome()
	{
		return nome;
	}
}
