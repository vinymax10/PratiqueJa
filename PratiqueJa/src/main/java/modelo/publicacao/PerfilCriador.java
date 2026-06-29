package modelo.publicacao;

public enum PerfilCriador
{
	// nome, intervalo (dias entre envios), exercicios por dia, gera os dois formatos (feed e reel),
	// créditos de post por mês, dias de retenção do download, renovavel, uploadBackground, legenda
	Teste("Teste", 1, 1, false, 10, 3, false, 0, "Curtas"),
	Master("Master", 1, 2, true, 120, 90, true, 30, "Personalizadas"),
	Premium("Premium", 1, 1, true, 60, 60, true, 10, "Com CTA"),
	Basico("Básico", 1, 1, false, 30, 30, true, 0, "Curtas");

	private String nome;
	private int intervalo;
	private int exerciciosPorDia;
	private boolean ambosFormatos;
	private int creditosMensais;
	private int diasRetencao;
	private boolean renovavel;
	private int uploadBackground;
	private String legenda;

	PerfilCriador(String nome, int intervalo, int exerciciosPorDia, boolean ambosFormatos, int creditosMensais, int diasRetencao, boolean renovavel, int uploadBackground, String legenda)
	{
		this.nome = nome;
		this.intervalo = intervalo;
		this.exerciciosPorDia = exerciciosPorDia;
		this.ambosFormatos = ambosFormatos;
		this.creditosMensais = creditosMensais;
		this.diasRetencao = diasRetencao;
		this.renovavel = renovavel;
		this.uploadBackground = uploadBackground;
		this.legenda = legenda;
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

	/** Créditos renovam mensalmente (false = cota total fixa, sem renovação). */
	public boolean isRenovavel()
	{
		return renovavel;
	}

	/** Quantidade de posts gerados por dia: um por exercício, dobrando quando gera feed e reel. */
	public int getPostsPorDia()
	{
		return ambosFormatos ? exerciciosPorDia * 2 : exerciciosPorDia;
	}

	/** Quantidade de imagens de background que o usuário pode fazer upload (0 = não disponível). */
	public int getUploadBackground()
	{
		return uploadBackground;
	}

	/** Tipo de legenda gerada: Curtas, Com CTA ou Personalizadas. */
	public String getLegenda()
	{
		return legenda;
	}

	public boolean equals(PerfilCriador x)
	{
		PerfilCriador modulo = (PerfilCriador) x;
		return this.nome.equals(modulo.nome);
	}

}
