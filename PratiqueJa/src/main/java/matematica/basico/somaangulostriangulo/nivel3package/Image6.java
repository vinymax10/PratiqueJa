package matematica.basico.somaangulostriangulo.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.somaangulostriangulo.ResolucaoSAT2;
import matematica.expressao.MyExpression;

public class Image6 extends GeradorExercicio
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

		int d = 180 - a - (c * x);
		int f = 180 - a - (e * x);

		MyExpression expressao = new MyExpression(c + "x+" + d);
		String str1 = expressao.imprimir();

		expressao = new MyExpression(e + "x+" + f);
		String str2 = expressao.imprimir();

		ConfigTriangulo6 config = new ConfigTriangulo6(a, b, a, 180 - a, 180 - a);
		config.d.mostrar();
		config.e.mostrar();
		config.d.nome = str1;
		config.e.nome = str2;

		String resultadoCorreto = "" + x + "°";
		String resolucao = new MyExpression(str2 + "=" + str1).resolverLatex();

		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + ResolucaoSAT2.boldLastResult(resolucao) + "\\)");
	}
}
