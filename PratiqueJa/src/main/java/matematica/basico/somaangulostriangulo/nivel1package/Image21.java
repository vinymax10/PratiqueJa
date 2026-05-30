package matematica.basico.somaangulostriangulo.nivel1package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.basico.somaangulostriangulo.ResolucaoSAT2;
import matematica.basico.somaangulostriangulo.nivel2package.ConfigTrianguloBipartido2;
import modelo.matematica.Conta;


public class Image21 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image21(int index)
	{
		super(index);

		int a = 40 + rand.nextInt(10);
		int b = 50 + rand.nextInt(10);
		int e = 40 + rand.nextInt(10);
		int c = 180 - a - b;
		int d = 180 - c;
		int f = 180 - b - e - a;

//		21- TrianguloBipartido2 a, b, E, f | 
		ConfigTrianguloBipartido2 config = new ConfigTrianguloBipartido2(a, b, c, d, e, f);
		config.f.mostrar();
		config.e.mostrar();
		config.b.mostrar();
		config.a.mostrar();
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
		new Image21(1);
	}
}
