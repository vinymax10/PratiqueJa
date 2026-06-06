package matematica.basico.somaangulostriangulo.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.expressao.MyExpression;

public class Image2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 25 + rand.nextInt(30);
		int x = 1 + rand.nextInt(20);
		int c = 1 + rand.nextInt(20);
		int b = 180 - (2 * a);

		int d = a - (c * x);
		MyExpression expressao = new MyExpression(c + "x+" + d);
		String str1 = expressao.imprimir();

		ConfigTriangulo4 config = new ConfigTriangulo4(a, b, a);
		config.a.mostrar();
		config.b.mostrar();
		config.a.nome = str1;

		String texto = config.getTextLatex();
		String resultadoCorreto = "" + x + "°";
		String resolucao = new MyExpression(str1 + "+" + str1 + "+" + b + "=180").resolverLatex();

		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafo("\\(" + texto + "\\)");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
