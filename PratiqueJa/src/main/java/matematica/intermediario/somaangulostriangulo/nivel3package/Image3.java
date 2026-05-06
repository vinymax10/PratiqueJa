package matematica.intermediario.somaangulostriangulo.nivel3package;

import java.awt.image.BufferedImage;

import auxiliar.Graphics;
import matematica.expressao.MyExpression;
import modelo.matematica.Conta;


public class Image3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image3(int index)
	{
		super(index);

		int a = 25 + rand.nextInt(30);
		int x = 1 + rand.nextInt(20);
		int c = 1 + rand.nextInt(20);
		int b = 180 - (2 * a);

		int d = a - (c * x);
		MyExpression expressao = new MyExpression(c + "x+" + d);
		String str1 = expressao.imprimir();

//		3- Triangulo4 A, c | 
		ConfigTriangulo4 config = new ConfigTriangulo4(a, b, a);
		config.a.mostrar();
		config.c.mostrar();
		config.a.nome = str1;

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + x + "°";
		MyExpression resolucao = new MyExpression(str1 + "=" + a);
		resolucaoLatex = resolucao.resolverLatex();

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image3(1);
	}
}
