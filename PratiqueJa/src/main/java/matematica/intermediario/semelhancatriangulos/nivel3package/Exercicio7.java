package matematica.intermediario.semelhancatriangulos.nivel3package;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.expressao.MyExpression;

public class Exercicio7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int x = 1 + rand.nextInt(10);
		int c = 1 + rand.nextInt(5);
		int d = 1 + rand.nextInt(10);
		int p = 2 + rand.nextInt(3);
		int L = c * x + d;
		int M = L * p;

		String ladoMenor = Auxiliar.getNumber(c, "x", true) + Auxiliar.getNumber(d, "", false);

		int effC = p * c;
		int effD = p * d;
		int rhs = p * L;
		MyExpression expressao = new MyExpression(
				Auxiliar.getNumber(effC, "x", true) + Auxiliar.getNumber(effD, "", false) + "=" + rhs);
		String resolucao = expressao.resolverLatex();

		addParagrafo("Dois triângulos são semelhantes com razão de semelhança \\(k = " + p + "\\). "
				+ "O lado correspondente do triângulo menor mede \\(" + ladoMenor + "\\) "
				+ "e do triângulo maior mede \\(" + M + "\\). "
				+ "Encontre o valor de \\(x\\).");

		gerarAlternativas(String.valueOf(x));

		String res = "A proporção entre os lados correspondentes:"
				+ "\\(\\\\\\)"
				+ "\\(\\dfrac{" + ladoMenor + "}{" + M + "} = \\dfrac{1}{" + p + "}\\\\"
				+ p + "(" + ladoMenor + ") = " + M + "\\\\"
				+ resolucao + "\\)";
		setResolucao(res);
	}
}
