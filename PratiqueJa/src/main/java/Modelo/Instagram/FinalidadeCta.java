package Modelo.Instagram;

public enum FinalidadeCta
{
	Ensino("Ensino"),
	ProducaoConteudo("Produção de Conteúdo"),
	Apostilas("Apostilas"),
	EBook("EBook"),
	Spam("Spam");

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
