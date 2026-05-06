package matematica.intermediario.somaangulostriangulo.nivel2package;

import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.intermediario.somaangulostriangulo.ResolucaoSAT2;
import modelo.matematica.Conta;


public class Image26 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image26(int index)
	{
		super(index);

		int a = 30 + rand.nextInt(10);
		int d = 30 + rand.nextInt(10);
		int b = 90 - a;
		int c = 180 - b;
		int g = 90 - d;
		int e = b - d;
		int f = 90 - e - a;

//		26- Retangulo a, D, e | b, c
		ConfigRetangulo config = new ConfigRetangulo(a, b, c, d, e, f, g);
		config.a.mostrar();
		config.d.mostrar();
		config.e.mostrar();
		config.d.nome = "x";

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + d + "°";
		resolucaoLatex = resolucao(a, b, c, d, e, f, g);

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		config.b.mostrar();
		config.b.nome = "y";

		config.c.mostrar();
		config.c.nome = "z";

		BufferedImage imageResolucao = config.criarImagem(index);
		baosResolucao = Graphics.salvar(imageResolucao, false, "");

		carregarBlob();
	}

	public String resolucao(int a, int b, int c, int d, int e, int f, int g)
	{
		String resolucaoLatex = ResolucaoSAT2.complemento180("y", a, 90);
		resolucaoLatex += ResolucaoSAT2.complemento180("z", b);
		resolucaoLatex += ResolucaoSAT2.complemento180("x", c, e);

		return resolucaoLatex;
	}

	public static void main(String[] args)
	{
		new Image26(1);
	}
}
