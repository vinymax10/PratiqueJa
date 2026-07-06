package matematica.basico.planocartesiano.nivel2package;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;
import matematica.basico.planocartesiano.config.ConfigPlanoCartesianoAB;

public class Image3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Gera M(mx, my) e semi-diferenças h; A = M - h, B = M + h → |A-B| = 2h (sempre ≥ 2)
		int mx, my, ax, bx, ay, by;
		do
		{
			mx = rand.nextInt(9) - 4; // -4 a 4
			my = rand.nextInt(9) - 4;
			int hdx = 1 + rand.nextInt(3);
			int hdy = 1 + rand.nextInt(3);
			if (rand.nextBoolean()) hdx = -hdx;
			if (rand.nextBoolean()) hdy = -hdy;
			ax = mx - hdx; bx = mx + hdx;
			ay = my - hdy; by = my + hdy;
		}
		while (ax == 0 || bx == 0 || ay == 0 || by == 0 || ax == bx || ay == by || (mx == 0 && my == 0));

		ConfigPlanoCartesianoAB config = new ConfigPlanoCartesianoAB(ax, ay, bx, by, 9);
		BufferedImage image = config.criarImagem();

		addParagrafo("Os pontos A e B estão indicados no plano. Qual é o ponto médio do segmento \\( \\overline{AB} \\)?");
		addParagrafoImagem(image);

		String correta = par(mx, my);
		List<String> alts = new ArrayList<>();
		alts.add(par(ax + bx, ay + by)); // erro clássico: esquecer de dividir por 2
		int[][] offsets = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
		for (int[] off : offsets)
		{
			String alt = par(mx + off[0], my + off[1]);
			if (!alt.equals(correta) && !alts.contains(alt) && alts.size() < 3)
				alts.add(alt);
		}
		embaralharEAdicionarAlternativas(correta, alts);

		addResolucao("Do plano, lemos: \\(A(" + ax + ",\\;" + ay + ")\\) e \\(B(" + bx + ",\\;" + by + ")\\).");
		addResolucao("Aplicando a fórmula do ponto médio:");
		addResolucao("\\(M = \\left(\\dfrac{x_A + x_B}{2},\\;\\dfrac{y_A + y_B}{2}\\right)\\).");
		String bxStr = bx < 0 ? "(" + bx + ")" : "" + bx;
		String byStr = by < 0 ? "(" + by + ")" : "" + by;
		addResolucao("\\(M = \\left(\\dfrac{" + ax + " + " + bxStr + "}{2},\\;\\dfrac{" + ay + " + " + byStr + "}{2}\\right) =\\)");
		addResolucao("\\(\\left(\\dfrac{" + (ax + bx) + "}{2},\\;\\dfrac{" + (ay + by) + "}{2}\\right) = \\mathbf{(" + mx + ",\\;" + my + ")}\\)");
	}

	private String par(int x, int y)
	{
		return "\\((" + x + ",\\;" + y + ")\\)";
	}
}
