package matematica.avancado.geometriaanalitica.nivel3package;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.expressao.MyExpression;

public class Exercicio7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Parâmetros da construção
		int x_val = 1 + rand.nextInt(10);
		int c     = 1 + rand.nextInt(5);
		int d     = -3 + rand.nextInt(7);
		int m     = 1 + rand.nextInt(4);
		int a1    = -4 + rand.nextInt(9);
		int b1    = -4 + rand.nextInt(9);

		// Coordenada x de B como expressão: c*x + d
		int a2 = c * x_val + d;

		// Garantir a2 != a1 para que o coeficiente angular seja definido
		while (a2 == a1)
		{
			d++;
			a2 = c * x_val + d;
		}

		// Coordenada y de B
		int b2 = b1 + m * (a2 - a1);

		// Expressão de x: e.g. "3x - 2"
		String xExpr = Auxiliar.getNumber(c, "x", true) + Auxiliar.getNumber(d, "", false);

		// Expressão deslocada: c*x + (d - a1), usada na derivação do coef. angular
		int dMinusA1 = d - a1;
		String xExprShifted = Auxiliar.getNumber(c, "x", true) + Auxiliar.getNumber(dMinusA1, "", false);

		// Para MyExpression: effC * x + effD = 0
		// m * (c*x + d - a1) = b2 - b1  =>  m*c*x + m*(d-a1) - (b2-b1) = 0
		int effC = m * c;
		int effD = m * (d - a1) - (b2 - b1);
		// Como b2-b1 = m*(a2-a1) = m*(c*x_val+d-a1), temos effD = -m*c*x_val
		String eqStr = Auxiliar.getNumber(effC, "x", true) + Auxiliar.getNumber(effD, "", false) + "=0";

		MyExpression myExpression = new MyExpression(eqStr);
		String resolucaoPassos = myExpression.resolverLatex();

		// Enunciado
		addParagrafo("A reta de coeficiente angular \\(m = " + m + "\\) passa por \\(A(" + a1 + ";\\;" + b1 + ")\\). "
				+ "O ponto \\(B(" + xExpr + ";\\;" + b2 + ")\\) também pertence à reta. Encontre \\(x\\).");

		gerarAlternativas(String.valueOf(x_val));

		// Resolução
		int dy = b2 - b1;
		String sinalDMinusA1 = dMinusA1 >= 0 ? " + " + dMinusA1 : " - " + Math.abs(dMinusA1);

		String res = "Usando a condição de coeficiente angular \\(m = \\dfrac{y_B - y_A}{x_B - x_A}\\):"
				+ "\\(\\\\\\)"
				+ "\\(" + m + " = \\dfrac{" + dy + "}{" + xExprShifted + "}\\\\"
				+ m + " \\cdot (" + xExprShifted + ") = " + dy + "\\\\"
				+ resolucaoPassos + "\\)";
		setResolucao(res);
	}
}
