package matematica.basico.somaangulostriangulo.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.somaangulostriangulo.nivel1package.ConfigTrianguloRetangulo;
import matematica.expressao.MyExpression;

public class Image11 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 45 + rand.nextInt(15);
		int x = 1 + rand.nextInt(20);
		int c = 1 + rand.nextInt(20);
		int b = 90 - a;
		int d = b - (c * x);
		MyExpression expressao = new MyExpression(c + "x+" + d);
		String strB = expressao.imprimir();

		ConfigTrianguloRetangulo config = new ConfigTrianguloRetangulo(a, b);
		config.a.mostrar();
		config.b.mostrar();
		config.b.nome = strB;

		String resultadoCorreto = "" + x + "°";
		String resolucao = new MyExpression(strB + "+" + a + "+90=180").resolverLatex();

		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
