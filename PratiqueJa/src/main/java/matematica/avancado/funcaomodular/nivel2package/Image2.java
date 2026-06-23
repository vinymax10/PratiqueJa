package matematica.avancado.funcaomodular.nivel2package;

import java.awt.image.BufferedImage;
import matematica.GeradorExercicio;
import matematica.avancado.funcaomodular.config.ConfigFuncaoMod;
import matematica.avancado.funcaomodular.config.DadosFuncaoMod;

public class Image2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Gráfico em V com ponto fora do vértice destacado; ler f(pontoX) do gráfico
		int h     = rand.nextInt(3);          // 0..2
		int k     = rand.nextInt(3);          // 0..2
		int delta = 2 + rand.nextInt(3);      // 2..4 (garante pontoX = h+delta > 0)
		int pontoX = h + delta;               // sempre > 0

		DadosFuncaoMod dados = DadosFuncaoMod.comPonto(h, k, pontoX);
		int pontoY = dados.pontoY;            // = delta + k

		ConfigFuncaoMod config = new ConfigFuncaoMod(dados);
		config.indice       = 1 + rand.nextInt(10);
		config.mostrarPonto = true;
		config.labelPonto   = "" + pontoY;

		BufferedImage image = config.criarImagem();

		addParagrafo("Leia no gráfico: qual é o valor de \\(f(" + pontoX + ")\\) para a função representada?");
		addParagrafoImagem(image);

		gerarAlternativas("" + pontoY);

		addResolucao("A linha tracejada vertical em \\(x = " + pontoX + "\\) "
			+ "encontra o gráfico na altura \\(y = " + pontoY + "\\).");
		addResolucao("\\(f(" + pontoX + ") = |" + pontoX + " - " + h + "| + " + k + " = " + delta + " + " + k + " = \\mathbf{" + pontoY + "}\\)");
	}
}
