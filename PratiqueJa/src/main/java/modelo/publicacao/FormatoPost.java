package modelo.publicacao;

/** Formato de uma publicação. Usado quando o plano gera apenas um formato por dia (Básico). */
public enum FormatoPost
{
	Feed("Feed"),
	Reel("Reel");

	private String nome;

	FormatoPost(String nome)
	{
		this.nome = nome;
	}

	public String getNome()
	{
		return nome;
	}
}
