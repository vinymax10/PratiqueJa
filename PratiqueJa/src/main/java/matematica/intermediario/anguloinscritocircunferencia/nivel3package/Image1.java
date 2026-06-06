package matematica.intermediario.anguloinscritocircunferencia.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.expressao.MyExpression;
import matematica.intermediario.anguloinscritocircunferencia.config.Config4;

public class Image1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 90 + rand.nextInt(20);
		int b = 30 + rand.nextInt(30);
		int c = (2 * a) - (2 * b);

		String strA = a + "°";
		String strB = b + "°";

		String resultadoCorreto = "" + c + "°";

		MyExpression expressao = new MyExpression("x+2*" + b + "=2*" + a);
		String resolucao = expressao.resolverLatex();

		Config4 config = new Config4(strA, strB, "x");
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
