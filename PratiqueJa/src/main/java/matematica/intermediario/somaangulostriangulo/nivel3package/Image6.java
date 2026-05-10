package matematica.intermediario.somaangulostriangulo.nivel3package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.expressao.MyExpression;
import modelo.matematica.Conta;


public class Image6 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image6(int index)
	{
		super(index);

		int a = 50 + rand.nextInt(30);
		int b = 180 - (2 * a);
		int x = 1 + rand.nextInt(20);
		int c = 1 + rand.nextInt(20);
		int e = 1 + rand.nextInt(20);

		while (e == c)
			e = 1 + rand.nextInt(20);

		int d = 180 - a - (c * x);
		int f = 180 - a - (e * x);

		MyExpression expressao = new MyExpression(c + "x+" + d);
		String str1 = expressao.imprimir();

		expressao = new MyExpression(e + "x+" + f);
		String str2 = expressao.imprimir();

//		6- Triangulo6 D, E | 
		ConfigTriangulo6 config = new ConfigTriangulo6(a, b, a, 180 - a, 180 - a);
		config.d.mostrar();
		config.e.mostrar();
		config.d.nome = str1;
		config.e.nome = str2;

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + x + "°";
		MyExpression resolucao = new MyExpression(str2 + "=" + str1);
		resolucaoLatex = resolucao.resolverLatex();

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image6(1);
	}
}
