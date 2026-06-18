package matematica.avancado.funcoestrigonometricas.nivel2package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Exercicio11 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Triplas pitagóricas: sen=p/r, cos=q/r no 1.º quadrante
		int[][] triples = {{3, 4, 5}, {5, 12, 13}, {8, 15, 17}, {7, 24, 25}};
		int[] t = triples[rand.nextInt(triples.length)];
		int p = t[0], q = t[1], r = t[2];
		int pp = p * p, qq = q * q, rr = r * r;

		addParagrafo("Dado que \\(\\operatorname{sen}\\,\\theta = \\dfrac{" + p + "}{" + r
				+ "}\\) e \\(\\theta\\) está no 1.º quadrante, calcule \\(\\cos\\theta\\).");

		String correta = "\\(\\dfrac{" + q + "}{" + r + "}\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(-\\dfrac{" + q + "}{" + r + "}\\)");
		distratores.add("\\(\\dfrac{" + p + "}{" + r + "}\\)");
		distratores.add("\\(\\dfrac{" + q + "}{" + p + "}\\)");
		embaralharEAdicionarAlternativas(correta, distratores);

		String res = "Usando a identidade fundamental \\(\\operatorname{sen}^2\\theta + \\cos^2\\theta = 1\\):"
				+ "\\(\\\\\\)"
				+ "\\(\\left(\\dfrac{" + p + "}{" + r + "}\\right)^2 + \\cos^2\\theta = 1 \\\\"
				+ "\\dfrac{" + pp + "}{" + rr + "} + \\cos^2\\theta = 1 \\\\"
				+ "\\cos^2\\theta = \\dfrac{" + qq + "}{" + rr + "} \\\\"
				+ "\\cos\\theta = \\mathbf{\\dfrac{" + q + "}{" + r + "}}\\)"
				+ " (positivo: \\(\\theta\\) está no 1.º quadrante)";
		setResolucao(res);
	}
}
