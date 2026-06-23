package matematica.avancado.funcaomodular.nivel2package;

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
		// Gráfico em V com vértice (h, k); identificar a equação da função
		int h = 1 + rand.nextInt(3);  // 1..3 (positivo para rótulo limpo)
		int k = 1 + rand.nextInt(3);  // 1..3

		DadosFuncaoMod dados = DadosFuncaoMod.comVertice(h, k);
		ConfigFuncaoMod config = new ConfigFuncaoMod(dados);
		config.indice       = 1 + rand.nextInt(10);
		config.mostrarPonto = true;
		config.labelPonto   = "" + k;

		BufferedImage image = config.criarImagem();

		addParagrafo("Qual equação descreve a função \\(f\\) representada no gráfico?");
		addParagrafoImagem(image);

		String correto = "\\(f(x) = |x - " + h + "| + " + k + "\\)";
		List<String> dist = new ArrayList<>();
		dist.add("\\(f(x) = |x + " + h + "| + " + k + "\\)");
		dist.add("\\(f(x) = |x - " + h + "| - " + k + "\\)");
		dist.add("\\(f(x) = |x - " + k + "| + " + h + "\\)");
		embaralharEAdicionarAlternativas(correto, dist);

		addResolucao("O vértice está em \\((" + h + ",\\," + k + ")\\).");
		addResolucao("Na forma \\(f(x) = |x - h| + k\\): \\(h = " + h + "\\) e \\(k = " + k + "\\).");
		addResolucao("\\(\\mathbf{f(x) = |x - " + h + "| + " + k + "}\\)");
	}
}
