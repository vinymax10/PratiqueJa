package matematica.intermediario.poligonosregulares.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.poligonosregulares.ResolucaoPoligonoRegular;
import matematica.intermediario.poligonosregulares.config.ConfigPoligonoRegular;

// Dado n, encontrar S_i = (n-2)×180°
public class Exercicio1 extends GeradorExercicio
{
	// n values with integer S_i
	private static final int[][] CASOS = {
		{3, 180}, {4, 360}, {5, 540}, {6, 720}, {8, 1080}, {9, 1260}, {10, 1440}, {12, 1800}
	};

	@Override
	protected void construir()
	{
		int[] caso = CASOS[rand.nextInt(CASOS.length)];
		int n = caso[0];
		int si = caso[1];

		ConfigPoligonoRegular config = new ConfigPoligonoRegular(n, false, "l", "");
		BufferedImage image = config.criarImagem();

		addParagrafo("Qual é a soma dos ângulos internos de um polígono regular de \\(" + n + "\\) lados?");
		addParagrafoImagem(image);
		gerarAlternativas("" + si);
		addResolucao("\\(" + ResolucaoPoligonoRegular.formulaSomaInternos() + "\\)");
		addResolucao("\\(S_i = (" + n + " - 2) \\times 180^\\circ = " + (n - 2) + " \\times 180^\\circ = \\mathbf{" + si + "^\\circ}\\)");
	}
}
