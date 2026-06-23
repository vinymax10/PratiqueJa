package matematica.avancado.trigoadicao.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

// tg(α+β) dado tg(α) e tg(β) de dois ângulos do 1.º quadrante
// tg(α+β) = (tg(α)+tg(β)) / (1 - tg(α)·tg(β))
//          = (p1*q2 + p2*q1) / (q1*q2 - p1*p2)
public class Exercicio6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// pares: tg(α)=p1/q1, tg(β)=p2/q2
		int[][][] pares = {
			{{3, 4, 5}, {5, 12, 13}},  // (36+20)/(48-15) = 56/33
			{{3, 4, 5}, {8, 15, 17}},  // (45+32)/(60-24) = 77/36
			{{5, 12, 13}, {8, 15, 17}} // (75+96)/(180-40) = 171/140
		};
		int[][] par = pares[rand.nextInt(pares.length)];
		int[] t1 = par[0], t2 = par[1];
		int p1 = t1[0], q1 = t1[1];
		int p2 = t2[0], q2 = t2[1];
		int r1 = t1[2], r2 = t2[2];
		int num = p1 * q2 + p2 * q1;
		int den = q1 * q2 - p1 * p2;

		addParagrafo("Sabendo que \\(\\operatorname{tg}\\,\\alpha = \\dfrac{" + p1 + "}{" + q1 + "}\\)"
				+ " e \\(\\operatorname{tg}\\,\\beta = \\dfrac{" + p2 + "}{" + q2 + "}\\)"
				+ " (ambos no 1.º quadrante), calcule \\(\\operatorname{tg}(\\alpha+\\beta)\\).");

		String correta = "\\(\\dfrac{" + num + "}{" + den + "}\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(\\dfrac{" + p1 + "}{" + q1 + "}\\)");
		distratores.add("\\(\\dfrac{" + (p1 * q2 - p2 * q1) + "}{" + (q1 * q2 + p1 * p2) + "}\\)"); // tg(α-β)
		distratores.add("\\(\\dfrac{" + num + "}{" + (q1 * q2 + p1 * p2) + "}\\)");                   // denominador errado
		embaralharEAdicionarAlternativas(correta, distratores);

		addResolucao("Pela fórmula \\(\\operatorname{tg}(\\alpha+\\beta) = \\dfrac{\\operatorname{tg}\\,\\alpha + \\operatorname{tg}\\,\\beta}{1 - \\operatorname{tg}\\,\\alpha \\cdot \\operatorname{tg}\\,\\beta}\\):");
		addResolucao("\\(\\operatorname{tg}(\\alpha+\\beta) = \\dfrac{\\dfrac{" + p1 + "}{" + q1 + "} + \\dfrac{" + p2 + "}{" + q2 + "}}"
				+ "{1 - \\dfrac{" + p1 + "}{" + q1 + "} \\cdot \\dfrac{" + p2 + "}{" + q2 + "}} =\\)");
		addResolucao("\\(\\dfrac{\\dfrac{" + (p1 * q2) + " + " + (p2 * q1) + "}{" + (q1 * q2) + "}}"
				+ "{\\dfrac{" + (q1 * q2) + " - " + (p1 * p2) + "}{" + (q1 * q2) + "}} = \\mathbf{\\dfrac{" + num + "}{" + den + "}}\\)");
	}
}
