package matematica.avancado.funcoestrigonometricas.nivel2package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Exercicio8 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// cos(-θ) = cos(θ): cosseno é função par
		int[][] triples = {{3, 4, 5}, {5, 12, 13}, {8, 15, 17}, {7, 24, 25}};
		int[] t = triples[rand.nextInt(triples.length)];
		int p = t[0], q = t[1], r = t[2];

		addParagrafo("Sabendo que \\(\\cos\\theta = \\dfrac{" + q + "}{" + r
				+ "}\\), calcule \\(\\cos(-\\theta)\\).");

		String correta = "\\(\\dfrac{" + q + "}{" + r + "}\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(-\\dfrac{" + q + "}{" + r + "}\\)");
		distratores.add("\\(\\dfrac{" + p + "}{" + r + "}\\)");
		distratores.add("\\(-\\dfrac{" + p + "}{" + r + "}\\)");
		embaralharEAdicionarAlternativas(correta, distratores);

		addResolucao("O cosseno é uma função par: \\(\\cos(-\\theta) = \\cos\\theta\\).");
		addResolucao("Portanto: \\(\\cos(-\\theta) = \\cos\\theta = \\mathbf{\\dfrac{" + q + "}{" + r + "}}\\)");
		addResolucao("(Compare com o seno, que é função ímpar: \\(\\operatorname{sen}(-\\theta) = -\\operatorname{sen}\\,\\theta\\).)");
	}
}
