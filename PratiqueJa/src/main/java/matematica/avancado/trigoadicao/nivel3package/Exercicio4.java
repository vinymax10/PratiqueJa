package matematica.avancado.trigoadicao.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

// cos(α+β) com α no 3.º quadrante (sen<0, cos<0) e β no 1.º quadrante
// cos(α+β) = (-q1/r1)(q2/r2) - (-p1/r1)(p2/r2) = (p1*p2 - q1*q2)/(r1*r2)
public class Exercicio4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int[][][] pares = {
			{{3, 4, 5}, {5, 12, 13}},  // (15-48)/65 = -33/65
			{{3, 4, 5}, {8, 15, 17}},  // (24-60)/85 = -36/85
			{{5, 12, 13}, {3, 4, 5}}   // (15-48)/65 = -33/65 (mesmo resultado, diferente apresentação)
		};
		int[][] par = pares[rand.nextInt(2)]; // só 2 pares distintos
		int[] t1 = par[0], t2 = par[1];
		int p1 = t1[0], q1 = t1[1], r1 = t1[2];
		int p2 = t2[0], q2 = t2[1], r2 = t2[2];
		int den = r1 * r2;
		int num = p1 * p2 - q1 * q2; // negativo

		addParagrafo("Dados \\(\\operatorname{sen}\\,\\alpha = -\\dfrac{" + p1 + "}{" + r1 + "}\\)"
				+ " e \\(\\cos\\alpha = -\\dfrac{" + q1 + "}{" + r1 + "}\\) (3.º quadrante),"
				+ " \\(\\operatorname{sen}\\,\\beta = \\dfrac{" + p2 + "}{" + r2 + "}\\)"
				+ " e \\(\\cos\\beta = \\dfrac{" + q2 + "}{" + r2 + "}\\) (1.º quadrante),"
				+ " calcule \\(\\cos(\\alpha+\\beta)\\).");

		String numStr = "-\\dfrac{" + Math.abs(num) + "}{" + den + "}";
		String correta = "\\(" + numStr + "\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(\\dfrac{" + Math.abs(num) + "}{" + den + "}\\)");
		distratores.add("\\(-\\dfrac{" + (p1 * q2 + q1 * p2) + "}{" + den + "}\\)"); // confundiu com sen(α+β)
		distratores.add("\\(\\dfrac{" + (q1 * q2 + p1 * p2) + "}{" + den + "}\\)");  // esqueceu os sinais
		embaralharEAdicionarAlternativas(correta, distratores);

		setResolucao("Pela fórmula \\(\\cos(\\alpha+\\beta) = \\cos\\alpha\\cos\\beta - \\operatorname{sen}\\,\\alpha\\,\\operatorname{sen}\\,\\beta\\):"
				+ "\\(\\\\\\)"
				+ "\\(\\cos(\\alpha+\\beta) = \\left(-\\dfrac{" + q1 + "}{" + r1 + "}\\right)\\dfrac{" + q2 + "}{" + r2
				+ "} - \\left(-\\dfrac{" + p1 + "}{" + r1 + "}\\right)\\dfrac{" + p2 + "}{" + r2 + "} = \\\\"
				+ "-\\dfrac{" + (q1 * q2) + "}{" + den + "} + \\dfrac{" + (p1 * p2) + "}{" + den + "} = \\mathbf{" + numStr + "}\\)");
	}
}
