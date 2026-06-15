package matematica.avancado.trigoadicao.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

// cos(α−β) com α no 2.º quadrante (cos(α)<0) e β no 1.º quadrante → resultado negativo
// cos(α-β) = cos(α)cos(β) + sen(α)sen(β) = (-q1*q2 + p1*p2)/(r1*r2)
public class Exercicio8 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int[][][] pares = {
			{{3, 4, 5}, {5, 12, 13}},  // (-48+15)/65 = -33/65
			{{3, 4, 5}, {8, 15, 17}},  // (-60+24)/85 = -36/85
		};
		int[][] par = pares[rand.nextInt(pares.length)];
		int[] t1 = par[0], t2 = par[1];
		int p1 = t1[0], q1 = t1[1], r1 = t1[2];
		int p2 = t2[0], q2 = t2[1], r2 = t2[2];
		// α em Q2: cos(α) = -q1/r1, sen(α) = p1/r1
		int den = r1 * r2;
		int num = p1 * p2 - q1 * q2; // negative

		addParagrafo("Dados \\(\\operatorname{sen}\\,\\alpha = \\dfrac{" + p1 + "}{" + r1 + "}\\)"
				+ " com \\(\\alpha\\) no 2.º quadrante, e \\(\\operatorname{sen}\\,\\beta = \\dfrac{" + p2 + "}{" + r2 + "}\\)"
				+ " com \\(\\cos\\beta = \\dfrac{" + q2 + "}{" + r2 + "}\\) no 1.º quadrante,"
				+ " calcule \\(\\cos(\\alpha-\\beta)\\).");

		String numStr = "-\\dfrac{" + Math.abs(num) + "}{" + den + "}";
		String correta = "\\(" + numStr + "\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(\\dfrac{" + Math.abs(num) + "}{" + den + "}\\)");              // esqueceu sinal do cos(α)
		distratores.add("\\(-\\dfrac{" + (p1 * q2 + q1 * p2) + "}{" + den + "}\\)");      // confundiu com cos(α+β)
		distratores.add("\\(\\dfrac{" + (p1 * q2 - q1 * p2) + "}{" + den + "}\\)");       // confundiu com sen(α-β) Q2
		embaralharEAdicionarAlternativas(correta, distratores);

		setResolucao("No 2.º quadrante, \\(\\cos\\alpha = -\\dfrac{" + q1 + "}{" + r1 + "}\\)."
				+ "\\(\\\\\\)"
				+ "Pela fórmula \\(\\cos(\\alpha-\\beta) = \\cos\\alpha\\cos\\beta + \\operatorname{sen}\\,\\alpha\\,\\operatorname{sen}\\,\\beta\\):"
				+ "\\(\\\\\\)"
				+ "\\(\\cos(\\alpha-\\beta) = \\left(-\\dfrac{" + q1 + "}{" + r1 + "}\\right)\\cdot\\dfrac{" + q2 + "}{" + r2
				+ "} + \\dfrac{" + p1 + "}{" + r1 + "} \\cdot \\dfrac{" + p2 + "}{" + r2 + "} = \\\\"
				+ "-\\dfrac{" + (q1 * q2) + "}{" + den + "} + \\dfrac{" + (p1 * p2) + "}{" + den + "} = \\mathbf{" + numStr + "}\\)");
	}
}
