package matematica.avancado.trigoadicao.nivel2package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

// cos(2α) usando a forma alternativa 1 − 2·sen²(α) (caminho diferente de Exercicio2)
public class Exercicio9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int[][] triples = {{3, 4, 5}, {5, 12, 13}, {8, 15, 17}, {7, 24, 25}};
		int[] t = triples[rand.nextInt(triples.length)];
		int p = t[0], q = t[1], r = t[2];
		int pp = p * p, rr = r * r;
		int num = rr - 2 * pp; // cos(2α) = (r²-2p²)/r² = (q²-p²)/r²

		addParagrafo("Dado que \\(\\operatorname{sen}\\,\\alpha = \\dfrac{" + p + "}{" + r
				+ "}\\) (1.º quadrante), use a forma \\(\\cos(2\\alpha) = 1 - 2\\operatorname{sen}^2\\alpha\\)"
				+ " para calcular \\(\\cos(2\\alpha)\\).");

		String correta = "\\(\\dfrac{" + num + "}{" + rr + "}\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(\\dfrac{" + (2 * pp) + "}{" + rr + "}\\)");           // esqueceu o "1 - "
		distratores.add("\\(\\dfrac{" + (rr + 2 * pp) + "}{" + rr + "}\\)");      // somou em vez de subtrair
		distratores.add("\\(\\dfrac{" + (2 * p * q) + "}{" + rr + "}\\)");        // confundiu com sen(2α)
		embaralharEAdicionarAlternativas(correta, distratores);

		setResolucao("Usando \\(\\cos(2\\alpha) = 1 - 2\\operatorname{sen}^2\\alpha\\):"
				+ "\\(\\\\\\)"
				+ "\\(\\cos(2\\alpha) = 1 - 2 \\cdot \\left(\\dfrac{" + p + "}{" + r + "}\\right)^2 = \\\\"
				+ "1 - \\dfrac{" + (2 * pp) + "}{" + rr + "} = "
				+ "\\dfrac{" + rr + " - " + (2 * pp) + "}{" + rr + "} = \\mathbf{\\dfrac{" + num + "}{" + rr + "}}\\)");
	}
}
