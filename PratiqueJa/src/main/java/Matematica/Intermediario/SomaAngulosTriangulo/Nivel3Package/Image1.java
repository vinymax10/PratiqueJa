package Matematica.Intermediario.SomaAngulosTriangulo.Nivel3Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Expressao.MyExpression;
import Modelo.Matematica.Conta;


public class Image1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image1(int index)
	{
		super(index);

		int a = 25 + rand.nextInt(30);
		int x = 1 + rand.nextInt(20);
		int c = 1 + rand.nextInt(20);
		int b = 180 - (2 * a);

		int d = a - (c * x);
		MyExpression expressao = new MyExpression(c + "x+" + d);
		String str1 = expressao.imprimir();

//		1- Triangulo4 b, C | 
		ConfigTriangulo4 config = new ConfigTriangulo4(a, b, a);
		config.b.mostrar();
		config.c.mostrar();
		config.c.nome = str1;

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
		new Image1(1);
	}
}
