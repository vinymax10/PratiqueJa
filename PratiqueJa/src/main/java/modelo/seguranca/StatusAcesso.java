package modelo.seguranca;

public enum StatusAcesso
{
	ATIVO("Ativo"),
	FINALIZADO("Finalizado"),
	EXPIRADO("Expirado");

	private String nome;

	StatusAcesso(String nome)
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

	public boolean equals(StatusAcesso x)
	{
		StatusAcesso modulo = (StatusAcesso) x;
		return this.nome.equals(modulo.nome);
	}

}
