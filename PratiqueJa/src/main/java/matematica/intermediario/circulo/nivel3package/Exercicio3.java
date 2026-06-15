package matematica.intermediario.circulo.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.circulo.ResolucaoCirculo;
import matematica.intermediario.circulo.config.ConfigSetor;

// Dado ℓ = kπ e θ, encontrar r = 180k/θ
public class Exercicio3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// (k, theta, r): r = 180k/θ
		int[][] triples = {{1, 90, 2}, {2, 90, 4}, {3, 90, 6}, {1, 60, 3}, {2, 60, 6}, {2, 120, 3}, {4, 120, 6}, {2, 180, 2}, {4, 180, 4}};
		int[] triple = triples[rand.nextInt(triples.length)];
		int k = triple[0];
		int theta = triple[1];
		int r = triple[2];

		String res = "\\(" + ResolucaoCirculo.formulaArco() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(" + k + "\\pi = \\dfrac{" + theta + "}{360} \\cdot 2\\pi r \\\\ ";
		res += k + " = \\dfrac{" + theta + "}{180} \\cdot r \\\\ ";
		res += "r = \\dfrac{180 \\cdot " + k + "}{" + theta + "} = \\dfrac{" + (180 * k) + "}{" + theta + "} = \\mathbf{" + r + "}\\)";

		ConfigSetor config = new ConfigSetor("r", theta);
		BufferedImage image = config.criarImagem();

		addParagrafo("O comprimento de um arco é \\(" + k + "\\pi\\) e o ângulo central é \\(" + theta + "^\\circ\\). Qual é o raio \\(r\\)?");
		addParagrafoImagem(image);
		gerarAlternativas("" + r);
		setResolucao(res);
	}
}
