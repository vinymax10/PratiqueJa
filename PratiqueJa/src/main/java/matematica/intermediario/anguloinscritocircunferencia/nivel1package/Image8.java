package matematica.intermediario.anguloinscritocircunferencia.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.expressao.MyExpression;
import matematica.intermediario.anguloinscritocircunferencia.config.Config2;

public class Image8 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 30 + rand.nextInt(30);
		int x = a;

		String strA = a + "°";

		String resultadoCorreto = "" + x + "°";

		MyExpression expressao = new MyExpression("x=" + a);
		String resolucao = expressao.resolverLatex();

		Config2 config = new Config2("x", strA);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
