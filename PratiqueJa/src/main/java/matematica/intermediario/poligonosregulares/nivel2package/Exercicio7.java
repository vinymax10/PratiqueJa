package matematica.intermediario.poligonosregulares.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.poligonosregulares.ResolucaoPoligonoRegular;
import matematica.intermediario.poligonosregulares.config.ConfigPoligonoRegular;

// Dado S_i, encontrar n  →  n = S_i/180 + 2
public class Exercicio7 extends GeradorExercicio
{
	private static final int[][] CASOS = {
		{180, 3}, {360, 4}, {540, 5}, {720, 6}, {1080, 8}, {1260, 9}, {1440, 10}, {1800, 12}
	};

	@Override
	protected void construir()
	{
		int[] caso = CASOS[rand.nextInt(CASOS.length)];
		int si = caso[0];
		int n = caso[1];

		String res = "\\(" + ResolucaoPoligonoRegular.formulaSomaInternos() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(" + si + "^\\circ = (n-2) \\times 180^\\circ \\\\ ";
		res += "n - 2 = \\dfrac{" + si + "}{180} = " + (n - 2) + " \\\\ ";
		res += "n = \\mathbf{" + n + "}\\)";

		ConfigPoligonoRegular config = new ConfigPoligonoRegular(n, false, "l", "");
		BufferedImage image = config.criarImagem();

		addParagrafo("A soma dos ângulos internos de um polígono regular é \\(" + si + "^\\circ\\). Quantos lados ele tem?");
		addParagrafoImagem(image);
		gerarAlternativas("" + n);
		setResolucao(res);
	}
}
