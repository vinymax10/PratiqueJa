package matematica.avancado.funcoestrigonometricas.nivel2package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Exercicio3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Triplas pitagóricas: tg = p/q
		int[][] triples = {{3, 4, 5}, {5, 12, 13}, {8, 15, 17}, {7, 24, 25}, {20, 21, 29}, {9, 40, 41}, {12, 35, 37}, {28, 45, 53}};
		int[] t = triples[rand.nextInt(triples.length)];
		int p = t[0], q = t[1], r = t[2];

		addParagrafo("Dado que \\(\\operatorname{sen}\\,\\theta = \\dfrac{" + p + "}{" + r
				+ "}\\) e \\(\\cos\\theta = \\dfrac{" + q + "}{" + r
				+ "}\\), calcule \\(\\operatorname{tg}\\,\\theta\\).");

		String correta = "\\(\\dfrac{" + p + "}{" + q + "}\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(\\dfrac{" + q + "}{" + p + "}\\)");
		distratores.add("\\(\\dfrac{" + p + "}{" + r + "}\\)");
		distratores.add("\\(\\dfrac{" + q + "}{" + r + "}\\)");
		embaralharEAdicionarAlternativas(correta, distratores);

		addResolucao("Usando \\(\\operatorname{tg}\\,\\theta = \\dfrac{\\operatorname{sen}\\,\\theta}{\\cos\\theta}\\):");
		addResolucao("\\(\\operatorname{tg}\\,\\theta = \\dfrac{\\dfrac{" + p + "}{" + r + "}}{\\dfrac{" + q + "}{" + r
				+ "}} = \\dfrac{" + p + "}{" + r + "} \\times \\dfrac{" + r + "}{" + q
				+ "} = \\mathbf{\\dfrac{" + p + "}{" + q + "}}\\)");
	}
}
