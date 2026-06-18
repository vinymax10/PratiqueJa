package matematica.avancado.funcaomodular.nivel2package;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;
import matematica.avancado.funcaomodular.config.ConfigFuncaoMod;
import matematica.avancado.funcaomodular.config.DadosFuncaoMod;

public class Image4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Gráfico em V com vértice (h, k) destacado; identificar vértice
		int h = 1 + rand.nextInt(4);  // 1..4 (positivo para rótulo à direita do eixo y)
		int k = 1 + rand.nextInt(3);  // 1..3

		DadosFuncaoMod dados = DadosFuncaoMod.comVertice(h, k);
		ConfigFuncaoMod config = new ConfigFuncaoMod(dados);
		config.indice       = 1 + rand.nextInt(10);
		config.mostrarPonto = true;
		config.labelPonto   = "" + k;

		BufferedImage image = config.criarImagem();

		addParagrafo("Qual é o vértice da função \\(f(x) = |x - h| + k\\) representada no gráfico?");
		addParagrafoImagem(image);

		String correto = "\\((" + h + ",\\," + k + ")\\)";
		List<String> dist = new ArrayList<>();
		dist.add("\\((" + (-h) + ",\\," + k + ")\\)");
		dist.add("\\((" + h + ",\\,0)\\)");
		dist.add("\\((0,\\," + k + ")\\)");
		embaralharEAdicionarAlternativas(correto, dist);

		String res = "As linhas tracejadas mostram \\(h = " + h + "\\) no eixo \\(x\\) e \\(k = " + k + "\\) no eixo \\(y\\). \\(\\\\\\)";
		res += "Vértice: \\(\\mathbf{(" + h + ",\\," + k + ")}\\)";
		setResolucao(res);
	}
}
