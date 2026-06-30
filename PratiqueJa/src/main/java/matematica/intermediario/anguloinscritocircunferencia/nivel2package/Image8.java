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
		int lastSep = resolucao.lastIndexOf("\\\\");
		String ultimoPasso = (lastSep >= 0) ? resolucao.substring(lastSep + 2).trim() : resolucao.trim();
		int lastEq = ultimoPasso.lastIndexOf('=');
		if (lastEq >= 0)
		{
			String boldado = ultimoPasso.substring(0, lastEq + 1) + "\\mathbf{" + ultimoPasso.substring(lastEq + 1).trim() + "}";
			resolucao = (lastSep >= 0) ? resolucao.substring(0, lastSep + 2) + boldado : boldado;
		}

		addParagrafo("Um ponto \\(P\\) sobre uma circunferência forma dois arcos com as extremidades de um diâmetro. Os arcos medem \\(" + str1 + "\\) e \\(" + strA + "\\). Encontre \\(x\\).");

		gerarAlternativas(resultadoCorreto);

		addResolucao("Os dois arcos formados pelo diâmetro e o ponto \\(P\\) somam \\(180^\\circ\\):");
		addResolucao("\\(" + resolucao + "\\)");
	}
}
