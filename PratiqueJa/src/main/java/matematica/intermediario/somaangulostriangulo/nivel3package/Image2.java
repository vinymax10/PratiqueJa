package matematica.intermediario.somaangulostriangulo.nivel3package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.expressao.MyExpression;
import modelo.matematica.Conta;

public class Image2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image2(int index)
	{
		super(index);

		int a = 25 + rand.nextInt(30);
		int x = 1 + rand.nextInt(20);
		int c = 1 + rand.nextInt(20);
		int b = 180 - (2 * a);

		int d = a - (c * x);
		MyExpression expressao = new MyExpression(c + "x+" + d);
		String str1 = expressao.imprimir();

//		2- Triangulo4 A, b | 
		ConfigTriangulo4 config = new ConfigTriangulo4(a, b, a);
		config.a.mostrar();
		config.b.mostrar();
		config.a.nome = str1;

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + x + "°";
		MyExpression resolucao = new MyExpression(str1 + "+" + str1 + "+" + b + "=180");
		resolucaoLatex = resolucao.resolverLatex();

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image2(1);
	}
}
