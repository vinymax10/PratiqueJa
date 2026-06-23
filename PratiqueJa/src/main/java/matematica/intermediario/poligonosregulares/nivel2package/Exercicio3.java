package matematica.intermediario.poligonosregulares.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.poligonosregulares.ResolucaoPoligonoRegular;
import matematica.intermediario.poligonosregulares.config.ConfigPoligonoRegular;

// Dado α_i, encontrar n  →  n = 360/(180 - α_i)  (via α_e = 180 - α_i)
public class Exercicio3 extends GeradorExercicio
{
	private static final int[][] CASOS = {
		{60, 3}, {90, 4}, {108, 5}, {120, 6}, {135, 8}, {140, 9}, {144, 10}, {150, 12}
	};

	@Override
	protected void construir()
	{
		int[] caso = CASOS[rand.nextInt(CASOS.length)];
		int ai = caso[0];
		int n = caso[1];
		int ae = 180 - ai;

		ConfigPoligonoRegular config = new ConfigPoligonoRegular(n, false, "l", "");
		BufferedImage image = config.criarImagem();

		addParagrafo("Cada ângulo interno de um polígono regular mede \\(" + ai + "^\\circ\\). Quantos lados ele tem?");
		addParagrafoImagem(image);
		gerarAlternativas("" + n);
		addResolucao("\\(" + ResolucaoPoligonoRegular.formulaAnguloInterno() + "\\)");
		addResolucao("\\(" + ai + "^\\circ = \\dfrac{(n-2) \\times 180^\\circ}{n}\\)");
		addResolucao("\\(" + ai + "n = (n-2) \\times 180\\)");
		addResolucao("\\(" + ai + "n = 180n - 360\\)");
		addResolucao("\\(360 = " + ae + "n\\)");
		addResolucao("\\(n = \\dfrac{360}{" + ae + "} = \\mathbf{" + n + "}\\)");
	}
}
