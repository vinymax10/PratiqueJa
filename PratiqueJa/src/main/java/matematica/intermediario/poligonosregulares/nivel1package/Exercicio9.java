package matematica.intermediario.poligonosregulares.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.poligonosregulares.ResolucaoPoligonoRegular;
import matematica.intermediario.poligonosregulares.config.ConfigPoligonoRegular;

// Dado n, encontrar α_i = (n-2)×180°/n
public class Exercicio9 extends GeradorExercicio
{
	// n values where α_i is integer
	private static final int[][] CASOS = {
		{3, 60}, {4, 90}, {5, 108}, {6, 120}, {8, 135}, {9, 140}, {10, 144}, {12, 150}
	};

	@Override
	protected void construir()
	{
		int[] caso = CASOS[rand.nextInt(CASOS.length)];
		int n = caso[0];
		int ai = caso[1];
		int si = (n - 2) * 180;

		String res = "\\(" + ResolucaoPoligonoRegular.formulaAnguloInterno() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(\\alpha_i = \\dfrac{(" + n + " - 2) \\times 180^\\circ}{" + n + "} = \\dfrac{" + si + "^\\circ}{" + n + "} = \\mathbf{" + ai + "^\\circ}\\)";

		ConfigPoligonoRegular config = new ConfigPoligonoRegular(n, false, "l", "");
		BufferedImage image = config.criarImagem();

		addParagrafo("Qual é a medida de cada ângulo interno de um polígono regular de \\(" + n + "\\) lados?");
		addParagrafoImagem(image);
		gerarAlternativas("" + ai);
		setResolucao(res);
	}
}
