package matematica.intermediario.anguloinscritocircunferencia.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.expressao.MyExpression;
import matematica.intermediario.anguloinscritocircunferencia.config.Config1;

public class Image6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 70 + rand.nextInt(40);
		int x = a;

		String strA = a + "°";

		String resultadoCorreto = "" + x + "°";

		MyExpression expressao = new MyExpression("x=" + a);
		String resolucao = expressao.resolverLatex();

		Config1 config = new Config1("", "x", strA);
		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + resolucao + "\\)");
	}
}
