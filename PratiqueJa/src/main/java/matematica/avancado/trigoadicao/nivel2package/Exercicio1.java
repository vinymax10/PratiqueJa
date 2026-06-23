package matematica.avancado.trigoadicao.nivel2package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

// sen(2α) dado sen(α) e cos(α) de uma tripla pitagórica (Q1)
public class Exercicio1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int[][] triples = {{3, 4, 5}, {5, 12, 13}, {8, 15, 17}, {7, 24, 25}};
		int[] t = triples[rand.nextInt(triples.length)];
		int p = t[0], q = t[1], r = t[2];
		int rr = r * r;
		int num = 2 * p * q; // sen(2α) = 2pq/r²

		addParagrafo("Dado que \\(\\operatorname{sen}\\,\\alpha = \\dfrac{" + p + "}{" + r
				+ "}\\) e \\(\\cos\\alpha = \\dfrac{" + q + "}{" + r
				+ "}\\) (1.º quadrante), calcule \\(\\operatorname{sen}(2\\alpha)\\).");

		String correta = "\\(\\dfrac{" + num + "}{" + rr + "}\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(\\dfrac{" + (p * q) + "}{" + rr + "}\\)");
		distratores.add("\\(\\dfrac{" + (q * q - p * p) + "}{" + rr + "}\\)");
		distratores.add("\\(\\dfrac{" + p + "}{" + r + "}\\)");
		embaralharEAdicionarAlternativas(correta, distratores);

		addResolucao("Pela fórmula \\(\\operatorname{sen}(2\\alpha) = 2\\operatorname{sen}\\,\\alpha\\cos\\alpha\\):");
		addResolucao("\\(\\operatorname{sen}(2\\alpha) = 2 \\cdot \\dfrac{" + p + "}{" + r + "} \\cdot \\dfrac{" + q + "}{" + r
				+ "} = \\mathbf{\\dfrac{" + num + "}{" + rr + "}}\\)");
	}
}
