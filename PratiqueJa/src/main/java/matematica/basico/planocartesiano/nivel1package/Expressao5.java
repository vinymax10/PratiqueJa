package matematica.basico.planocartesiano.nivel1package;

import matematica.GeradorExercicio;

public class Expressao5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int[][] triples = {{3, 4, 5}, {4, 3, 5}, {6, 8, 10}, {5, 12, 13}};
		int[] t = triples[rand.nextInt(triples.length)];
		int dx = t[0], dy = t[1], dist = t[2];
		if (rand.nextBoolean()) dx = -dx;
		if (rand.nextBoolean()) dy = -dy;

		int ax = rand.nextInt(5) - 2; // -2 a 2
		int ay = rand.nextInt(5) - 2;
		int bx = ax + dx;
		int by = ay + dy;

		int dx2 = dx * dx, dy2 = dy * dy, d2 = dx2 + dy2;
		String strDx = dx < 0 ? "(" + dx + ")" : "" + dx;
		String strDy = dy < 0 ? "(" + dy + ")" : "" + dy;
		String innerX = ax >= 0 ? bx + " - " + ax : bx + " - (" + ax + ")";
		String innerY = ay >= 0 ? by + " - " + ay : by + " - (" + ay + ")";

		addParagrafo("Calcule a distância entre os pontos \\( A(" + ax + ",\\;" + ay + ") \\) e \\( B(" + bx + ",\\;" + by + ") \\).");

		gerarAlternativasInteiras(dist);

		addResolucao("A distância entre dois pontos \\(A(x_A,\\;y_A)\\) e \\(B(x_B,\\;y_B)\\) é dada pela fórmula:");
		addResolucao("\\(d(A,B) = \\sqrt{(x_B - x_A)^2 + (y_B - y_A)^2}\\).");
		addResolucao("Calculando as diferenças: \\(x_B - x_A = " + innerX + " = " + dx + "\\) e \\(y_B - y_A = " + innerY + " = " + dy + "\\).");
		addResolucao("Substituindo na fórmula:");
		addResolucao("\\(d = \\sqrt{" + strDx + "^2 + " + strDy + "^2} = \\sqrt{" + dx2 + " + " + dy2 + "} =\\)");
		addResolucao("\\(\\sqrt{" + d2 + "} = \\mathbf{" + dist + "}\\)");
	}
}
