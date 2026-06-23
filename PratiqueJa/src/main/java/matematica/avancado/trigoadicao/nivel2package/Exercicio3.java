package matematica.avancado.trigoadicao.nivel2package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

// tg(2α) = 2·tg(α) / (1 - tg²(α)) de uma tripla pitagórica (Q1)
public class Exercicio3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// triplas cujos resultados de tg(2α) são frações simples
		int[][] triples = {{3, 4, 5}, {5, 12, 13}, {8, 15, 17}};
		int[] t = triples[rand.nextInt(triples.length)];
		int p = t[0], q = t[1], r = t[2];
		int numTg2 = 2 * p * q;          // numerador de tg(2α)
		int denTg2 = q * q - p * p;      // denominador de tg(2α)

		addParagrafo("Dado que \\(\\operatorname{tg}\\,\\alpha = \\dfrac{" + p + "}{" + q
				+ "}\\) (1.º quadrante), use a fórmula de duplicação para calcular \\(\\operatorname{tg}(2\\alpha)\\).");

		String correta = "\\(\\dfrac{" + numTg2 + "}{" + denTg2 + "}\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(\\dfrac{" + p + "}{" + q + "}\\)");
		distratores.add("\\(\\dfrac{" + (2 * p) + "}{" + q + "}\\)");
		distratores.add("\\(\\dfrac{" + q + "}{" + p + "}\\)");
		embaralharEAdicionarAlternativas(correta, distratores);

		addResolucao("Pela fórmula \\(\\operatorname{tg}(2\\alpha) = \\dfrac{2\\operatorname{tg}\\,\\alpha}{1 - \\operatorname{tg}^2\\alpha}\\):");
		addResolucao("\\(\\operatorname{tg}(2\\alpha) = \\dfrac{2 \\cdot \\dfrac{" + p + "}{" + q + "}}{1 - \\dfrac{" + (p * p) + "}{" + (q * q) + "}} =\\)");
		addResolucao("\\(\\dfrac{\\dfrac{" + (2 * p) + "}{" + q + "}}{\\dfrac{" + denTg2 + "}{" + (q * q) + "}} = "
				+ "\\dfrac{" + (2 * p) + "}{" + q + "} \\cdot \\dfrac{" + (q * q) + "}{" + denTg2 + "} "
				+ "= \\mathbf{\\dfrac{" + numTg2 + "}{" + denTg2 + "}}\\)");
	}
}
