package matematica.intermediario.anguloinscritocircunferencia.nivel2package;

import java.awt.image.BufferedImage;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.expressao.MyExpression;
import matematica.intermediario.anguloinscritocircunferencia.config.Config2;

public class Image6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int x = 1 + rand.nextInt(20);
		int c = 1 + rand.nextInt(20);
		int a = 30 + rand.nextInt(60); // ângulo inscrito conhecido: 30..89°
		int d = a - (c * x);

		String str1 = Auxiliar.getNumber(c, "x", true) + Auxiliar.getNumber(d, "", false);
		String strA = a + "°";
		String resultadoCorreto = "" + x + "°";

		MyExpression expressao = new MyExpression(str1 + "=" + a);
		String resolucao = expressao.resolverLatex();

		Config2 config = new Config2(strA, str1);
		BufferedImage image = config.criarImagem();

		addParagrafo("Encontre o valor de \\(x\\):");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);

		String res = "Ângulos inscritos que subtendem o mesmo arco são iguais:";
		res += "\\(\\\\\\)";
		res += "\\(" + resolucao + "\\)";
		setResolucao(res);
	}
}
