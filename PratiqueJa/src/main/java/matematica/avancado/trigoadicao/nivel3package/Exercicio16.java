package matematica.avancado.trigoadicao.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

// tg(α−β) = (tg(α)−tg(β)) / (1+tg(α)·tg(β)) de dois ângulos do 1.º quadrante
// Resultado pode ser positivo ou negativo conforme o par escolhido.
public class Exercicio16 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// tg(α-β) = (p1*q2 - p2*q1) / (q1*q2 + p1*p2)
		int[][][] pares = {
			{{3, 4, 5},  {5, 12, 13}},  // (36-20)/(48+15) =  16/63
			{{3, 4, 5},  {8, 15, 17}},  // (45-32)/(60+24) =  13/84
			{{5, 12, 13},{8, 15, 17}}   // (75-96)/(180+40) = -21/220
		};
		int[][] par = pares[rand.nextInt(pares.length)];
		int[] t1 = par[0], t2 = par[1];
		int p1 = t1[0], q1 = t1[1];
		int p2 = t2[0], q2 = t2[1];
		int r1 = t1[2], r2 = t2[2];
		int num = p1 * q2 - p2 * q1;
		int den = q1 * q2 + p1 * p2;

		addParagrafo("Sabendo que \\(\\operatorname{tg}\\,\\alpha = \\dfrac{" + p1 + "}{" + q1 + "}\\)"
				+ " e \\(\\operatorname{tg}\\,\\beta = \\dfrac{" + p2 + "}{" + q2 + "}\\)"
				+ " (ambos no 1.º quadrante), calcule \\(\\operatorname{tg}(\\alpha-\\beta)\\).");

		String numStr = num < 0 ? "-\\dfrac{" + Math.abs(num) + "}{" + den + "}" : "\\dfrac{" + num + "}{" + den + "}";
		String correta = "\\(" + numStr + "\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(\\dfrac{" + (p1 * q2 + p2 * q1) + "}{" + (q1 * q2 - p1 * p2) + "}\\)"); // tg(α+β)
		distratores.add("\\(\\dfrac{" + Math.abs(num) + "}{" + den + "}\\)");                         // esqueceu sinal
		distratores.add("\\(\\dfrac{" + (p1 * q2 - p2 * q1) + "}{" + (q1 * q2) + "}\\)");           // denominador incompleto
		embaralharEAdicionarAlternativas(correta, distratores);

		setResolucao("Pela fórmula \\(\\operatorname{tg}(\\alpha-\\beta) = \\dfrac{\\operatorname{tg}\\,\\alpha - \\operatorname{tg}\\,\\beta}{1 + \\operatorname{tg}\\,\\alpha \\cdot \\operatorname{tg}\\,\\beta}\\):"
				+ "\\(\\\\\\)"
				+ "\\(\\operatorname{tg}(\\alpha-\\beta) = \\dfrac{\\dfrac{" + p1 + "}{" + q1 + "} - \\dfrac{" + p2 + "}{" + q2 + "}}"
				+ "{1 + \\dfrac{" + p1 + "}{" + q1 + "} \\cdot \\dfrac{" + p2 + "}{" + q2 + "}} = \\\\"
				+ "\\dfrac{\\dfrac{" + (p1 * q2) + " - " + (p2 * q1) + "}{" + (q1 * q2) + "}}"
				+ "{\\dfrac{" + (q1 * q2) + " + " + (p1 * p2) + "}{" + (q1 * q2) + "}} = \\mathbf{" + numStr + "}\\)");
	}
}
