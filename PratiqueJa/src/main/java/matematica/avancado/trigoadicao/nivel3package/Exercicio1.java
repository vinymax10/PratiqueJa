package matematica.avancado.trigoadicao.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

// sen(2α) com α no 2.º quadrante → resultado negativo
public class Exercicio1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Q2: sen(α)=p/r > 0, cos(α)=-q/r < 0 → sen(2α)=2·(p/r)·(-q/r) = -2pq/r² < 0
		int[][] triples = {{3, 4, 5}, {5, 12, 13}, {8, 15, 17}, {7, 24, 25}};
		int[] t = triples[rand.nextInt(triples.length)];
		int p = t[0], q = t[1], r = t[2];
		int num = 2 * p * q;
		int rr  = r * r;

		addParagrafo("Dado que \\(\\operatorname{sen}\\,\\alpha = \\dfrac{" + p + "}{" + r
				+ "}\\) e \\(\\alpha\\) está no 2.º quadrante, calcule \\(\\operatorname{sen}(2\\alpha)\\).");

		String correta = "\\(-\\dfrac{" + num + "}{" + rr + "}\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(\\dfrac{" + num + "}{" + rr + "}\\)");
		distratores.add("\\(-\\dfrac{" + (q * q - p * p) + "}{" + rr + "}\\)");
		distratores.add("\\(\\dfrac{" + (q * q - p * p) + "}{" + rr + "}\\)");
		embaralharEAdicionarAlternativas(correta, distratores);

		setResolucao("No 2.º quadrante, \\(\\cos\\alpha = -\\dfrac{" + q + "}{" + r + "}\\)."
				+ "\\(\\\\\\)"
				+ "Pela fórmula \\(\\operatorname{sen}(2\\alpha) = 2\\operatorname{sen}\\,\\alpha\\cos\\alpha\\):"
				+ "\\(\\\\\\)"
				+ "\\(\\operatorname{sen}(2\\alpha) = 2 \\cdot \\dfrac{" + p + "}{" + r + "} \\cdot \\left(-\\dfrac{" + q + "}{" + r
				+ "}\\right) = \\mathbf{-\\dfrac{" + num + "}{" + rr + "}}\\)");
	}
}
