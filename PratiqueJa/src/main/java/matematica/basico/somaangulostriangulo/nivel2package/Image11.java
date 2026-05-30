package matematica.basico.somaangulostriangulo.nivel2package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.somaangulostriangulo.ResolucaoSAT2;
import modelo.matematica.Conta;


public class Image11 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image11(int index)
	{
		super(index);

		int b = 55 + rand.nextInt(15);
		int e = 10 + rand.nextInt(15);
		int f = 25 + rand.nextInt(15);
		int c = e + f;
		int a = 180 - b - c;
		int d = 180 - c;

//		11- TrianguloBipartido1 c, e, F | d
		ConfigTrianguloBipartido1 config = new ConfigTrianguloBipartido1(a, b, c, d, e, f);
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
		new Image11(1);
	}
}
