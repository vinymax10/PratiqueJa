package matematica.intermediario.poligonosregulares.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.poligonosregulares.ResolucaoPoligonoRegular;
import matematica.intermediario.poligonosregulares.config.ConfigPoligonoRegular;

// Dado n e l, calcular P = n·l
public class Exercicio13 extends GeradorExercicio
{
	// {n, l, P}
	private static final int[][] CASOS = {
		{3, 4, 12}, {4, 5, 20}, {5, 6, 30}, {6, 7, 42},
		{8, 3, 24}, {9, 4, 36}, {10, 5, 50}, {12, 6, 72}
	};

	@Override
	protected void construir()
	{
		int[] caso = CASOS[rand.nextInt(CASOS.length)];
		int n = caso[0];
		int l = caso[1];
		int p = caso[2];

		String res = "\\(" + ResolucaoPoligonoRegular.formulaPerimetro() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(P = " + n + " \\times " + l + " = \\mathbf{" + p + "}\\)";

		ConfigPoligonoRegular config = new ConfigPoligonoRegular(n, false, "" + l, "");
		BufferedImage image = config.criarImagem();

		addParagrafo("Um polígono regular de \\(" + n + "\\) lados tem lado medindo \\(" + l + "\\). Qual é o seu perímetro?");
		addParagrafoImagem(image);
		gerarAlternativas("" + p);
		setResolucao(res);
	}
}
