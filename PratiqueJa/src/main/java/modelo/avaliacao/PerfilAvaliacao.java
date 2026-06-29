package modelo.avaliacao;

public enum PerfilAvaliacao
{
	Teste("Teste", 10, 3, 5, 10, false),
	Essencial("Essencial", 100, 7, 20, 30, true),
	Profissional("Profissional", 500, 30, 40, 60, true),
	Master("Master", 2000, 60, 60, 100, true);

	private final String nome;
	private final int limiteMensal;
	private final int diasRetencao;
	private final int maxExerciciosPorAvaliacao;
	private final int maxAvaliacoesPorSolicitacao;
	private final boolean renovavel;

	PerfilAvaliacao(String nome, int limiteMensal, int diasRetencao, int maxExerciciosPorAvaliacao, int maxAvaliacoesPorSolicitacao, boolean renovavel)
	{
		this.nome = nome;
		this.limiteMensal = limiteMensal;
		this.diasRetencao = diasRetencao;
		this.maxExerciciosPorAvaliacao = maxExerciciosPorAvaliacao;
		this.maxAvaliacoesPorSolicitacao = maxAvaliacoesPorSolicitacao;
		this.renovavel = renovavel;
	}

	public String getNome()
	{
		return nome;
	}

	public int getLimiteMensal()
	{
		return limiteMensal;
	}

	public int getDiasRetencao()
	{
		return diasRetencao;
	}

	public int getMaxExerciciosPorAvaliacao()
	{
		return maxExerciciosPorAvaliacao;
	}

	public int getMaxAvaliacoesPorSolicitacao()
	{
		return maxAvaliacoesPorSolicitacao;
	}

	/** Limite renova mensalmente (false = cota total fixa, sem renovação). */
	public boolean isRenovavel()
	{
		return renovavel;
	}
}
