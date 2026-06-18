package matematica.avancado.geometriaanalitica.nivel1package;

import matematica.GeradorExercicio;

public class Exercicio15 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int[][] triples = {{3,4,5},{5,12,13},{6,8,10},{8,15,17},{9,12,15}};
		int[] t = triples[rand.nextInt(triples.length)];
		int dx = rand.nextBoolean() ? t[0] : -t[0];
		int dy = rand.nextBoolean() ? t[1] : -t[1];
		int dist = t[2];

		int x1 = -4 + rand.nextInt(5);
		int y1 = -4 + rand.nextInt(5);
		int x2 = x1 + dx;
		int y2 = y1 + dy;

		int dx2 = dx * dx;
		int dy2 = dy * dy;

		addParagrafo("Calcule a distância entre os pontos \\(A(" + x1 + ";\\;" + y1 + ")\\) e \\(B(" + x2 + ";\\;" + y2 + ")\\).");

		gerarAlternativasInteiras(dist);

		String res = "Aplicando a fórmula da distância entre dois pontos:"
				+ "\\(\\\\\\)"
				+ "\\(d_{AB} = \\sqrt{(x_2 - x_1)^2 + (y_2 - y_1)^2}\\\\"
				+ "d_{AB} = \\sqrt{(" + dx + ")^2 + (" + dy + ")^2}\\\\"
				+ "d_{AB} = \\sqrt{" + dx2 + " + " + dy2 + "} = \\sqrt{" + (dx2 + dy2) + "} = \\mathbf{" + dist + "}\\)";
		setResolucao(res);
	}
}
