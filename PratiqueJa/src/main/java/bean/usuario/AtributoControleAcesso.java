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

	private int valor;

	AtributoControleAcesso(int valor)
	{
		this.valor = valor;
	}

	public int getValor()
	{
		return valor;
	}

	public void setValor(int valor)
	{
		this.valor = valor;
	}

	public boolean equals(AtributoControleAcesso x)
	{
		AtributoControleAcesso modulo = (AtributoControleAcesso) x;
		return this.valor==modulo.valor;
	}

}
