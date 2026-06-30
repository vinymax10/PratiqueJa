package matematica.intermediario.anguloinscritocircunferencia.nivel2package;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.expressao.MyExpression;

public class Image7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int x = 1 + rand.nextInt(20);
		int c = 1 + rand.nextInt(10);
		int a = 10 + rand.nextInt(40); // ângulo agudo dado: 10..49°
		int outroAngulo = 90 - a;      // outro ângulo agudo = cx+d
		int d = outroAngulo - (c * x);

		String str1 = Auxiliar.getNumber(c, "x", true) + Auxiliar.getNumber(d, "", false);
		String resultadoCorreto = "" + x + "°";

		MyExpression expressao = new MyExpression(str1 + "+" + a + "+90=180");
		String resolucao = expressao.resolverLatex();
		int lastSep = resolucao.lastIndexOf("\\\\");
		String ultimoPasso = (lastSep >= 0) ? resolucao.substring(lastSep + 2).trim() : resolucao.trim();
		int lastEq = ultimoPasso.lastIndexOf('=');
		if (lastEq >= 0)
		{
			String boldado = ultimoPasso.substring(0, lastEq + 1) + "\\mathbf{" + ultimoPasso.substring(lastEq + 1).trim() + "}";
			resolucao = (lastSep >= 0) ? resolucao.substring(0, lastSep + 2) + boldado : boldado;
		}

		addParagrafo("Um triângulo retângulo está inscrito numa semicircunferência, com a hipotenusa como diâmetro. Os ângulos agudos medem \\(" + a + "^\\circ\\) e \\(" + str1 + "\\). Encontre \\(x\\).");

		gerarAlternativas(resultadoCorreto);

		addResolucao("Pelo Teorema de Tales, o ângulo oposto ao diâmetro é \\(90^\\circ\\). Pela soma dos ângulos do triângulo:");
		addResolucao("\\(" + resolucao + "\\)");
	}
}
