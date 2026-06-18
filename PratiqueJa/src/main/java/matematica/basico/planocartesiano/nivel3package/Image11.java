package matematica.basico.planocartesiano.nivel3package;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import matematica.GeradorExercicio;
import matematica.basico.planocartesiano.config.ConfigPlanoCartesiano;

public class Image11 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int px = 2 + rand.nextInt(5);
		int py = 2 + rand.nextInt(5);
		while (px == py || ehQuadradoPerfeito(px * px + py * py))
		{
			px = 2 + rand.nextInt(5);
			py = 2 + rand.nextInt(5);
		}
		if (rand.nextBoolean()) px = -px;
		if (rand.nextBoolean()) py = -py;

		int px2 = px * px;
		int py2 = py * py;
		int d2 = px2 + py2;
		String strPx = px < 0 ? "(" + px + ")" : "" + px;
		String strPy = py < 0 ? "(" + py + ")" : "" + py;

		// Verifica se √d² admite simplificação (ex: √8 = 2√2)
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

		ConfigPlanoCartesiano config = new ConfigPlanoCartesiano(px, py);
		BufferedImage image = config.criarImagem();

		addParagrafo("O ponto P está indicado no plano. Calcule a distância de P à origem \\( O(0,\\;0) \\). Simplifique o radical, se possível.");
		addParagrafoImagem(image);

		String correta = "\\(\\sqrt{" + d2 + "}\\)";
		if (simplificado[0] > 1)
			correta = "\\(" + raizStr + "\\)";

		Set<Integer> vals = new LinkedHashSet<>();
		vals.add(d2);
		for (int delta = 1; vals.size() < 4; delta++)
		{
			if (!vals.contains(d2 + delta))                        vals.add(d2 + delta);
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

		String res = "Do plano, lemos as coordenadas de P: \\(x = " + px + "\\) e \\(y = " + py + "\\). \\(\\\\\\)";
		res += "Aplicando a fórmula da distância à origem: \\(\\\\\\)";
		res += "\\(d(P,\\;O) = \\sqrt{x^2 + y^2} = \\sqrt{" + strPx + "^2 + " + strPy + "^2} =\\\\ \\sqrt{" + px2 + " + " + py2 + "} = \\sqrt{" + d2 + "}\\) ";
		res += simplStr;
		res += "Portanto, \\(d = " + raizStr + "\\).";

		setResolucao(res);
	}

	private boolean ehQuadradoPerfeito(int n)
	{
		int r = (int) Math.round(Math.sqrt(n));
		return r * r == n;
	}

	// Retorna {fatorInteiro, radicando} tal que √n = fatorInteiro·√radicando
	private int[] simplificarRadical(int n)
	{
		int fator = 1;
		for (int i = (int) Math.sqrt(n); i >= 2; i--)
		{
			if (n % (i * i) == 0)
			{
				fator = i;
				break;
			}
		}
		return new int[] {fator, n / (fator * fator)};
	}
}
