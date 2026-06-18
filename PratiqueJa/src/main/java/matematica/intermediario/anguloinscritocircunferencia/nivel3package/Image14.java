package matematica.intermediario.anguloinscritocircunferencia.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.expressao.MyExpression;
import matematica.intermediario.anguloinscritocircunferencia.config.Config4;

public class Image14 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 90 + rand.nextInt(20);
		int b = 30 + rand.nextInt(30);
		int c = (2 * a) - (2 * b);

		String strA = a + "°";
		String strC = c + "°";

		String resultadoCorreto = "" + b + "°";

		MyExpression expressao = new MyExpression(c + "+2x=2*" + a);
		String resolucao = expressao.resolverLatex();

		Config4 config = new Config4(strA, "x", strC);
		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
