package modelo.publicacao;

public enum PerfilCriador
{
	Master("Master",1),
	Premium("Premium",1),
	Basico("Básico",3);

	private String nome;
	private int intervalo;
	
	PerfilCriador(String nome, int intervalo)
	{
		this.nome = nome;
		this.intervalo=intervalo;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public int getIntervalo()
	{
		return intervalo;
	}

	public void setIntervalo(int intervalo)
	{
		this.intervalo = intervalo;
	}

	public boolean equals(PerfilCriador x)
	{
		PerfilCriador modulo = (PerfilCriador) x;
		return this.nome.equals(modulo.nome);
	}

}
