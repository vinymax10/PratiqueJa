package matematica.avancado.trigoadicao.nivel2package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

// cos(2α) = cos²α - sen²α de uma tripla pitagórica (Q1)
public class Exercicio2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int[][] triples = {{3, 4, 5}, {5, 12, 13}, {8, 15, 17}, {7, 24, 25}};
		int[] t = triples[rand.nextInt(triples.length)];
		int p = t[0], q = t[1], r = t[2];
		int pp = p * p, qq = q * q, rr = r * r;
		int num = qq - pp; // cos(2α) = (q²-p²)/r²

		addParagrafo("Dado que \\(\\operatorname{sen}\\,\\alpha = \\dfrac{" + p + "}{" + r
				+ "}\\) e \\(\\cos\\alpha = \\dfrac{" + q + "}{" + r
				+ "}\\) (1.º quadrante), calcule \\(\\cos(2\\alpha)\\).");

		String correta = "\\(\\dfrac{" + num + "}{" + rr + "}\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(\\dfrac{" + (pp - qq) + "}{" + rr + "}\\)");
		distratores.add("\\(\\dfrac{" + (2 * p * q) + "}{" + rr + "}\\)");
		distratores.add("\\(\\dfrac{" + qq + "}{" + rr + "}\\)");
		embaralharEAdicionarAlternativas(correta, distratores);

		setResolucao("Pela fórmula \\(\\cos(2\\alpha) = \\cos^2\\alpha - \\operatorname{sen}^2\\alpha\\):"
				+ "\\(\\\\\\)"
				+ "\\(\\cos(2\\alpha) = \\left(\\dfrac{" + q + "}{" + r + "}\\right)^2 - \\left(\\dfrac{" + p + "}{" + r
				+ "}\\right)^2 = \\\\ \\dfrac{" + qq + "}{" + rr + "} - \\dfrac{" + pp + "}{" + rr
				+ "} = \\mathbf{\\dfrac{" + num + "}{" + rr + "}}\\)");
	}
}
