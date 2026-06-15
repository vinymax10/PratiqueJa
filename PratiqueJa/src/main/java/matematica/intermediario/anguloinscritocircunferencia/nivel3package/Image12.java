package matematica.intermediario.anguloinscritocircunferencia.nivel3package;

import java.awt.image.BufferedImage;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.expressao.MyExpression;
import matematica.intermediario.anguloinscritocircunferencia.config.Config4;

public class Image12 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int x = 1 + rand.nextInt(20);
		int c = 1 + rand.nextInt(10);
		int a = 90 + rand.nextInt(20);   // ângulo inscrito lateralEsq: 90..109°
		int bVal = 30 + rand.nextInt(30); // ângulo inscrito centro: 30..59°
		int cArc = 2 * a - 2 * bVal;     // arco lateralDir: 62..158° (sempre > 0)
		int d = bVal - (c * x);

		String str1 = Auxiliar.getNumber(c, "x", true) + Auxiliar.getNumber(d, "", false);
		String strA = a + "°";
		String strC = cArc + "°";
		String resultadoCorreto = "" + x + "°";

		// Equação expandida: cArc + 2*(cx+d) = 2*a → 2c*x + (cArc + 2d) = 2*a
		int effC = 2 * c;
		int effD = cArc + 2 * d;
		String expandedStr = Auxiliar.getNumber(effC, "x", true) + Auxiliar.getNumber(effD, "", false);
		MyExpression expressao = new MyExpression(expandedStr + "=2*" + a);
		String resolucao = expressao.resolverLatex();

		Config4 config = new Config4(strA, str1, strC);
		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
