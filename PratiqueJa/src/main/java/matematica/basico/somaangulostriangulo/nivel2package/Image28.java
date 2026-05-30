package matematica.basico.somaangulostriangulo.nivel2package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.somaangulostriangulo.ResolucaoSAT2;
import modelo.matematica.Conta;


public class Image28 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image28(int index)
	{
		super(index);

		int a = 30 + rand.nextInt(10);
		int d = 30 + rand.nextInt(10);
		int b = 90 - a;
		int c = 180 - b;
		int g = 90 - d;
		int e = b - d;
		int f = 90 - e - a;

//		28- Retangulo b, E, f | a
		ConfigRetangulo config = new ConfigRetangulo(a, b, c, d, e, f, g);
		config.b.mostrar();
		config.e.mostrar();
		config.f.mostrar();
		config.e.nome = "x";

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + e + "°";
		resolucaoLatex = resolucao(a, b, c, d, e, f, g);

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		config.a.mostrar();
		config.a.nome = "y";

		BufferedImage imageResolucao = config.criarImagem(index);
		baosResolucao = Graphics.salvar(imageResolucao, false, "");

		carregarBlob();
	}

	public String resolucao(int a, int b, int c, int d, int e, int f, int g)
	{
		String resolucaoLatex = ResolucaoSAT2.complemento180("y", b, 90);
		resolucaoLatex += ResolucaoSAT2.complemento90("x", a, f);

		return resolucaoLatex;
	}

	public static void main(String[] args)
	{
		new Image28(1);
	}
}
