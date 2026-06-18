package matematica.avancado.funcaomodular.nivel3package;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;
import matematica.avancado.funcaomodular.config.ConfigFuncaoMod;
import matematica.avancado.funcaomodular.config.DadosFuncaoMod;

public class Image3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Gráfico mostra V com vértice (h, k) e linha horizontal y=c
		// Pergunta: resolver f(x) < c  →  h-delta < x < h+delta
		int[] hs = {-2, -1, 1, 2};
		int h     = hs[rand.nextInt(4)];
		int k     = rand.nextInt(3);          // 0..2
		int delta = 2 + rand.nextInt(2);      // 2..3 (c = k+delta ≤ 5, dentro do viewport)
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
		addParagrafo("Para quais valores de \\(x\\) temos \\(f(x) < " + c + "\\)?");

		String correto = "\\(" + low + " < x < " + high + "\\)";
		List<String> dist = new ArrayList<>();
		dist.add("\\(x < " + low + "\\) ou \\(x > " + high + "\\)");
		dist.add("\\(" + low + " \\leq x \\leq " + high + "\\)");
		dist.add("\\(" + (low - 1) + " < x < " + (high + 1) + "\\)");
		embaralharEAdicionarAlternativas(correto, dist);

		String res = "\\(f(x) < " + c + " \\Leftrightarrow |x - (" + h + ")| + " + k + " < " + c + "\\\\";
		res += "|x - " + h + "| < " + c + " - " + k + " = " + delta + "\\) \\(\\\\\\)";
		res += "Regra: \\(|u| < r \\Leftrightarrow -r < u < r\\): \\(\\\\\\)";
		res += "\\(-" + delta + " < x - " + h + " < " + delta + "\\\\";
		res += "\\mathbf{" + low + " < x < " + high + "}\\)";
		setResolucao(res);
	}
}
