package matematica.avancado.geometriaespacial.nivel2package;

import matematica.GeradorExercicio;

public class Exercicio1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int[][] triples = {{3, 4, 5}, {5, 12, 13}, {6, 8, 10}, {8, 15, 17}, {9, 12, 15}};
		int[]   t       = triples[rand.nextInt(triples.length)];
		int r = t[0], h = t[1], g = t[2];
		int r2 = r * r, h2 = h * h;

		addParagrafo("Um cone tem raio \\(r = " + r + "\\,\\text{cm}\\) e altura \\(h = " + h
				+ "\\,\\text{cm}\\). Calcule a geratriz \\(g\\).");

		gerarAlternativasInteiras(g, 4, true);

		addResolucao("Pelo Teorema de Pitágoras:");
		addResolucao("\\(g = \\sqrt{r^2 + h^2} = \\sqrt{" + r + "^2 + " + h + "^2} =\\)");
		addResolucao("\\(\\sqrt{"+ r2 + " + " + h2 + "} = "
				+ "\\sqrt{" + (r2 + h2) + "} = \\mathbf{" + g + "}\\,\\text{cm}\\)");
	}
}
