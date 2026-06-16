package modelo.avaliacao;

public enum FormatoAvaliacao
{
	ALTERNATIVAS("Com Alternativas"),
	DISCURSIVA("Discursiva");

	private final String nome;

	FormatoAvaliacao(String nome)
	{
		this.nome = nome;
	}

	public String getNome()
	{
		return nome;
	}
}
