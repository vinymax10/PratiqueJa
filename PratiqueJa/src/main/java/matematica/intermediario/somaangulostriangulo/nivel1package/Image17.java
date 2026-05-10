package matematica.intermediario.somaangulostriangulo.nivel1package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.intermediario.somaangulostriangulo.ResolucaoSAT2;
import matematica.intermediario.somaangulostriangulo.nivel2package.ConfigTrianguloBipartido1;
import modelo.matematica.Conta;


public class Image17 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image17(int index)
	{
		super(index);

		int b = 55 + rand.nextInt(15);
		int e = 10 + rand.nextInt(15);
		int f = 25 + rand.nextInt(15);
		int c = e + f;
		int a = 180 - b - c;
		int d = 180 - c;

//		17- TrianguloBipartido1 a, B, e, f | 
		ConfigTrianguloBipartido1 config = new ConfigTrianguloBipartido1(a, b, c, d, e, f);
		config.a.mostrar();
		config.b.mostrar();
		config.f.mostrar();
		config.e.mostrar();
		config.b.nome = "x";

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + b + "°";
		resolucaoLatex = ResolucaoSAT2.complemento180("x", a, e, f);

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image17(1);
	}
}
