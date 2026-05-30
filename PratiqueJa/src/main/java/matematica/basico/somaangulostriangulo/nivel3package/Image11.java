package matematica.basico.somaangulostriangulo.nivel3package;

import java.awt.image.BufferedImage;

import infra.Graphics;
import matematica.expressao.MyExpression;
import matematica.basico.somaangulostriangulo.nivel1package.ConfigTrianguloRetangulo;
import modelo.matematica.Conta;


public class Image11 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Image11(int index)
	{
		super(index);

		int a = 45 + rand.nextInt(15);
		int x = 1 + rand.nextInt(20);
		int c = 1 + rand.nextInt(20);
		int b = 90 - a;
		int d = b - (c * x);
		MyExpression expressao = new MyExpression(c + "x+" + d);
		String strB = expressao.imprimir();

//		11- Triangulo7 a, B | 
		ConfigTrianguloRetangulo config = new ConfigTrianguloRetangulo(a, b);
		config.a.mostrar();
		config.b.mostrar();
		config.b.nome = strB;

		textLatex = config.getTextLatex();
		resultadoCorreto = "" + x + "°";
		MyExpression resolucao = new MyExpression(strB + "+" + a + "+90=180");
		resolucaoLatex = resolucao.resolverLatex();

		BufferedImage image = config.criarImagem(index);
		baos = Graphics.salvar(image, false, "");

		carregarBlob();
	}

	public static void main(String[] args)
	{
		new Image11(1);
	}
}
