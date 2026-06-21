package modelo.avaliacao;

public enum PlanoAvaliacao
{
	ESSENCIAL("Essencial", 100, 7, 20, 30),
	PROFISSIONAL("Profissional", 500, 30, 40, 60),
	MASTER("Master", 2000, 60, 60, 100);

	private final String nome;
	private final int limiteMensal;
	private final int diasRetencao;
	private final int maxExerciciosPorAvaliacao;
	private final int maxAvaliacoesPorSolicitacao;

	PlanoAvaliacao(String nome, int limiteMensal, int diasRetencao, int maxExerciciosPorAvaliacao, int maxAvaliacoesPorSolicitacao)
	{
		this.nome = nome;
		this.limiteMensal = limiteMensal;
		this.diasRetencao = diasRetencao;
		this.maxExerciciosPorAvaliacao = maxExerciciosPorAvaliacao;
		this.maxAvaliacoesPorSolicitacao = maxAvaliacoesPorSolicitacao;
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
}
