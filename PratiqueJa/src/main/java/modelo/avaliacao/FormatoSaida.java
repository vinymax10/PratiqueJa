package modelo.avaliacao;

public enum FormatoSaida
{
	PDF_UNICO("PDF único"),
	ZIP("ZIP (um PDF por avaliação)");

	private final String nome;

	FormatoSaida(String nome)
	{
		this.nome = nome;
	}

	public String getNome()
	{
		return nome;
	}
}
