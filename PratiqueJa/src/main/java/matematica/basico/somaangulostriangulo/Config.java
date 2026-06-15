package matematica.basico.somaangulostriangulo;

public abstract class Config
{
	protected static final int IMG_W = matematica.ConfigImagem.IMG_W;
	protected static final int IMG_H = matematica.ConfigImagem.IMG_H;

	int totalInsercao;

	public int incrementaInsecao()
	{
		return totalInsercao++;
	}

}
