package matematica.intermediario.anguloinscritocircunferencia.nivel2package;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.expressao.MyExpression;

public class Image8 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int x = 1 + rand.nextInt(20);
		int c = 1 + rand.nextInt(15);
		int a = 30 + rand.nextInt(100); // arco conhecido: 30..129°
		int arcoExpr = 180 - a;         // arco com expressão: 51..150°
		int d = arcoExpr - (c * x);

		String str1 = Auxiliar.getNumber(c, "x", true) + Auxiliar.getNumber(d, "", false);
		String strA = a + "°";
		String resultadoCorreto = "" + x + "°";

		MyExpression expressao = new MyExpression(str1 + "+" + a + "=180");
		String resolucao = expressao.resolverLatex();

		addParagrafo("Um ponto \\(P\\) sobre uma circunferência forma dois arcos com as extremidades de um diâmetro. Os arcos medem \\(" + str1 + "\\) e \\(" + strA + "\\). Encontre \\(x\\).");

		gerarAlternativas(resultadoCorreto);

		String res = "Os dois arcos formados pelo diâmetro e o ponto \\(P\\) somam \\(180^\\circ\\):";
		res += "\\(\\\\\\)";
		res += "\\(" + resolucao + "\\)";
		setResolucao(res);
	}
}
