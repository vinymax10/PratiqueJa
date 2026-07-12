package modelo.publicacao;

/** Estilo visual usado na geração dos posts (Feed/Reel): controla o fundo, a moldura e a cor de destaque. */
public enum EstiloPost
{
	FotoFundo("Foto de Fundo", "Sua foto de fundo ocupa toda a página, com o conteúdo sobre um painel branco translúcido.", "pi pi-image", "Com foto"),
	MolduraAzul("Moldura Azul", "Sua foto de fundo, com o conteúdo num cartão branco de moldura dupla na cor de destaque.", "fa-solid fa-border-all", "Moldura dupla"),
	SombraCoral("Sombra Coral", "Sua foto de fundo, com o conteúdo num cartão branco com sombra na cor de destaque por trás.", "fa-solid fa-layer-group", "Sombra"),
	CantosVivos("Cantos Vivos", "Sua foto de fundo, com o conteúdo emoldurado só por cantos na cor de destaque, estilo minimalista.", "fa-solid fa-expand", "Minimalista"),
	FaixaSuperior("Faixa Superior", "Sua foto de fundo, com uma faixa na cor de destaque no topo do cartão branco — visual de pôster.", "pi pi-flag", "Pôster");

	private String nome;
	private String descricao;
	private String icone;
	private String tag;

	EstiloPost(String nome, String descricao, String icone, String tag)
	{
		this.nome = nome;
		this.descricao = descricao;
		this.icone = icone;
		this.tag = tag;
	}

	public String getNome()
	{
		return nome;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public String getIcone()
	{
		return icone;
	}

	public String getTag()
	{
		return tag;
	}
}
