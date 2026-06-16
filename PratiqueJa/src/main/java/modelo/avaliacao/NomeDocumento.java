package modelo.avaliacao;

public enum NomeDocumento
{
	PROVA("Prova"),
	AVALIACAO("Avaliação"),
	SIMULADO("Simulado"),
	TESTE("Teste");

	private final String nome;

	NomeDocumento(String nome)
	{
		this.nome = nome;
	}

	public String getNome()
	{
		return nome;
	}
}
