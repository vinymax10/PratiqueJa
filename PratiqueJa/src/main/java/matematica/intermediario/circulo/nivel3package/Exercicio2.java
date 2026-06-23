package matematica.intermediario.circulo.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.circulo.ResolucaoCirculo;
import matematica.intermediario.circulo.config.ConfigSetor;

// Dado As = kπ e θ, encontrar r = √(360k/θ)
public class Exercicio2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// (k, theta, r): r² = 360k/θ
		int[][] triples = {{1, 90, 2}, {4, 90, 4}, {9, 90, 6}, {16, 90, 8}, {6, 60, 6}, {3, 120, 3}, {12, 120, 6}, {2, 180, 2}, {8, 180, 4}};
		int[] triple = triples[rand.nextInt(triples.length)];
		int k = triple[0];
		int theta = triple[1];
		int r = triple[2];
		int r2 = 360 * k / theta; // r²

		ConfigSetor config = new ConfigSetor("r", theta);
		BufferedImage image = config.criarImagem();

		addParagrafo("A área de um setor circular é \\(" + k + "\\pi\\) e o ângulo central é \\(" + theta + "^\\circ\\). Qual é o raio \\(r\\)?");
		addParagrafoImagem(image);
		gerarAlternativas("" + r);
		addResolucao("\\(" + ResolucaoCirculo.formulaAreaSetor() + "\\)");
		addResolucao("\\(" + k + "\\pi = \\dfrac{" + theta + "}{360} \\cdot \\pi \\cdot r^2\\)");
		addResolucao("\\(" + k + " = \\dfrac{" + theta + " \\cdot r^2}{360}\\)");
		addResolucao("\\(r^2 = \\dfrac{360 \\cdot " + k + "}{" + theta + "} = " + r2 + "\\)");
		addResolucao("\\(r = \\sqrt{" + r2 + "} = \\mathbf{" + r + "}\\)");
	}
}
