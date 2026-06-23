package matematica.avancado.funcoestrigonometricas.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Exercicio5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Dado cos=q/r, calcule tg²θ + 1 = sec²θ = r²/q²
		// Triplas primitivas: cos=4/5, 12/13, 15/17
		int[][] triples = {{3, 4, 5}, {5, 12, 13}, {8, 15, 17}};
		int[] t = triples[rand.nextInt(triples.length)];
		int q = t[1], r = t[2];
		int qq = q * q, rr = r * r;

		addParagrafo("Dado que \\(\\cos\\theta = \\dfrac{" + q + "}{" + r
				+ "}\\), calcule \\(\\operatorname{tg}^2\\theta + 1\\).");

		String correta = "\\(\\dfrac{" + rr + "}{" + qq + "}\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(\\dfrac{" + qq + "}{" + rr + "}\\)");
		distratores.add("\\(\\dfrac{" + (rr - qq) + "}{" + qq + "}\\)");
		distratores.add("\\(\\dfrac{" + (rr + qq) + "}{" + rr + "}\\)");
		embaralharEAdicionarAlternativas(correta, distratores);

		addResolucao("Pela identidade \\(\\operatorname{tg}^2\\theta + 1 = \\sec^2\\theta = \\dfrac{1}{\\cos^2\\theta}\\):");
		addResolucao("\\(\\operatorname{tg}^2\\theta + 1 = \\dfrac{1}{\\left(\\dfrac{" + q + "}{" + r + "}\\right)^2}"
				+ " = \\dfrac{1}{\\dfrac{" + qq + "}{" + rr + "}} = \\mathbf{\\dfrac{" + rr + "}{" + qq + "}}\\)");
	}
}
