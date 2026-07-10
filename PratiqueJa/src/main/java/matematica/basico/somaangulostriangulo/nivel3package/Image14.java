package matematica.basico.somaangulostriangulo.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.somaangulostriangulo.ResolucaoSAT2;
import matematica.expressao.MyExpression;

public class Image14 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 35 + rand.nextInt(10);
		int x = 1 + rand.nextInt(20);
		int c = 1 + rand.nextInt(20);
		int b = 60 + rand.nextInt(20);
		int e = 180 - a - b;

		int d = b - (c * x);
		MyExpression expressao = new MyExpression(c + "x+" + d);
		String strC = expressao.imprimir();

		ConfigTriangulo9 config = new ConfigTriangulo9(a, b, e);
		config.a.mostrar();
		config.b.mostrar();
		config.c.mostrar();
		config.b.nome = strC;

		String resultadoCorreto = "" + x + "°";
		String resolucao = new MyExpression(a + "+" + e + "+" + strC + "=180").resolverLatex();

		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + ResolucaoSAT2.boldLastResult(resolucao) + "\\)");
	}
}
