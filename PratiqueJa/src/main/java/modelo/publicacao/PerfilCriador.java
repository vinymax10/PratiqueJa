package modelo.publicacao;

public enum PerfilCriador
{
	// nome, intervalo (dias entre envios), exercicios por dia, gera os dois formatos (feed e reel),
	// créditos de post por mês, dias de retenção do download
	Master("Master", 1, 2, true, 120, 90),
	Premium("Premium", 1, 1, true, 60, 60),
	Basico("Básico", 1, 1, false, 30, 30);

	private String nome;
	private int intervalo;
	private int exerciciosPorDia;
	private boolean ambosFormatos;
	private int creditosMensais;
	private int diasRetencao;

	PerfilCriador(String nome, int intervalo, int exerciciosPorDia, boolean ambosFormatos, int creditosMensais, int diasRetencao)
	{
		this.nome = nome;
		this.intervalo = intervalo;
		this.exerciciosPorDia = exerciciosPorDia;
		this.ambosFormatos = ambosFormatos;
		this.creditosMensais = creditosMensais;
		this.diasRetencao = diasRetencao;
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

	public int getExerciciosPorDia()
	{
		return exerciciosPorDia;
	}

	public void setExerciciosPorDia(int exerciciosPorDia)
	{
		this.exerciciosPorDia = exerciciosPorDia;
	}

	public boolean isAmbosFormatos()
	{
		return ambosFormatos;
	}

	public void setAmbosFormatos(boolean ambosFormatos)
	{
		this.ambosFormatos = ambosFormatos;
	}

	/** Créditos de post liberados por mês neste plano (1 crédito = 1 post). */
	public int getCreditosMensais()
	{
		return creditosMensais;
	}

	public void setCreditosMensais(int creditosMensais)
	{
		this.creditosMensais = creditosMensais;
	}

	/** Dias que o lote gerado fica disponível para download. */
	public int getDiasRetencao()
	{
		return diasRetencao;
	}

	public void setDiasRetencao(int diasRetencao)
	{
		this.diasRetencao = diasRetencao;
	}

	/** Quantidade de posts gerados por dia: um por exercício, dobrando quando gera feed e reel. */
	public int getPostsPorDia()
	{
		return ambosFormatos ? exerciciosPorDia * 2 : exerciciosPorDia;
	}

	public boolean equals(PerfilCriador x)
	{
		PerfilCriador modulo = (PerfilCriador) x;
		return this.nome.equals(modulo.nome);
	}

}
