package matematica.basico.planocartesiano.nivel3package;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import matematica.GeradorExercicio;
import matematica.basico.planocartesiano.config.ConfigPlanoCartesianoAB;

public class Image18 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int ax, ay, bx, by, dx, dy, d2;
		do
		{
			ax = rand.nextInt(7) - 3; // -3 a 3
			ay = rand.nextInt(7) - 3;
			dx = 2 + rand.nextInt(3); if (rand.nextBoolean()) dx = -dx; // |dx| ≥ 2
			dy = 2 + rand.nextInt(3); if (rand.nextBoolean()) dy = -dy;
			bx = ax + dx;
			by = ay + dy;
			d2 = dx * dx + dy * dy;
		}
		while (ax == 0 || bx == 0 || ay == 0 || by == 0
				|| Math.abs(bx) > 7 || Math.abs(by) > 7
				|| ehQuadradoPerfeito(d2));

		int dx2 = dx * dx, dy2 = dy * dy;
		String strDx = dx < 0 ? "(" + dx + ")" : "" + dx;
		String strDy = dy < 0 ? "(" + dy + ")" : "" + dy;
		String innerX = ax >= 0 ? bx + " - " + ax : bx + " - (" + ax + ")";
		String innerY = ay >= 0 ? by + " - " + ay : by + " - (" + ay + ")";

		int[] simplificado = simplificarRadical(d2);
		String raizStr;
		String simplStr = "";
		if (simplificado[0] > 1)
		{
			raizStr = simplificado[0] + "\\sqrt{" + simplificado[1] + "}";
			simplStr = "Simplificando o radical: \\(\\\\\\)";
			simplStr += "\\(\\sqrt{" + d2 + "} = \\sqrt{" + simplificado[0] * simplificado[0] + " \\cdot " + simplificado[1] + "} = " + raizStr + "\\) \\(\\\\\\) ";
		}
		else
		{
			raizStr = "\\sqrt{" + d2 + "}";
		}

		String correta = simplificado[0] > 1 ? "\\(" + raizStr + "\\)" : "\\(\\sqrt{" + d2 + "}\\)";

		ConfigPlanoCartesianoAB config = new ConfigPlanoCartesianoAB(ax, ay, bx, by);
		BufferedImage image = config.criarImagem();

		addParagrafo("Os pontos A e B estão indicados no plano. Calcule a distância \\( d(A,B) \\). Simplifique o radical, se possível.");
		addParagrafoImagem(image);

		Set<Integer> vals = new LinkedHashSet<>();
		vals.add(d2);
		for (int delta = 1; vals.size() < 4; delta++)
		{
			if (!vals.contains(d2 + delta)) vals.add(d2 + delta);
			if (vals.size() < 4 && d2 - delta > 0 && !vals.contains(d2 - delta)) vals.add(d2 - delta);
		}
		List<String> distratores = new ArrayList<>();
		for (int v : vals)
		{
			if (v == d2) continue;
			int[] s = simplificarRadical(v);
			String alt;
			if (s[1] == 1)      alt = "\\(" + s[0] + "\\)";
			else if (s[0] > 1)  alt = "\\(" + s[0] + "\\sqrt{" + s[1] + "}\\)";
			else                alt = "\\(\\sqrt{" + v + "}\\)";
			if (!alt.equals(correta) && !distratores.contains(alt)) distratores.add(alt);
		}
		embaralharEAdicionarAlternativas(correta, distratores);

		String res = "Do plano, lemos: \\(A(" + ax + ",\\;" + ay + ")\\) e \\(B(" + bx + ",\\;" + by + ")\\). \\(\\\\\\)";
		res += "Aplicando a fórmula da distância entre dois pontos: \\(\\\\\\)";
		res += "\\(d(A,B) = \\sqrt{(x_B - x_A)^2 + (y_B - y_A)^2} = \\\\";
		res += "\\sqrt{(" + innerX + ")^2 + (" + innerY + ")^2} = \\\\"
		+ "\\sqrt{" + strDx + "^2 + " + strDy + "^2} =\\\\ ";
		res += "\\sqrt{" + dx2 + " + " + dy2 + "} = \\sqrt{" + d2 + "}\\\\ \\) ";
		res += simplStr;
		res += "Portanto, \\(d(A,B) = " + raizStr + "\\).";

		setResolucao(res);
	}

	private boolean ehQuadradoPerfeito(int n)
	{
		int r = (int) Math.round(Math.sqrt(n));
		return r * r == n;
	}

	private int[] simplificarRadical(int n)
	{
		int fator = 1;
		for (int i = (int) Math.sqrt(n); i >= 2; i--)
		{
			if (n % (i * i) == 0) { fator = i; break; }
		}
		return new int[] {fator, n / (fator * fator)};
	}
}
