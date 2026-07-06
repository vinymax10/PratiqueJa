package modelo.publicacao;

public enum FinalidadeCta
{
	Ensino("Ensino"),
	ProducaoConteudo("Produção de Conteúdo"),
	Apostilas("Apostilas"),
	EBook("EBook");

	private String nome;
	
	FinalidadeCta(String nome)
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

	public boolean equals(FinalidadeCta x)
	{
		FinalidadeCta modulo = (FinalidadeCta) x;
		return this.nome.equals(modulo.nome);
	}

}
