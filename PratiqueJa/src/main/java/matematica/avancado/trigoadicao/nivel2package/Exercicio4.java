package matematica.avancado.trigoadicao.nivel2package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

// sen(α+β) dado sen e cos de α e β (dois ângulos do 1.º quadrante, triplas distintas)
public class Exercicio4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// pares de triplas pitagóricas; numerador e denominador de sen(α+β)
		// sen(α+β) = (p1*q2 + q1*p2) / (r1*r2)
		int[][][] pares = {
			{{3, 4, 5}, {5, 12, 13}},  // 56/65
			{{3, 4, 5}, {8, 15, 17}},  // 77/85
			{{5, 12, 13}, {8, 15, 17}} // 171/221
		};
		int[][] par = pares[rand.nextInt(pares.length)];
		int[] t1 = par[0], t2 = par[1];
		int p1 = t1[0], q1 = t1[1], r1 = t1[2];
		int p2 = t2[0], q2 = t2[1], r2 = t2[2];
		int num = p1 * q2 + q1 * p2;
		int den = r1 * r2;

		addParagrafo("Sabendo que \\(\\operatorname{sen}\\,\\alpha = \\dfrac{" + p1 + "}{" + r1 + "}\\),"
				+ " \\(\\cos\\alpha = \\dfrac{" + q1 + "}{" + r1 + "}\\),"
				+ " \\(\\operatorname{sen}\\,\\beta = \\dfrac{" + p2 + "}{" + r2 + "}\\) e"
				+ " \\(\\cos\\beta = \\dfrac{" + q2 + "}{" + r2 + "}\\)"
				+ " (ambos no 1.º quadrante), calcule \\(\\operatorname{sen}(\\alpha + \\beta)\\).");

		String correta = "\\(\\dfrac{" + num + "}{" + den + "}\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(\\dfrac{" + (p1 * p2 + q1 * q2) + "}{" + den + "}\\)"); // cos(α+β) invertido
		distratores.add("\\(\\dfrac{" + (p1 * q2 - q1 * p2) + "}{" + den + "}\\)"); // sen(α-β)
		distratores.add("\\(\\dfrac{" + (p1 * p2) + "}{" + den + "}\\)");            // só um termo
		embaralharEAdicionarAlternativas(correta, distratores);

		addResolucao("Pela fórmula \\(\\operatorname{sen}(\\alpha+\\beta) = \\operatorname{sen}\\,\\alpha\\cos\\beta + \\cos\\alpha\\,\\operatorname{sen}\\,\\beta\\):");
		addResolucao("\\(\\operatorname{sen}(\\alpha+\\beta) = \\dfrac{" + p1 + "}{" + r1 + "} \\cdot \\dfrac{" + q2 + "}{" + r2
				+ "} + \\dfrac{" + q1 + "}{" + r1 + "} \\cdot \\dfrac{" + p2 + "}{" + r2 + "} =\\)");
		addResolucao("\\(\\dfrac{" + (p1 * q2) + "}{" + den + "} + \\dfrac{" + (q1 * p2) + "}{" + den + "} "
				+ "= \\mathbf{\\dfrac{" + num + "}{" + den + "}}\\)");
	}
}
