package matematica.intermediario.poligonosregulares.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.poligonosregulares.config.ConfigPoligonoRegular;

// Dado α_i, calcular α_e = 180° − α_i (ângulos suplementares)
public class Exercicio7 extends GeradorExercicio
{
	// {n, α_i, α_e}
	private static final int[][] CASOS = {
		{3, 60, 120}, {4, 90, 90}, {5, 108, 72}, {6, 120, 60},
		{8, 135, 45}, {9, 140, 40}, {10, 144, 36}, {12, 150, 30}
	};

	@Override
	protected void construir()
	{
		int[] caso = CASOS[rand.nextInt(CASOS.length)];
		int n = caso[0];
		int ai = caso[1];
		int ae = caso[2];

		ConfigPoligonoRegular config = new ConfigPoligonoRegular(n, false, "l", "");
		BufferedImage image = config.criarImagem();

		addParagrafo("Cada ângulo interno de um polígono regular mede \\(" + ai + "^\\circ\\). Qual é a medida de cada ângulo externo?");
		addParagrafoImagem(image);
		gerarAlternativas("" + ae);
		addResolucao("Os ângulos interno e externo são suplementares: \\(\\alpha_i + \\alpha_e = 180^\\circ\\)");
		addResolucao("\\(\\alpha_e = 180^\\circ - " + ai + "^\\circ = \\mathbf{" + ae + "^\\circ}\\)");
	}
}
