package matematica.basico.somaangulostriangulo.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.expressao.MyExpression;

public class Image16 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 45 + rand.nextInt(10);
		int x = 1 + rand.nextInt(20);
		int c = 1 + rand.nextInt(20);
		int b = 20 + rand.nextInt(10);
		int e = 180 - a - b;

		int d = a - (c * x);
		MyExpression expressao = new MyExpression(c + "x+" + d);
		String strC = expressao.imprimir();

		ConfigTriangulo10 config = new ConfigTriangulo10(a, b, e, 180 - e);
		config.a.mostrar();
		config.b.mostrar();
		config.d.mostrar();
		config.a.nome = strC;

		String resultadoCorreto = "" + x + "°";
		String passo1 = new MyExpression((180 - e) + "+y=180").resolverLatex();
		String passo2 = new MyExpression(e + "+" + strC + "+" + b + "=180").resolverLatex();

		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + passo1 + "\\)");
		addResolucao("\\(" + passo2 + "\\)");
	}
}
