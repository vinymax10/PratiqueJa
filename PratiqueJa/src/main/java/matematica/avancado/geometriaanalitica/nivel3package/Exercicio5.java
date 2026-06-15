package matematica.avancado.geometriaanalitica.nivel3package;

import matematica.GeradorExercicio;

public class Exercicio5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int[][] triplas = {{3, 4, 5}, {5, 12, 13}, {6, 8, 10}, {8, 15, 17}};
		int[] tripla = triplas[rand.nextInt(triplas.length)];
		int dx = tripla[0];
		int dy = tripla[1];
		int r  = tripla[2];

		if (rand.nextBoolean()) dx = -dx;
		if (rand.nextBoolean()) dy = -dy;

		int cx = -3 + rand.nextInt(7);
		int cy = -3 + rand.nextInt(7);
		int px = cx + dx;
		int py = cy + dy;

		int dx2 = dx * dx;
		int dy2 = dy * dy;
		int soma = dx2 + dy2;

		addParagrafo("Uma circunferência de centro \\(C(" + cx + ";\\;" + cy + ")\\) passa pelo ponto "
				+ "\\(P(" + px + ";\\;" + py + ")\\). Qual o raio?");

		gerarAlternativasInteiras(r);

		String res = "O raio é a distância \\(d(C, P)\\):"
				+ "\\(\\\\\\)"
				+ "\\(r = \\sqrt{(" + px + " - " + cx + ")^2 + (" + py + " - " + cy + ")^2}"
				+ " = \\sqrt{" + dx + "^2 + " + dy + "^2}"
				+ " = \\\\ \\sqrt{" + dx2 + " + " + dy2 + "}"
				+ " = \\sqrt{" + soma + "} = \\mathbf{" + r + "}\\)";
		setResolucao(res);
	}
}
