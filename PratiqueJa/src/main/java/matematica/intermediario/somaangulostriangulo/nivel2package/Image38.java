package matematica.intermediario.somaangulostriangulo.nivel2package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.intermediario.somaangulostriangulo.ResolucaoSAT2;
import modelo.matematica.Conta;


public class Image38 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image38(int index)
	{
		super(index);

		int a = 40 + rand.nextInt(10);
		int b = 50 + rand.nextInt(10);
		int e = 40 + rand.nextInt(10);
		int c = 180 - a - b;
		int d = 180 - c;
		int f = 180 - b - e - a;

//		38- TrianguloBipartido2 c, e, F | d
		ConfigTrianguloBipartido2 config = new ConfigTrianguloBipartido2(a, b, c, d, e, f);
		config.c.mostrar();
		config.e.mostrar();
		config.f.mostrar();
		config.f.nome = "x";

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + f + "°";
		resolucaoLatex = resolucao(a, b, c, d, e, f);

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		config.d.mostrar();
		config.d.nome = "y";

		BufferedImage imageResolucao = config.criarImagem(index);
		baosResolucao = Graphics.salvar(imageResolucao, false, "");

		carregarBlob();
	}

	public String resolucao(int a, int b, int c, int d, int e, int f)
	{
		String resolucaoLatex = ResolucaoSAT2.complemento180("y", c);
		resolucaoLatex += ResolucaoSAT2.complemento180("x", e, d);

		return resolucaoLatex;
	}

	public static void main(String[] args)
	{
		new Image38(1);
	}
}
