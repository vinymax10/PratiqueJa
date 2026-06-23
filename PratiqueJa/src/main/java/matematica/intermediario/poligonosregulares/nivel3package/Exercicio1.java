package matematica.intermediario.poligonosregulares.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.poligonosregulares.ResolucaoPoligonoRegular;
import matematica.intermediario.poligonosregulares.config.ConfigPoligonoRegular;

// Dado α_i, determinar S_i em dois passos: α_i → n → S_i
public class Exercicio1 extends GeradorExercicio
{
	private static final int[][] CASOS = {
		{60, 3, 180}, {90, 4, 360}, {108, 5, 540}, {120, 6, 720},
		{135, 8, 1080}, {140, 9, 1260}, {144, 10, 1440}, {150, 12, 1800}
	};

	@Override
	protected void construir()
	{
		int[] caso = CASOS[rand.nextInt(CASOS.length)];
		int ai = caso[0];
		int n = caso[1];
		int si = caso[2];
		int ae = 180 - ai;

		ConfigPoligonoRegular config = new ConfigPoligonoRegular(n, false, "l", "");
		BufferedImage image = config.criarImagem();

		addParagrafo("Cada ângulo interno de um polígono regular mede \\(" + ai + "^\\circ\\). Qual é a soma dos ângulos internos desse polígono?");
		addParagrafoImagem(image);
		gerarAlternativas("" + si);
		addResolucao("Passo 1: encontrar \\(n\\) a partir de \\(\\alpha_e = 180^\\circ - \\alpha_i\\)");
		addResolucao("\\(\\alpha_e = 180^\\circ - " + ai + "^\\circ = " + ae + "^\\circ\\)");
		addResolucao("\\(n = \\dfrac{360^\\circ}{\\alpha_e} = \\dfrac{360^\\circ}{" + ae + "^\\circ} = " + n + "\\)");
		addResolucao("Passo 2: calcular \\(S_i\\)");
		addResolucao("\\(" + ResolucaoPoligonoRegular.formulaSomaInternos() + "\\)");
		addResolucao("\\(S_i = (" + n + " - 2) \\times 180^\\circ = \\mathbf{" + si + "^\\circ}\\)");
	}
}
