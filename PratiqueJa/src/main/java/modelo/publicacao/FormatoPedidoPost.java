package modelo.publicacao;

/** Formato escolhido para um item de um pedido de posts sob demanda. */
public enum FormatoPedidoPost
{
	FEED("Feed"),
	REEL("Reel"),
	AMBOS("Feed e reel");

	private final String nome;

	FormatoPedidoPost(String nome)
	{
		this.nome = nome;
	}

	public String getNome()
	{
		return nome;
	}

	/** Posts (créditos) que cada exercício gera: 2 no AMBOS (feed + reel), 1 nos demais. */
	public int getPostsPorExercicio()
	{
		return this == AMBOS ? 2 : 1;
	}

	public boolean geraFeed()
	{
		return this == FEED || this == AMBOS;
	}

	public boolean geraReel()
	{
		return this == REEL || this == AMBOS;
	}
}
