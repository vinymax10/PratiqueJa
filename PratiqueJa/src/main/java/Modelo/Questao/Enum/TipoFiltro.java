package Modelo.Questao.Enum;

public enum TipoFiltro
{
	Todas("Todas"),
	Repondidas("Repondidas"),
	NaoRespondidas("Não Respondidas"),
	Aceitei("Aceitei"),
	Errei("Errei");

	private String nome;

	TipoFiltro(String nome)
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

	public boolean equals(TipoFiltro x)
	{
		TipoFiltro assunto = (TipoFiltro) x;
		return this.nome.equals(assunto.nome);
	}

}
