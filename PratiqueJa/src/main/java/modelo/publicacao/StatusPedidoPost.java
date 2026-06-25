package modelo.publicacao;

public enum StatusPedidoPost
{
	RASCUNHO("Rascunho", "bgAmarelo", "fa-regular fa-pen-to-square"),
	AGUARDANDO("Aguardando", "bgCinza", "fa-regular fa-clock"),
	GERANDO("Gerando", "bgAzul", "fa-solid fa-spinner"),
	CONCLUIDO("Concluído", "bgVerde", "fa-regular fa-circle-check"),
	ERRO("Erro", "bgVermelho", "fa-regular fa-circle-xmark");

	private final String nome;
	private final String cor;
	private final String icon;

	StatusPedidoPost(String nome, String cor, String icon)
	{
		this.nome = nome;
		this.cor = cor;
		this.icon = icon;
	}

	public String getNome()
	{
		return nome;
	}

	public String getCor()
	{
		return cor;
	}

	public String getIcon()
	{
		return icon;
	}
}
