package matematica.avancado.geometriaanalitica.nivel1package;

import matematica.GeradorExercicio;

public class Exercicio14 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int[][] triplas = {{3, 4, 5}, {5, 12, 13}, {8, 15, 17}};
		int[] tripla = triplas[rand.nextInt(triplas.length)];
		int dx   = tripla[0];
		int dy   = tripla[1];
		int dist = tripla[2];

		if (rand.nextBoolean()) dx = -dx;
		if (rand.nextBoolean()) dy = -dy;

		int px  = dx;
		int py  = dy;
		int px2 = px * px;
		int py2 = py * py;
		int soma = px2 + py2;

		addParagrafo("Calcule a distância de \\(O(0;\\;0)\\) a \\(P(" + px + ";\\;" + py + ")\\).");

		gerarAlternativasInteiras(dist);

		String res = "Pela fórmula da distância com a origem:"
				+ "\\(\\\\\\)"
				+ "\\(d = \\sqrt{" + px + "^2 + " + py + "^2} = \\sqrt{" + px2 + " + " + py2 + "} = \\sqrt{" + soma + "} = \\mathbf{" + dist + "}\\)";
		setResolucao(res);
	}
}
