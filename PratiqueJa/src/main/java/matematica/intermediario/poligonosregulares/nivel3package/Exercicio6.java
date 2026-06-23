package matematica.intermediario.poligonosregulares.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.poligonosregulares.ResolucaoPoligonoRegular;
import matematica.intermediario.poligonosregulares.config.ConfigPoligonoRegular;

// Quadrado: dado A = l² (quadrado perfeito), calcular P = 4l
public class Exercicio6 extends GeradorExercicio
{
	// {A, l, P}  onde A = l²
	private static final int[][] CASOS = {
		{4, 2, 8}, {9, 3, 12}, {16, 4, 16}, {25, 5, 20},
		{36, 6, 24}, {49, 7, 28}, {64, 8, 32}, {100, 10, 40}
	};

	@Override
	protected void construir()
	{
		int[] caso = CASOS[rand.nextInt(CASOS.length)];
		int area = caso[0];
		int l = caso[1];
		int p = caso[2];

		ConfigPoligonoRegular config = new ConfigPoligonoRegular(4, false, "" + l, "");
		BufferedImage image = config.criarImagem();

		addParagrafo("A área de um quadrado é \\(" + area + "\\). Qual é o seu perímetro?");
		addParagrafoImagem(image);
		gerarAlternativas("" + p);
		addResolucao("Passo 1: encontrar \\(l\\) a partir da área do quadrado \\((A = l^2)\\)");
		addResolucao("\\(l = \\sqrt{A} = \\sqrt{" + area + "} = " + l + "\\)");
		addResolucao("Passo 2: calcular \\(P\\)");
		addResolucao("\\(" + ResolucaoPoligonoRegular.formulaPerimetro() + "\\)");
		addResolucao("\\(P = 4 \\times " + l + " = \\mathbf{" + p + "}\\)");
	}
}
