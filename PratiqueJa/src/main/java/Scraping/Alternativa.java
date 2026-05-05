package Scraping;

import java.io.Serializable;

public class Alternativa implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String texto;
	private String imagem;

	private boolean correta;

	public String getTexto()
	{
		return texto;
	}

	public void setTexto(String texto)
	{
		this.texto = texto;
	}

	public boolean isCorreta()
	{
		return correta;
	}

	public void setCorreta(boolean correta)
	{
		this.correta = correta;
	}

	@Override
	public String toString()
	{
		return "\nAlternativa [texto=" + texto + ", imagem=" + imagem + ", correta=" + correta + "]";
	}

	public String getImagem()
	{
		return imagem;
	}

	public void setImagem(String imagem)
	{
		this.imagem = imagem;
	}

}
