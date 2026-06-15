package matematica.avancado.funcoestrigonometricas.nivel2package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Exercicio2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Triplas pitagóricas: no 2.º quadrante cos=-q/r, sen=p/r (positivo)
		int[][] triples = {{3, 4, 5}, {5, 12, 13}, {8, 15, 17}, {7, 24, 25}};
		int[] t = triples[rand.nextInt(triples.length)];
		int p = t[0], q = t[1], r = t[2];
		int pp = p * p, qq = q * q, rr = r * r;

		addParagrafo("Dado que \\(\\cos\\theta = -\\dfrac{" + q + "}{" + r
				+ "}\\) e \\(\\theta\\) está no 2.º quadrante, calcule \\(\\operatorname{sen}\\,\\theta\\).");

		String correta = "\\(\\dfrac{" + p + "}{" + r + "}\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(-\\dfrac{" + p + "}{" + r + "}\\)");
		distratores.add("\\(\\dfrac{" + q + "}{" + r + "}\\)");
		distratores.add("\\(\\dfrac{" + p + "}{" + q + "}\\)");
		embaralharEAdicionarAlternativas(correta, distratores);

		String res = "Usando a identidade fundamental \\(\\operatorname{sen}^2\\theta + \\cos^2\\theta = 1\\):"
				+ "\\(\\\\\\)"
				+ "\\(\\operatorname{sen}^2\\theta + \\left(-\\dfrac{" + q + "}{" + r + "}\\right)^2 = 1 \\\\"
				+ "\\operatorname{sen}^2\\theta + \\dfrac{" + qq + "}{" + rr + "} = 1 \\\\"
				+ "\\operatorname{sen}^2\\theta = \\dfrac{" + pp + "}{" + rr + "} \\\\"
				+ "\\operatorname{sen}\\,\\theta = \\mathbf{\\dfrac{" + p + "}{" + r + "}}\\)"
				+ " (positivo: \\(\\theta\\) está no 2.º quadrante)";
		setResolucao(res);
	}
}
