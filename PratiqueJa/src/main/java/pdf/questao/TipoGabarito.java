package pdf.questao;

/**
 * Define o nível de detalhe do gabarito incluído na lista de questões.
 */
public enum TipoGabarito
{
	SOMENTE_ALTERNATIVAS("Somente alternativas"),
	COMPLETO("Com resolução completa");

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
