package modelo.avaliacao;

public enum StatusPedidoAvaliacao
{
	AGUARDANDO("Aguardando"),
	GERANDO("Gerando"),
	CONCLUIDO("Concluído"),
	ERRO("Erro");

	private final String nome;

	StatusPedidoAvaliacao(String nome)
	{
		this.nome = nome;
	}

	public String getNome()
	{
		return nome;
	}
}
