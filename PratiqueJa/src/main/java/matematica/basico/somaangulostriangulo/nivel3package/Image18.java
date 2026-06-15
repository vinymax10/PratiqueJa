package matematica.basico.somaangulostriangulo.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.expressao.MyExpression;

public class Image18 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 35 + rand.nextInt(10);
		int x = 1 + rand.nextInt(20);
		int c = 1 + rand.nextInt(20);
		int b = 60 + rand.nextInt(20);
		int e = 180 - a - b;

		int d = a - (c * x);

		MyExpression expressao = new MyExpression(c + "x+" + d);
		String strA = expressao.imprimir();

		ConfigTriangulo11 config = new ConfigTriangulo11(a, b, e);
		config.a.mostrar();
		config.b.mostrar();
		config.c.mostrar();
		config.a.nome = strA;

		String resultadoCorreto = "" + x + "°";
		String resolucao = new MyExpression(b + "+" + e + "+" + strA + "=180").resolverLatex();

		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
