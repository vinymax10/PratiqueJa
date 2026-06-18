package matematica.intermediario.anguloinscritocircunferencia.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.expressao.MyExpression;
import matematica.intermediario.anguloinscritocircunferencia.config.Config1;

public class Image13 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int x = 30 + rand.nextInt(30);
		int a = x * 2;

		String strA = a + "°";

		String resultadoCorreto = "" + x + "°";

		MyExpression expressao = new MyExpression("x=" + a + "/2");
		String resolucao = expressao.resolverLatex();

		Config1 config = new Config1("x", "", strA);
		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
