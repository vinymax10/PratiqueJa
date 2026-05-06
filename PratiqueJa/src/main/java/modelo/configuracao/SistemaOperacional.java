package modelo.configuracao;

public enum SistemaOperacional
{
	Windows("Windows"),
	Linux("Linux");

	private String nome;

	SistemaOperacional(String nome)
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

	public boolean equals(SistemaOperacional x)
	{
		SistemaOperacional modulo = (SistemaOperacional) x;
		return this.nome.equals(modulo.nome);
	}

}
