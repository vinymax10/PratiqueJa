package matematica.intermediario.anguloinscritocircunferencia.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.expressao.MyExpression;
import matematica.intermediario.anguloinscritocircunferencia.config.Config3;

public class Image4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int x = 45 + rand.nextInt(30);
		int a = (180 - x) / 2;

		String strA = a + "°";

		String resultadoCorreto = "" + x + "°";

		MyExpression expressao = new MyExpression("2*" + a + "+x=180");
		String resolucao = expressao.resolverLatex();

		Config3 config = new Config3(strA, "x");
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
