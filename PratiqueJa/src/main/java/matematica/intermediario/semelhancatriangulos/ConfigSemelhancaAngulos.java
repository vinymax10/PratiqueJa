package matematica.intermediario.semelhancatriangulos;

import java.awt.image.BufferedImage;

import matematica.ConfigImagem;

public abstract class ConfigSemelhancaAngulos implements ConfigImagem
{
	public Arco a, b, c, d;

	public ConfigSemelhancaAngulos(ConfigValores configValores)
	{
		this.a = new Arco(configValores.a, configValores.a.toStringLatex());
		this.b = new Arco(configValores.b, configValores.b.toStringLatex());
		this.c = new Arco(configValores.c, configValores.c.toStringLatex());
		this.d = new Arco(configValores.d, configValores.d.toStringLatex());
		
		if(configValores.incognita.equals(configValores.a))
			a.nome = configValores.nome;
		else if(configValores.incognita.equals(configValores.b))
			b.nome = configValores.nome;
		else if(configValores.incognita.equals(configValores.c))
			c.nome = configValores.nome;
		else if(configValores.incognita.equals(configValores.d))
			d.nome = configValores.nome;
	}

	public BufferedImage criarImagem()
	{
		return null;
	}

	@Override
	public String toString()
	{
		return "ConfigTriangulo1:\n" + (a != null ? "a: " + a + "\n" : "") + (b != null ? "b: " + b + "\n" : "")
		+ (c != null ? "c: " + c : "");
	}

}
