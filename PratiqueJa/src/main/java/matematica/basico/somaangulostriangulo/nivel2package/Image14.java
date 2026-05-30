package matematica.basico.somaangulostriangulo.nivel2package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.somaangulostriangulo.ResolucaoSAT2;
import modelo.matematica.Conta;


public class Image14 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image14(int index)
	{
		super(index);

		int a = 30 + rand.nextInt(10);
		int d = 30 + rand.nextInt(10);
		int b = 90 - a;
		int c = 180 - b;
		int g = 90 - d;
		int e = b - d;
		int f = 90 - e - a;

//		14- Retangulo C, e, g | d
		ConfigRetangulo config = new ConfigRetangulo(a, b, c, d, e, f, g);
		config.c.mostrar();
		config.e.mostrar();
		config.g.mostrar();
		config.c.nome = "x";

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + c + "°";
		resolucaoLatex = resolucao(a, b, c, d, e, f, g);

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		config.d.mostrar();
		config.d.nome = "y";

		BufferedImage imageResolucao = config.criarImagem(index);
		baosResolucao = Graphics.salvar(imageResolucao, false, "");

		carregarBlob();
	}

	public String resolucao(int a, int b, int c, int d, int e, int f, int g)
	{
		String resolucaoLatex = ResolucaoSAT2.complemento90("y", g);
		resolucaoLatex += ResolucaoSAT2.complemento180("x", d, e);

		return resolucaoLatex;
	}

	public static void main(String[] args)
	{
		new Image14(1);
	}
}
