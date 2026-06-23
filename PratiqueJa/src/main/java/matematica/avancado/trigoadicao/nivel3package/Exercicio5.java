package matematica.avancado.trigoadicao.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

// sen(α-β) com α no 1.º quadrante e β no 2.º quadrante → resultado negativo
// sen(α-β) = sen(α)cos(β) - cos(α)sen(β) = (p1/r1)(-q2/r2) - (q1/r1)(p2/r2)
//           = -(p1*q2 + q1*p2) / (r1*r2)
public class Exercicio5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int[][][] pares = {
			{{3, 4, 5}, {5, 12, 13}},  // -(36+20)/65 = -56/65
			{{3, 4, 5}, {8, 15, 17}},  // -(45+32)/85 = -77/85
			{{5, 12, 13}, {3, 4, 5}}   // -(60+12)/65... wait: p1=5,q1=12,p2=3,q2=4 → -(5·4+12·3)/65 = -(20+36)/65 = -56/65
		};
		int[][] par = pares[rand.nextInt(pares.length)];
		int[] t1 = par[0], t2 = par[1];
		int p1 = t1[0], q1 = t1[1], r1 = t1[2];
		int p2 = t2[0], q2 = t2[1], r2 = t2[2];
		// β in Q2: cos(β)=-q2/r2
		int den = r1 * r2;
		int num = p1 * q2 + q1 * p2; // always positive; result is -num/den

		addParagrafo("Dados \\(\\operatorname{sen}\\,\\alpha = \\dfrac{" + p1 + "}{" + r1 + "}\\)"
				+ " e \\(\\cos\\alpha = \\dfrac{" + q1 + "}{" + r1 + "}\\) (1.º quadrante),"
				+ " e \\(\\operatorname{sen}\\,\\beta = \\dfrac{" + p2 + "}{" + r2 + "}\\)"
				+ " com \\(\\beta\\) no 2.º quadrante,"
				+ " calcule \\(\\operatorname{sen}(\\alpha - \\beta)\\).");

		String correta = "\\(-\\dfrac{" + num + "}{" + den + "}\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(\\dfrac{" + num + "}{" + den + "}\\)");
		distratores.add("\\(\\dfrac{" + (p1 * q2 - q1 * p2) + "}{" + den + "}\\)");  // esqueceu sinal de cos
		distratores.add("\\(-\\dfrac{" + (q1 * q2 + p1 * p2) + "}{" + den + "}\\)"); // fórmula de cos
		embaralharEAdicionarAlternativas(correta, distratores);

		addResolucao("No 2.º quadrante, \\(\\cos\\beta = -\\dfrac{" + q2 + "}{" + r2 + "}\\).");
		addResolucao("Pela fórmula \\(\\operatorname{sen}(\\alpha-\\beta) = \\operatorname{sen}\\,\\alpha\\cos\\beta - \\cos\\alpha\\,\\operatorname{sen}\\,\\beta\\):");
		addResolucao("\\(\\operatorname{sen}(\\alpha-\\beta) = \\dfrac{" + p1 + "}{" + r1 + "} \\cdot \\left(-\\dfrac{" + q2 + "}{" + r2
				+ "}\\right) - \\dfrac{" + q1 + "}{" + r1 + "} \\cdot \\dfrac{" + p2 + "}{" + r2 + "} =\\)");
		addResolucao("\\(-\\dfrac{" + (p1 * q2) + "}{" + den + "} - \\dfrac{" + (q1 * p2) + "}{" + den + "} = \\mathbf{-\\dfrac{" + num + "}{" + den + "}}\\)");
	}
}
