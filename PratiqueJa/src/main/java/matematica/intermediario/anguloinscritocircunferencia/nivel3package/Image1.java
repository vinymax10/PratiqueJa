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
		int lastSep = resolucao.lastIndexOf("\\\\");
		String ultimoPasso = (lastSep >= 0) ? resolucao.substring(lastSep + 2).trim() : resolucao.trim();
		int lastEq = ultimoPasso.lastIndexOf('=');
		if (lastEq >= 0)
		{
			String boldado = ultimoPasso.substring(0, lastEq + 1) + "\\mathbf{" + ultimoPasso.substring(lastEq + 1).trim() + "}";
			resolucao = (lastSep >= 0) ? resolucao.substring(0, lastSep + 2) + boldado : boldado;
		}

		Config4 config = new Config4(strA, strB, "x");
		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + resolucao + "\\)");
	}
}
