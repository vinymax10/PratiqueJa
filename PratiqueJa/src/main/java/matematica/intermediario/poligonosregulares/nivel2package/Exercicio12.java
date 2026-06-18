package matematica.intermediario.poligonosregulares.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.poligonosregulares.ResolucaoPoligonoRegular;
import matematica.intermediario.poligonosregulares.config.ConfigPoligonoRegular;

// Dado A e apótema a, encontrar P = 2A/a (inverso de A = P·a/2)
public class Exercicio12 extends GeradorExercicio
{
	// {A, a, P}  onde P = 2A/a
	private static final int[][] CASOS = {
		{24, 3, 16}, {30, 5, 12}, {40, 8, 10}, {48, 6, 16},
		{60, 10, 12}, {42, 6, 14}, {35, 7, 10}, {56, 8, 14}
	};

	@Override
	protected void construir()
	{
		int[] caso = CASOS[rand.nextInt(CASOS.length)];
		int area = caso[0];
		int a = caso[1];
		int p = caso[2];

		String res = "\\(" + ResolucaoPoligonoRegular.formulaArea() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(" + area + " = \\dfrac{P \\times " + a + "}{2} \\\\ ";
		res += "P = \\dfrac{2 \\times " + area + "}{" + a + "} = \\mathbf{" + p + "}\\)";

		ConfigPoligonoRegular config = new ConfigPoligonoRegular(6, true, "l", "" + a);
		BufferedImage image = config.criarImagem();

		addParagrafo("A área de um polígono regular é \\(" + area + "\\) e o seu apótema mede \\(" + a + "\\). Qual é o seu perímetro?");
		addParagrafoImagem(image);
		gerarAlternativas("" + p);
		setResolucao(res);
	}
}
