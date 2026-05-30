package matematica.basico.somaangulostriangulo.nivel2package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.somaangulostriangulo.ResolucaoSAT2;
import modelo.matematica.Conta;


public class Image12 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image12(int index)
	{
		super(index);

		int b = 55 + rand.nextInt(15);
		int e = 10 + rand.nextInt(15);
		int f = 25 + rand.nextInt(15);
		int c = e + f;
		int a = 180 - b - c;
		int d = 180 - c;

//		12- TrianguloBipartido1 c, E, f | d
		ConfigTrianguloBipartido1 config = new ConfigTrianguloBipartido1(a, b, c, d, e, f);
		config.c.mostrar();
		config.e.mostrar();
		config.f.mostrar();
		config.e.nome = "x";

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + e + "°";
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
		resolucaoLatex += ResolucaoSAT2.complemento180("x", f, d);

		return resolucaoLatex;
	}

	public static void main(String[] args)
	{
		new Image12(1);
	}
}
