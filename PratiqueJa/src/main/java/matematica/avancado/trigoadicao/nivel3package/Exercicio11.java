package matematica.avancado.trigoadicao.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

// tg(2α) com α no 2.º quadrante — dado tg(α)=-p/q, resultado -2pq/(q²-p²)
public class Exercicio11 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Q2: tg(α) = sen(α)/cos(α) = (p/r)/(-q/r) = -p/q (negativo)
		// tg(2α) = 2·tg(α)/(1-tg²(α)) = 2·(-p/q)/(1-p²/q²) = -2pq/(q²-p²)
		int[][] triples = {{3, 4, 5}, {5, 12, 13}, {8, 15, 17}};
		int[] t = triples[rand.nextInt(triples.length)];
		int p = t[0], q = t[1], r = t[2];
		int numTg2 = 2 * p * q;
		int denTg2 = q * q - p * p;

		addParagrafo("Dado que \\(\\operatorname{tg}\\,\\alpha = -\\dfrac{" + p + "}{" + q
				+ "}\\) e \\(\\alpha\\) está no 2.º quadrante, use a fórmula de duplicação para calcular"
				+ " \\(\\operatorname{tg}(2\\alpha)\\).");

		String correta = "\\(-\\dfrac{" + numTg2 + "}{" + denTg2 + "}\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(\\dfrac{" + numTg2 + "}{" + denTg2 + "}\\)");
		distratores.add("\\(-\\dfrac{" + p + "}{" + q + "}\\)");
		distratores.add("\\(\\dfrac{" + denTg2 + "}{" + numTg2 + "}\\)");
		embaralharEAdicionarAlternativas(correta, distratores);

		setResolucao("Pela fórmula \\(\\operatorname{tg}(2\\alpha) = \\dfrac{2\\operatorname{tg}\\,\\alpha}{1-\\operatorname{tg}^2\\alpha}\\):"
				+ "\\(\\\\\\)"
				+ "\\(\\operatorname{tg}(2\\alpha) = \\dfrac{2 \\cdot \\left(-\\dfrac{" + p + "}{" + q + "}\\right)}{1 - \\dfrac{" + (p * p) + "}{" + (q * q) + "}} = \\\\"
				+ "\\dfrac{-\\dfrac{" + (2 * p) + "}{" + q + "}}{\\dfrac{" + denTg2 + "}{" + (q * q) + "}} = "
				+ "-\\dfrac{" + (2 * p) + "}{" + q + "} \\cdot \\dfrac{" + (q * q) + "}{" + denTg2 + "} = \\mathbf{-\\dfrac{" + numTg2 + "}{" + denTg2 + "}}\\)");
	}
}
