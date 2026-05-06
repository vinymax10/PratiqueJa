package scraping;

import java.io.Serializable;

public class Paragrafo implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String texto;
	private String imagem;

	public String getTexto()
	{
		return texto;
	}

	public void setTexto(String texto)
	{
		this.texto = texto;
	}

	public String getImagem()
	{
		return imagem;
	}

	public void setImagem(String imagem)
	{
		this.imagem = imagem;
	}

	@Override
	public String toString()
	{
		if(imagem == null)
			return "\nParagrafo [texto=" + texto + "]";
		else
			return "\nParagrafo [imagem=" + imagem + "]";
	}

}
