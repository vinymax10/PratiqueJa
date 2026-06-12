package matematica.intermediario.poligonosregulares.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.poligonosregulares.ResolucaoPoligonoRegular;
import matematica.intermediario.poligonosregulares.config.ConfigPoligonoRegular;

// Dado n, encontrar α_e = 360°/n
public class Exercicio3 extends GeradorExercicio
{
	// n values where 360/n is integer
	private static final int[][] CASOS = {
		{3, 120}, {4, 90}, {5, 72}, {6, 60}, {8, 45}, {9, 40}, {10, 36}, {12, 30}
	};

	@Override
	protected void construir()
	{
		int[] caso = CASOS[rand.nextInt(CASOS.length)];
		int n = caso[0];
		int ae = caso[1];

		String res = "\\(" + ResolucaoPoligonoRegular.formulaAnguloExterno() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(\\alpha_e = \\dfrac{360^\\circ}{" + n + "} = \\mathbf{" + ae + "^\\circ}\\)";

		ConfigPoligonoRegular config = new ConfigPoligonoRegular(n, false, "l", "");
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Qual é a medida de cada ângulo externo de um polígono regular de \\(" + n + "\\) lados?");
		addParagrafoImagem(image);
		gerarAlternativas("" + ae);
		setResolucao(res);
	}
}
