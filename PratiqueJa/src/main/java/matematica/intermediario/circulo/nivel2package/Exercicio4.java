package matematica.intermediario.circulo.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.circulo.ResolucaoCirculo;
import matematica.intermediario.circulo.config.ConfigSetor;

// Dado As = kπ e r, encontrar θ = 360k/r²
public class Exercicio4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// (r, k, theta): As = kπ, r given, θ = 360k/r²
		int[][] casos = {{2, 1, 90}, {4, 4, 90}, {6, 9, 90}, {3, 3, 120}, {6, 12, 120}, {2, 2, 180}, {4, 8, 180}};
		int[] caso = casos[rand.nextInt(casos.length)];
		int r = caso[0];
		int k = caso[1];
		int theta = caso[2];

		String res = "\\(" + ResolucaoCirculo.formulaAreaSetor() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(" + k + "\\pi = \\dfrac{\\theta}{360} \\cdot \\pi \\cdot " + r + "^2 \\\\ ";
		res += k + " = \\dfrac{" + (r * r) + " \\cdot \\theta}{360} \\\\ ";
		res += "\\theta = \\dfrac{360 \\cdot " + k + "}{" + (r * r) + "} = \\dfrac{" + (360 * k) + "}{" + (r * r) + "} = \\mathbf{" + theta + "}^\\circ\\)";

		ConfigSetor config = new ConfigSetor("" + r, theta);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Um setor circular de raio \\(" + r + "\\) tem área \\(" + k + "\\pi\\). Qual é o ângulo central?");
		addParagrafoImagem(image);
		gerarAlternativas("" + theta);
		setResolucao(res);
	}
}
