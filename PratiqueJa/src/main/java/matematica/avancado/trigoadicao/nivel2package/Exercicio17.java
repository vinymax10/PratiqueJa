package matematica.avancado.trigoadicao.nivel2package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

// sen(α−β) = sen(α)cos(β) − cos(α)sen(β) de dois ângulos do 1.º quadrante
// A ordem dos ângulos varia para que o resultado seja às vezes positivo, às vezes negativo.
public class Exercicio17 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// (t1, t2): sen(α-β) = (p1*q2 - q1*p2) / (r1*r2)
		// Pares com ordens alternadas para variar sinal
		int[][][] pares = {
			{{3, 4, 5},  {5, 12, 13}},  // (36-20)/65  = +16/65
			{{5, 12, 13},{3, 4, 5}},    // (20-36)/65  = -16/65
			{{3, 4, 5},  {8, 15, 17}},  // (45-32)/85  = +13/85
			{{8, 15, 17},{3, 4, 5}},    // (32-45)/85  = -13/85
			{{5, 12, 13},{8, 15, 17}}   // (75-96)/221 = -21/221
		};
		int[][] par = pares[rand.nextInt(pares.length)];
		int[] t1 = par[0], t2 = par[1];
		int p1 = t1[0], q1 = t1[1], r1 = t1[2];
		int p2 = t2[0], q2 = t2[1], r2 = t2[2];
		int num = p1 * q2 - q1 * p2;
		int den = r1 * r2;

		addParagrafo("Sabendo que \\(\\operatorname{sen}\\,\\alpha = \\dfrac{" + p1 + "}{" + r1 + "}\\),"
				+ " \\(\\cos\\alpha = \\dfrac{" + q1 + "}{" + r1 + "}\\),"
				+ " \\(\\operatorname{sen}\\,\\beta = \\dfrac{" + p2 + "}{" + r2 + "}\\) e"
				+ " \\(\\cos\\beta = \\dfrac{" + q2 + "}{" + r2 + "}\\)"
				+ " (ambos no 1.º quadrante), calcule \\(\\operatorname{sen}(\\alpha-\\beta)\\).");

		String numStr = num < 0 ? "-\\dfrac{" + Math.abs(num) + "}{" + den + "}" : "\\dfrac{" + num + "}{" + den + "}";
		String correta = "\\(" + numStr + "\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(\\dfrac{" + (p1 * q2 + q1 * p2) + "}{" + den + "}\\)"); // sen(α+β)
		distratores.add("\\(\\dfrac{" + Math.abs(num) + "}{" + den + "}\\)");         // esqueceu o sinal
		distratores.add("\\(\\dfrac{" + (q1 * q2 - p1 * p2) + "}{" + den + "}\\)"); // cos(α+β)
		embaralharEAdicionarAlternativas(correta, distratores);

		setResolucao("Pela fórmula \\(\\operatorname{sen}(\\alpha-\\beta) = \\operatorname{sen}\\,\\alpha\\cos\\beta - \\cos\\alpha\\,\\operatorname{sen}\\,\\beta\\):"
				+ "\\(\\\\\\)"
				+ "\\(\\operatorname{sen}(\\alpha-\\beta) = \\dfrac{" + p1 + "}{" + r1 + "} \\cdot \\dfrac{" + q2 + "}{" + r2
				+ "} - \\dfrac{" + q1 + "}{" + r1 + "} \\cdot \\dfrac{" + p2 + "}{" + r2 + "} = \\\\"
				+ "\\dfrac{" + (p1 * q2) + "}{" + den + "} - \\dfrac{" + (q1 * p2) + "}{" + den + "} "
				+ "= \\mathbf{" + numStr + "}\\)");
	}
}
