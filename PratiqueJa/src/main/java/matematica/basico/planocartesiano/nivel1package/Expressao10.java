package matematica.basico.planocartesiano.nivel1package;

import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;

public class Expressao10 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Gera ponto médio M e semi-diferenças; A = M - h, B = M + h
		int mx = rand.nextInt(7) - 3; // -3 a 3
		int my = rand.nextInt(7) - 3;
		int hdx = 1 + rand.nextInt(3);
		int hdy = 1 + rand.nextInt(3);
		if (rand.nextBoolean()) hdx = -hdx;
		if (rand.nextBoolean()) hdy = -hdy;

		int ax = mx - hdx, bx = mx + hdx;
		int ay = my - hdy, by = my + hdy;

		addParagrafo("Dados os pontos \\( A(" + ax + ",\\;" + ay + ") \\) e \\( B(" + bx + ",\\;" + by + ") \\), qual é o ponto médio do segmento \\( \\overline{AB} \\)?");

		String correta = par(mx, my);
		List<String> alts = new ArrayList<>();
		alts.add(par(mx * 2, my * 2)); // erro clássico: esquecer de dividir por 2
		int[][] offsets = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
		for (int[] off : offsets)
		{
			String alt = par(mx + off[0], my + off[1]);
			if (!alt.equals(correta) && !alts.contains(alt) && alts.size() < 3)
				alts.add(alt);
		}
		embaralharEAdicionarAlternativas(correta, alts);

		String res = "O ponto médio \\(M\\) de um segmento \\(\\overline{AB}\\) é calculado fazendo a média aritmética das coordenadas dos extremos: ";
		res += "\\(M = \\left(\\dfrac{x_A + x_B}{2},\\;\\dfrac{y_A + y_B}{2}\\right)\\). \\(\\\\\\)";
		res += "Substituindo \\(A(" + ax + ",\\;" + ay + ")\\) e \\(B(" + bx + ",\\;" + by + ")\\): \\(\\\\\\)";
		res += "\\(M = \\left(\\dfrac{" + ax + " + " + bx + "}{2},\\;\\dfrac{" + ay + " + " + by + "}{2}\\right) =\\\\ ";
		res += "\\left(\\dfrac{" + (ax + bx) + "}{2},\\;\\dfrac{" + (ay + by) + "}{2}\\right) = (" + mx + ",\\;" + my + ")\\)";

		setResolucao(res);
	}

	private String par(int x, int y)
	{
		return "\\((" + x + ",\\;" + y + ")\\)";
	}
}
