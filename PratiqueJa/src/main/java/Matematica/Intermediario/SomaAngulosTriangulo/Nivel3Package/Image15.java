package Matematica.Intermediario.SomaAngulosTriangulo.Nivel3Package;

import java.awt.image.BufferedImage;

import Auxiliar.Graphics;
import Matematica.Expressao.MyExpression;
import Modelo.Matematica.Conta;


public class Image15 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image15(int index)
	{
		super(index);

		int a = 45 + rand.nextInt(10);
		int x = 1 + rand.nextInt(20);
		int c = 1 + rand.nextInt(20);
		int b = 20 + rand.nextInt(10);
		int e = 180 - a - b;

		int d = 180 - e - (c * x);
		MyExpression expressao = new MyExpression(c + "x+" + d);
		String strC = expressao.imprimir();

//		15- Triangulo10 a, c, D | 
		ConfigTriangulo10 config = new ConfigTriangulo10(a, b, e, 180 - e);
		config.a.mostrar();
		config.b.mostrar();
		config.d.mostrar();
		config.d.nome = strC;

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + x + "°";
		MyExpression resolucao = new MyExpression(a + "+" + b + "+y=180");
		resolucaoLatex = resolucao.resolverLatex();

		resolucao = new MyExpression(e + "+" + strC + "=180");
		resolucaoLatex += "\\\\" + resolucao.resolverLatex();

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		config.c.mostrar();
		config.c.nome = "y";

		BufferedImage imageResolucao = config.criarImagem(index);
		baosResolucao = Graphics.salvar(imageResolucao, false, "");

		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image15(1);
	}
}
