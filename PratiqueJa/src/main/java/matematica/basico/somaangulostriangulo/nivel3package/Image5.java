package matematica.basico.somaangulostriangulo.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.expressao.MyExpression;

public class Image5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 50 + rand.nextInt(30);
		int x = 1 + rand.nextInt(20);
		int c = 1 + rand.nextInt(20);
		int b = 180 - (2 * a);

		int d = 180 - a - (c * x);
		MyExpression expressao = new MyExpression(c + "x+" + d);
		String str1 = expressao.imprimir();

		ConfigTriangulo5 config = new ConfigTriangulo5(a, b, a, 180 - a);
		config.c.mostrar();
		config.d.mostrar();
		config.d.nome = str1;

		String resultadoCorreto = "" + x + "°";
		String resolucao = new MyExpression("y=" + a).resolverLatex();
		resolucao += "\\\\" + new MyExpression(a + "+" + str1 + "=180").resolverLatex();

		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
