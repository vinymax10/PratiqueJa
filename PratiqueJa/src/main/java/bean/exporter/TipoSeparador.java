package bean.exporter;

public enum TipoSeparador
{
	QUEBRA_lINHA("Quebra de linha","\n"),
	ESPACO("Espaço"," "),
	VIRGULA("Vírgula",", ");

	private String nome;
	private String caracter;

	TipoSeparador(String nome, String caracter)
	{
		this.nome = nome;
		this.caracter=caracter;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getCaracter()
	{
		return caracter;
	}

	public void setCaracter(String caracter)
	{
		this.caracter = caracter;
	}

	public boolean equals(TipoSeparador x)
	{
		TipoSeparador modulo = (TipoSeparador) x;
		return this.nome.equals(modulo.nome);
	}

}
