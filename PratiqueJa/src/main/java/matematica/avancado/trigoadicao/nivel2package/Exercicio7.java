package matematica.avancado.trigoadicao.nivel2package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

// cos(α+β) = cos(α)cos(β) − sen(α)sen(β) de dois ângulos do 1.º quadrante
public class Exercicio7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// cos(α+β) = (q1*q2 - p1*p2) / (r1*r2)
		int[][][] pares = {
			{{3, 4, 5}, {5, 12, 13}},  // (48-15)/65 = 33/65
			{{3, 4, 5}, {8, 15, 17}},  // (60-24)/85 = 36/85
			{{5, 12, 13}, {8, 15, 17}} // (180-40)/221 = 140/221
		};
		int[][] par = pares[rand.nextInt(pares.length)];
		int[] t1 = par[0], t2 = par[1];
		int p1 = t1[0], q1 = t1[1], r1 = t1[2];
		int p2 = t2[0], q2 = t2[1], r2 = t2[2];
		int num = q1 * q2 - p1 * p2;
		int den = r1 * r2;

		addParagrafo("Sabendo que \\(\\operatorname{sen}\\,\\alpha = \\dfrac{" + p1 + "}{" + r1 + "}\\),"
				+ " \\(\\cos\\alpha = \\dfrac{" + q1 + "}{" + r1 + "}\\),"
				+ " \\(\\operatorname{sen}\\,\\beta = \\dfrac{" + p2 + "}{" + r2 + "}\\) e"
				+ " \\(\\cos\\beta = \\dfrac{" + q2 + "}{" + r2 + "}\\)"
				+ " (ambos no 1.º quadrante), calcule \\(\\cos(\\alpha+\\beta)\\).");

		String correta = "\\(\\dfrac{" + num + "}{" + den + "}\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(\\dfrac{" + (q1 * q2 + p1 * p2) + "}{" + den + "}\\)"); // cos(α-β): sinal errado
		distratores.add("\\(\\dfrac{" + (p1 * q2 + q1 * p2) + "}{" + den + "}\\)"); // sen(α+β): fórmula errada
		distratores.add("\\(\\dfrac{" + (q1 * q2) + "}{" + den + "}\\)");            // esqueceu o termo -sen·sen
		embaralharEAdicionarAlternativas(correta, distratores);

		addResolucao("Pela fórmula \\(\\cos(\\alpha+\\beta) = \\cos\\alpha\\cos\\beta - \\operatorname{sen}\\,\\alpha\\,\\operatorname{sen}\\,\\beta\\):");
		addResolucao("\\(\\cos(\\alpha+\\beta) = \\dfrac{" + q1 + "}{" + r1 + "} \\cdot \\dfrac{" + q2 + "}{" + r2
				+ "} - \\dfrac{" + p1 + "}{" + r1 + "} \\cdot \\dfrac{" + p2 + "}{" + r2 + "} =\\)");
		addResolucao("\\(\\dfrac{" + (q1 * q2) + "}{" + den + "} - \\dfrac{" + (p1 * p2) + "}{" + den + "} "
				+ "= \\mathbf{\\dfrac{" + num + "}{" + den + "}}\\)");
	}
}
