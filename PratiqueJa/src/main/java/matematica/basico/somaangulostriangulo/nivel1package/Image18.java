package matematica.basico.somaangulostriangulo.nivel1package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.somaangulostriangulo.ResolucaoSAT2;
import matematica.basico.somaangulostriangulo.nivel2package.ConfigTrianguloBipartido1;
import modelo.matematica.Conta;


public class Image18 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image18(int index)
	{
		super(index);

		int b = 55 + rand.nextInt(15);
		int e = 10 + rand.nextInt(15);
		int f = 25 + rand.nextInt(15);
		int c = e + f;
		int a = 180 - b - c;
		int d = 180 - c;

//		18- TrianguloBipartido1 a, b, E, f | 
		ConfigTrianguloBipartido1 config = new ConfigTrianguloBipartido1(a, b, c, d, e, f);
		config.a.mostrar();
		config.b.mostrar();
		config.f.mostrar();
		config.e.mostrar();
		config.e.nome = "x";

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + e + "°";
		resolucaoLatex = ResolucaoSAT2.complemento180("x", a, b, f);

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image18(1);
	}
}
