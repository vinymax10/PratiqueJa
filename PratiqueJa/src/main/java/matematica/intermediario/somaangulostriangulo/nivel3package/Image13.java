package matematica.intermediario.somaangulostriangulo.nivel3package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.expressao.MyExpression;
import modelo.matematica.Conta;


public class Image13 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image13(int index)
	{
		super(index);

		int a = 35 + rand.nextInt(10);
		int x = 1 + rand.nextInt(20);
		int c = 1 + rand.nextInt(20);
		int b = 60 + rand.nextInt(20);
		int e = 180 - a - b;

		int d = e - (c * x);
		MyExpression expressao = new MyExpression(c + "x+" + d);
		String strC = expressao.imprimir();

//		13- Triangulo9 a, b, C | 
		ConfigTriangulo9 config = new ConfigTriangulo9(a, b, e);
		config.a.mostrar();
		config.b.mostrar();
		config.c.mostrar();
		config.c.nome = strC;

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + x + "°";
		MyExpression resolucao = new MyExpression(a + "+" + b + "+" + strC + "=180");
		resolucaoLatex = resolucao.resolverLatex();

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image13(1);
	}
}
