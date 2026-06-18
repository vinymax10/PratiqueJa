package matematica.intermediario.poligonosregulares.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.poligonosregulares.ResolucaoPoligonoRegular;
import matematica.intermediario.poligonosregulares.config.ConfigPoligonoRegular;

// Dado α_e, encontrar n = 360/α_e
public class Exercicio14 extends GeradorExercicio
{
	private static final int[][] CASOS = {
		{120, 3}, {90, 4}, {72, 5}, {60, 6}, {45, 8}, {40, 9}, {36, 10}, {30, 12}
	};

	@Override
	protected void construir()
	{
		int[] caso = CASOS[rand.nextInt(CASOS.length)];
		int ae = caso[0];
		int n = caso[1];

		String res = "\\(" + ResolucaoPoligonoRegular.formulaAnguloExterno() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(" + ae + "^\\circ = \\dfrac{360^\\circ}{n} \\\\ ";
		res += "n = \\dfrac{360^\\circ}{" + ae + "^\\circ} = \\mathbf{" + n + "}\\)";

		ConfigPoligonoRegular config = new ConfigPoligonoRegular(n, false, "l", "");
		BufferedImage image = config.criarImagem();

		addParagrafo("Cada ângulo externo de um polígono regular mede \\(" + ae + "^\\circ\\). Quantos lados ele tem?");
		addParagrafoImagem(image);
		gerarAlternativas("" + n);
		setResolucao(res);
	}
}
