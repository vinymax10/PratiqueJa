package matematica.intermediario.somaangulostriangulo.nivel3package;

import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.expressao.MyExpression;
import modelo.matematica.Conta;


public class Image18 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image18(int index)
	{
		super(index);

		int a = 35 + rand.nextInt(10);
		int x = 1 + rand.nextInt(20);
		int c = 1 + rand.nextInt(20);
		int b = 60 + rand.nextInt(20);
		int e = 180 - a - b;

		int d = a - (c * x);

		MyExpression expressao = new MyExpression(c + "x+" + d);
		String strA = expressao.imprimir();

//		18- Triangulo11 A, b, c | 
		ConfigTriangulo11 config = new ConfigTriangulo11(a, b, e);
		config.a.mostrar();
		config.b.mostrar();
		config.c.mostrar();
		config.a.nome = strA;

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + x + "°";

		MyExpression resolucao = new MyExpression(b + "+" + e + "+" + strA + "=180");
		resolucaoLatex = resolucao.resolverLatex();

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image18(1);
	}
}
