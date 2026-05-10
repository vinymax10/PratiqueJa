package matematica.intermediario.somaangulostriangulo.nivel2package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.intermediario.somaangulostriangulo.ResolucaoSAT2;
import modelo.matematica.Conta;


public class Image2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image2(int index)
	{
		super(index);

		int b = 60 + rand.nextInt(20);
		int e = 40 + rand.nextInt(20);
		int d = 90 - e;
		int c = d;
		int a = 180 - b - d;

//		2- Trapezio a, B, c | d, e
		ConfigTrapezio config = new ConfigTrapezio(a, b, c, d, e);
		config.a.mostrar();
		config.b.mostrar();
		config.c.mostrar();
		config.b.nome = "x";

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + b + "°";
		resolucaoLatex = resolucao(a, b, c, d, e);

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		config.d.mostrar();
		config.d.nome = "y";

		config.e.mostrar();
		config.e.nome = "z";

		BufferedImage imageResolucao = config.criarImagem(index);
		baosResolucao = Graphics.salvar(imageResolucao, false, "");

		carregarBlob();
	}

	public String resolucao(int a, int b, int c, int d, int e)
	{
		String resolucaoLatex = ResolucaoSAT2.complemento180("z", c, 90);
		resolucaoLatex += ResolucaoSAT2.complemento90("y", e);
		resolucaoLatex += ResolucaoSAT2.complemento180("x", a, d);

		return resolucaoLatex;
	}

	public static void main(String[] args)
	{
		new Image2(1);
	}
}
