package matematica.basico.somaangulostriangulo.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.expressao.MyExpression;

public class Image12 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 35 + rand.nextInt(20);
		int x = 1 + rand.nextInt(20);
		int c = 1 + rand.nextInt(20);
		int b = 90 - a;
		int d = 180 - b - (c * x);

		MyExpression expressao = new MyExpression(c + "x+" + d);
		String strC = expressao.imprimir();

		ConfigTriangulo8 config = new ConfigTriangulo8(a, b, 180 - b);
		config.a.mostrar();
		config.c.mostrar();
		config.c.nome = strC;

		String resultadoCorreto = "" + x + "°";
		String resolucao = new MyExpression("y+" + a + "+90=180").resolverLatex();
		resolucao += "\\\\" + new MyExpression(b + "+" + strC + "=180").resolverLatex();

		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
