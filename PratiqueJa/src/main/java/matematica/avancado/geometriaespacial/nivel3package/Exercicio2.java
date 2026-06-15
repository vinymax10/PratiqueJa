package matematica.avancado.geometriaespacial.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Exercicio2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int[][] triples = {{3, 4, 5}, {5, 12, 13}, {6, 8, 10}, {8, 15, 17}};
		int[]   t       = triples[rand.nextInt(triples.length)];
		int r = t[0], h = t[1], g = t[2];
		int gpr  = g + r;
		int coef = r * gpr; // r(g+r) coeficiente de π

		addParagrafo("Um cone tem raio \\(r = " + r + "\\,\\text{cm}\\) e geratriz \\(g = " + g
				+ "\\,\\text{cm}\\). Calcule a área total.");

		String correta = "\\(" + coef + "\\pi\\,\\text{cm}^2\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(" + (r * g) + "\\pi\\,\\text{cm}^2\\)");            // apenas lateral (πrg)
		distratores.add("\\(" + (r * (g + 2 * r)) + "\\pi\\,\\text{cm}^2\\)"); // r(g+2r)
		distratores.add("\\(" + (2 * r * gpr) + "\\pi\\,\\text{cm}^2\\)");     // 2r(g+r) confusão com cilindro
		embaralharEAdicionarAlternativas(correta, distratores);

		String res = "\\(A_{\\text{total}} = \\pi r(g + r) = \\pi \\cdot " + r + " \\cdot (" + g + " + " + r
				+ ") = \\\\"
				+ "\\pi \\cdot " + r + " \\cdot " + gpr + " = \\mathbf{" + coef + "\\pi}\\,\\text{cm}^2\\)";
		setResolucao(res);
	}
}
