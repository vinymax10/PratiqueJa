package matematica.basico.somaangulostriangulo.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.expressao.MyExpression;

public class Image8 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 50 + rand.nextInt(30);
		int b = 180 - (2 * a);
		int x = 1 + rand.nextInt(20);
		int c = 1 + rand.nextInt(20);
		int e = 1 + rand.nextInt(20);

		while(e == c)
			e = 1 + rand.nextInt(20);

		int f = 180 - a - (e * x);

		MyExpression expressao = new MyExpression(e + "x+" + f);
		String strE = expressao.imprimir();

		ConfigTriangulo6 config = new ConfigTriangulo6(a, b, a, 180 - a, 180 - a);
		config.e.mostrar();
		config.c.mostrar();
		config.e.nome = strE;

		String resultadoCorreto = "" + x + "°";
		String resolucao = new MyExpression("y=" + a).resolverLatex();
		resolucao += "\\\\" + new MyExpression(a + "+" + strE + "=180").resolverLatex();

		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
