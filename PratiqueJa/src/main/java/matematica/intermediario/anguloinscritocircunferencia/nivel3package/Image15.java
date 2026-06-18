package matematica.intermediario.anguloinscritocircunferencia.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.expressao.MyExpression;
import matematica.intermediario.anguloinscritocircunferencia.config.Config4;

public class Image15 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 90 + rand.nextInt(20);
		int b = 30 + rand.nextInt(30);
		int c = (2 * a) - (2 * b);

		String strB = b + "°";
		String strC = c + "°";

		String resultadoCorreto = "" + a + "°";

		MyExpression expressao = new MyExpression(c + "+2*+" + b + "=2x");
		String resolucao = expressao.resolverLatex();

		Config4 config = new Config4("x", strB, strC);
		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
