package matematica.intermediario.somaangulostriangulo.nivel1package;

import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.intermediario.somaangulostriangulo.ResolucaoSAT2;
import matematica.intermediario.somaangulostriangulo.nivel2package.ConfigTrianguloBipartido1;
import modelo.matematica.Conta;


public class Image16 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image16(int index)
	{
		super(index);

		int b = 55 + rand.nextInt(15);
		int e = 10 + rand.nextInt(15);
		int f = 25 + rand.nextInt(15);
		int c = e + f;
		int a = 180 - b - c;
		int d = 180 - c;

//		16- TrianguloBipartido1 A, b, e, f | 
		ConfigTrianguloBipartido1 config = new ConfigTrianguloBipartido1(a, b, c, d, e, f);
		config.a.mostrar();
		config.b.mostrar();
		config.f.mostrar();
		config.e.mostrar();
		config.a.nome = "x";

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + a + "°";
		resolucaoLatex = ResolucaoSAT2.complemento180("x", b, e, f);

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image16(1);
	}
}
