package matematica.avancado.funcoestrigonometricas.nivel2package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Exercicio5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Ângulo oposto: sen(-θ) = -sen(θ)
		int[][] triples = {{3, 4, 5}, {5, 12, 13}, {8, 15, 17}, {7, 24, 25}, {20, 21, 29}, {9, 40, 41}, {12, 35, 37}, {28, 45, 53}};
		int[] t = triples[rand.nextInt(triples.length)];
		int p = t[0], q = t[1], r = t[2];

		addParagrafo("Sabendo que \\(\\operatorname{sen}\\,\\theta = \\dfrac{" + p + "}{" + r
				+ "}\\), calcule \\(\\operatorname{sen}(-\\theta)\\).");

		String correta = "\\(-\\dfrac{" + p + "}{" + r + "}\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(\\dfrac{" + p + "}{" + r + "}\\)");
		distratores.add("\\(\\dfrac{" + q + "}{" + r + "}\\)");
		distratores.add("\\(\\dfrac{" + p + "}{" + q + "}\\)");
		embaralharEAdicionarAlternativas(correta, distratores);

		addResolucao("Usando a propriedade \\(\\operatorname{sen}(-\\theta) = -\\operatorname{sen}\\,\\theta\\)"
				+ " (seno é função ímpar):");
		addResolucao("\\(\\operatorname{sen}(-\\theta) = -\\operatorname{sen}\\,\\theta = \\mathbf{-\\dfrac{" + p + "}{" + r + "}}\\)");
	}
}
