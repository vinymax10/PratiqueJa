package modelo.auditoria;

public enum TipoEvento
{
	CRIACAO("Criação", "bgVerde", "fa-regular fa-square-plus"),
	EDICAO("Edição", "bgAzul", "fa-regular fa-pen-to-square"),
	EXCLUSAO("Exclusão", "bgVermelho", "fa-regular fa-square-minus");
	
	private String nome;
	private String cor;
	private String icon;

	TipoEvento(String nome, String cor, String icon)
	{
		this.nome = nome;
		this.cor = cor;
		this.icon = icon;
	}

	public String getNome()
	{
		return nome;
	}

	public String getIcon()
	{
		return icon;
	}

	public String getCor()
	{
		return cor;
	}

	public boolean equals(TipoEvento x)
	{
		return this.equals(x);
	}

}
