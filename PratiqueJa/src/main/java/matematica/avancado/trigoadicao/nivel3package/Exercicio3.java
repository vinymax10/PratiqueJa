package matematica.avancado.trigoadicao.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

// sen(α+β) com α no 2.º quadrante e β no 1.º quadrante
// sen(α+β) = (p1*q2 - q1*p2) / (r1*r2)  [cos(α) = -q1/r1]
public class Exercicio3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int[][][] pares = {
			{{3, 4, 5}, {5, 12, 13}},  // (3·12 - 4·5)/65 = 16/65
			{{3, 4, 5}, {8, 15, 17}},  // (3·15 - 4·8)/85 = 13/85
			{{5, 12, 13}, {3, 4, 5}}   // (5·4 - 12·3)/65 = -16/65 (negativo)
		};
		int[][] par = pares[rand.nextInt(pares.length)];
		int[] t1 = par[0], t2 = par[1];
		int p1 = t1[0], q1 = t1[1], r1 = t1[2];
		int p2 = t2[0], q2 = t2[1], r2 = t2[2];
		// cos(α)=-q1/r1 (Q2)
		int den = r1 * r2;
		int num = p1 * q2 - q1 * p2; // can be negative

		addParagrafo("Dados \\(\\operatorname{sen}\\,\\alpha = \\dfrac{" + p1 + "}{" + r1 + "}\\)"
				+ " com \\(\\alpha\\) no 2.º quadrante, e \\(\\operatorname{sen}\\,\\beta = \\dfrac{" + p2 + "}{" + r2 + "}\\)"
				+ " com \\(\\beta\\) no 1.º quadrante, calcule \\(\\operatorname{sen}(\\alpha+\\beta)\\).");

		String numStr = num < 0 ? "-\\dfrac{" + Math.abs(num) + "}{" + den + "}" : "\\dfrac{" + num + "}{" + den + "}";
		String correta = "\\(" + numStr + "\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(\\dfrac{" + (p1 * q2 + q1 * p2) + "}{" + den + "}\\)"); // esqueceu o sinal de cos
		distratores.add("\\(-\\dfrac{" + (p1 * p2 + q1 * q2) + "}{" + den + "}\\)"); // cos(α+β) com sinal errado
		distratores.add("\\(\\dfrac{" + Math.abs(num) + "}{" + den + "}\\)");         // esqueceu o sinal negativo
		embaralharEAdicionarAlternativas(correta, distratores);

		setResolucao("No 2.º quadrante, \\(\\cos\\alpha = -\\dfrac{" + q1 + "}{" + r1 + "}\\)."
				+ "\\(\\\\\\)"
				+ "Pela fórmula \\(\\operatorname{sen}(\\alpha+\\beta) = \\operatorname{sen}\\,\\alpha\\cos\\beta + \\cos\\alpha\\,\\operatorname{sen}\\,\\beta\\):"
				+ "\\(\\\\\\)"
				+ "\\(\\operatorname{sen}(\\alpha+\\beta) = \\dfrac{" + p1 + "}{" + r1 + "} \\cdot \\dfrac{" + q2 + "}{" + r2
				+ "} + \\left(-\\dfrac{" + q1 + "}{" + r1 + "}\\right) \\cdot \\dfrac{" + p2 + "}{" + r2 + "} = \\\\"
				+ "\\dfrac{" + (p1 * q2) + "}{" + den + "} - \\dfrac{" + (q1 * p2) + "}{" + den + "} = \\mathbf{" + numStr + "}\\)");
	}
}
