package matematica.avancado.funcaomodular.nivel3package;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;
import matematica.avancado.funcaomodular.config.ConfigFuncaoMod;
import matematica.avancado.funcaomodular.config.DadosFuncaoMod;

public class Image2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Gráfico mostra V com vértice (h, k) e linha horizontal y=c
		// Pergunta: resolver f(x) > c  →  x < h-delta ou x > h+delta
		int[] hs = {-2, -1, 1, 2};
		int h     = hs[rand.nextInt(4)];
		int k     = rand.nextInt(3);          // 0..2
		int delta = 2 + rand.nextInt(2);      // 2..3
		int c     = k + delta;

		int low  = h - delta;
		int high = h + delta;

		DadosFuncaoMod dados = DadosFuncaoMod.comVertice(h, k);
		ConfigFuncaoMod config = new ConfigFuncaoMod(dados);
		config.indice       = 1 + rand.nextInt(10);
		config.mostrarPonto = true;
		config.labelPonto   = "" + k;
		config.mostrarLinha = true;
		config.yLinha       = c;
		config.labelLinha   = "" + c;

		BufferedImage image = config.criarImagem();

		String modStr = h > 0 ? "|x - " + h + "|" : "|x + " + (-h) + "|";
		String funcStr = k == 0 ? modStr : modStr + " + " + k;

		addParagrafo("O gráfico mostra \\(f(x) = " + funcStr + "\\) e a reta \\(y = " + c + "\\).");
		addParagrafoImagem(image);
		addParagrafo("Para quais valores de \\(x\\) temos \\(f(x) > " + c + "\\)?");

		String correto = "\\(x < " + low + "\\) ou \\(x > " + high + "\\)";
		List<String> dist = new ArrayList<>();
		dist.add("\\(" + low + " < x < " + high + "\\)");
		dist.add("\\(x \\leq " + low + "\\) ou \\(x \\geq " + high + "\\)");
		dist.add("\\(x < " + (low - 1) + "\\) ou \\(x > " + (high + 1) + "\\)");
		embaralharEAdicionarAlternativas(correto, dist);

		addResolucao("\\(f(x) > " + c + " \\Leftrightarrow |x - (" + h + ")| + " + k + " > " + c + "\\)");
		addResolucao("\\(|x - " + h + "| > " + delta + "\\)");
		addResolucao("Regra: \\(|u| > r \\Leftrightarrow u < -r\\) ou \\(u > r\\):");
		addResolucao("\\(x - " + h + " < -" + delta + "\\) ou \\(x - " + h + " > " + delta + "\\)");
		addResolucao("\\(\\mathbf{x < " + low + "}\\) ou \\(\\mathbf{x > " + high + "}\\)");
	}
}
