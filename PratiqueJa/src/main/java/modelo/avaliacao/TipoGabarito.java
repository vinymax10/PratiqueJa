package modelo.avaliacao;

public enum TipoGabarito
{
	SOMENTE_GABARITO("Somente Gabarito"),
	COM_RESOLUCAO("Com Resolução");

	private final String nome;

	TipoGabarito(String nome)
	{
		this.nome = nome;
	}

	public String getNome()
	{
		return nome;
	}
}
