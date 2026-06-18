package matematica.intermediario.circulo.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.circulo.ResolucaoCirculo;
import matematica.intermediario.circulo.config.ConfigSetor;

// Dado ℓ = kπ e r, encontrar θ = 180k/r
public class Exercicio13 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// (r, k, theta): ℓ = kπ, r given, θ = 180k/r
		int[][] casos = {{2, 1, 90}, {4, 2, 90}, {6, 3, 90}, {3, 1, 60}, {6, 2, 60}, {3, 2, 120}, {6, 4, 120}, {4, 4, 180}, {6, 6, 180}};
		int[] caso = casos[rand.nextInt(casos.length)];
		int r = caso[0];
		int k = caso[1];
		int theta = caso[2];

		String res = "\\(" + ResolucaoCirculo.formulaArco() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(" + k + "\\pi = \\dfrac{\\theta}{360} \\cdot 2\\pi \\cdot " + r + " \\\\ ";
		res += k + " = \\dfrac{2 \\cdot " + r + " \\cdot \\theta}{360} \\\\ ";
		res += "\\theta = \\dfrac{360 \\cdot " + k + "}{2 \\cdot " + r + "} = \\dfrac{" + (360 * k) + "}{" + (2 * r) + "} = \\mathbf{" + theta + "}^\\circ\\)";

		ConfigSetor config = new ConfigSetor("" + r, theta);
		BufferedImage image = config.criarImagem();

		addParagrafo("Um arco de raio \\(" + r + "\\) tem comprimento \\(" + k + "\\pi\\). Qual é o ângulo central?");
		addParagrafoImagem(image);
		gerarAlternativas("" + theta);
		setResolucao(res);
	}
}
