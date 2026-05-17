package bean.usuario;

public enum AtributoControleAcesso
{
	numPaginasBaixadas(3),
	numResolucaoExercicio(4),
	numResolucaoQuestao(5),
	numQuestaoFeita(6);

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
