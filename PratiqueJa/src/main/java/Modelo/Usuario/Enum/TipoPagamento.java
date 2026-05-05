package Modelo.Usuario.Enum;

public enum TipoPagamento
{
	Mensal("Mensal"),
	Anual("Anual");

	private String nome;

	TipoPagamento(String nome)
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

	public boolean equals(TipoPagamento x)
	{
		TipoPagamento modulo = (TipoPagamento) x;
		return this.nome.equals(modulo.nome);
	}

}
