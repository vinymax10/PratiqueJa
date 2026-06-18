package matematica.intermediario.poligonosregulares.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.poligonosregulares.ResolucaoPoligonoRegular;
import matematica.intermediario.poligonosregulares.config.ConfigPoligonoRegular;

// Dado P e n, encontrar l = P/n
public class Exercicio11 extends GeradorExercicio
{
	// {n, P, l}
	private static final int[][] CASOS = {
		{3, 15, 5}, {4, 20, 5}, {5, 30, 6}, {6, 42, 7},
		{8, 32, 4}, {9, 36, 4}, {10, 50, 5}, {12, 60, 5}
	};

	@Override
	protected void construir()
	{
		int[] caso = CASOS[rand.nextInt(CASOS.length)];
		int n = caso[0];
		int p = caso[1];
		int l = caso[2];

		String res = "\\(" + ResolucaoPoligonoRegular.formulaPerimetro() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(" + p + " = " + n + " \\times l \\\\ ";
		res += "l = \\dfrac{" + p + "}{" + n + "} = \\mathbf{" + l + "}\\)";

		ConfigPoligonoRegular config = new ConfigPoligonoRegular(n, false, "l", "");
		BufferedImage image = config.criarImagem();

		addParagrafo("O perímetro de um polígono regular de \\(" + n + "\\) lados é \\(" + p + "\\). Qual é a medida de cada lado?");
		addParagrafoImagem(image);
		gerarAlternativas("" + l);
		setResolucao(res);
	}
}
