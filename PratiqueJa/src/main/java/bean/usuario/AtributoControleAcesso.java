package bean.usuario;

public enum AtributoControleAcesso
{
	numDownloadExercicio(4),
	numDownloadQuestao(5),
	numDownloadQuestaoMassa(6),
	numDownloadMassa(7),
	numResolucaoExercicio(8),
	numResolucaoQuestao(9),
	numQuestaoFeita(10);

	private final int valor;

	AtributoControleAcesso(int valor)
	{
		this.valor = valor;
	}

	public int getValor()
	{
		return valor;
	}
}
