package matematica.intermediario.somaangulostriangulo.nivel2package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.intermediario.somaangulostriangulo.ResolucaoSAT2;
import modelo.matematica.Conta;


public class Image6 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image6(int index)
	{
		super(index);

		int b = 60 + rand.nextInt(20);
		int e = 40 + rand.nextInt(20);
		int d = 90 - e;
		int c = d;
		int a = 180 - b - d;

//		6- Trapezio c, D | e
		ConfigTrapezio config = new ConfigTrapezio(a, b, c, d, e);
		config.c.mostrar();
		config.d.mostrar();
		config.d.nome = "x";

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + d + "°";
		resolucaoLatex = resolucao(a, b, c, d, e);

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		config.e.mostrar();
		config.e.nome = "y";

		BufferedImage imageResolucao = config.criarImagem(index);
		baosResolucao = Graphics.salvar(imageResolucao, false, "");

		carregarBlob();
	}

	public String resolucao(int a, int b, int c, int d, int e)
	{
		resolucaoLatex = ResolucaoSAT2.complemento180("y", c, 90);
		resolucaoLatex += ResolucaoSAT2.complemento90("x", d);

		return resolucaoLatex;
	}

	public static void main(String[] args)
	{
		new Image6(1);
	}
}
