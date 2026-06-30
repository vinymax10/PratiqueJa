package matematica.basico.planocartesiano.nivel2package;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import matematica.GeradorExercicio;
import matematica.basico.planocartesiano.config.ConfigPlanoCartesiano;

public class Image2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int px = 1 + rand.nextInt(5);
		int py = 1 + rand.nextInt(5);

		// Garante que d² não é quadrado perfeito (resultado em radical)
		while (ehQuadradoPerfeito(px * px + py * py))
		{
			px = 1 + rand.nextInt(5);
			py = 1 + rand.nextInt(5);
		}
		if (rand.nextBoolean()) px = -px;
		if (rand.nextBoolean()) py = -py;

		int px2 = px * px;
		int py2 = py * py;
		int d2 = px2 + py2;
		String strPx = px < 0 ? "(" + px + ")" : "" + px;
		String strPy = py < 0 ? "(" + py + ")" : "" + py;

		ConfigPlanoCartesiano config = new ConfigPlanoCartesiano(px, py);
		BufferedImage image = config.criarImagem();

		addParagrafo("O ponto P está indicado no plano. Calcule a distância de P à origem \\( O(0,\\;0) \\).");
		addParagrafoImagem(image);

		String correta = "\\(\\sqrt{" + d2 + "}\\)";
		Set<Integer> vals = new LinkedHashSet<>();
		vals.add(d2);
		for (int delta = 1; vals.size() < 4; delta++)
		{
			if (!vals.contains(d2 + delta))           vals.add(d2 + delta);
			if (vals.size() < 4 && d2 - delta > 0 && !vals.contains(d2 - delta)) vals.add(d2 - delta);
		}
		List<String> distratores = new ArrayList<>();
		for (int v : vals)
			if (v != d2) distratores.add("\\(\\sqrt{" + v + "}\\)");
		embaralharEAdicionarAlternativas(correta, distratores);

		addResolucao("Do plano, lemos as coordenadas de P: \\(x = " + px + "\\) e \\(y = " + py + "\\). Aplicando a fórmula da distância à origem:");
		addResolucao("\\(d(P,\\;O) = \\sqrt{x^2 + y^2} = \\sqrt{" + strPx + "^2 + " + strPy + "^2} =\\)");
		addResolucao("\\(\\sqrt{" + px2 + " + " + py2 + "} = \\mathbf{\\sqrt{" + d2 + "}}\\)");
	}

	private boolean ehQuadradoPerfeito(int n)
	{
		int r = (int) Math.round(Math.sqrt(n));
		return r * r == n;
	}
}
