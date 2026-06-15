package matematica.intermediario.poligonosregulares.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.poligonosregulares.ResolucaoPoligonoRegular;
import matematica.intermediario.poligonosregulares.config.ConfigPoligonoRegular;

// Dado α_e, calcular S_i em dois passos: α_e → n → S_i
public class Exercicio4 extends GeradorExercicio
{
	// {α_e, n, S_i}
	private static final int[][] CASOS = {
		{120, 3, 180}, {90, 4, 360}, {72, 5, 540}, {60, 6, 720},
		{45, 8, 1080}, {40, 9, 1260}, {36, 10, 1440}, {30, 12, 1800}
	};

	@Override
	protected void construir()
	{
		int[] caso = CASOS[rand.nextInt(CASOS.length)];
		int ae = caso[0];
		int n = caso[1];
		int si = caso[2];

		String res = "Passo 1: encontrar \\(n\\) a partir de \\(\\alpha_e\\)";
		res += "\\(\\\\\\)";
		res += "\\(" + ResolucaoPoligonoRegular.formulaAnguloExterno() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(n = \\dfrac{360^\\circ}{" + ae + "^\\circ} = " + n + "\\)";
		res += "\\(\\\\\\)";
		res += "Passo 2: calcular \\(S_i\\)";
		res += "\\(\\\\\\)";
		res += "\\(" + ResolucaoPoligonoRegular.formulaSomaInternos() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(S_i = (" + n + " - 2) \\times 180^\\circ = \\mathbf{" + si + "^\\circ}\\)";

		ConfigPoligonoRegular config = new ConfigPoligonoRegular(n, false, "l", "");
		BufferedImage image = config.criarImagem();

		addParagrafo("Cada ângulo externo de um polígono regular mede \\(" + ae + "^\\circ\\). Qual é a soma dos ângulos internos desse polígono?");
		addParagrafoImagem(image);
		gerarAlternativas("" + si);
		setResolucao(res);
	}
}
