package matematica.intermediario.circulo.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.circulo.ResolucaoCirculo;
import matematica.intermediario.circulo.config.ConfigCoroa;

// Dado A_coroa = kπ e R (raio externo), encontrar r = √(R² - k)
public class Exercicio9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// (k, bigR, smallR): bigR² - k = smallR²
		int[][] triples = {{16, 5, 3}, {9, 5, 4}, {20, 6, 4}, {32, 6, 2}, {24, 7, 5}, {12, 4, 2}, {8, 3, 1}};
		int[] triple = triples[rand.nextInt(triples.length)];
		int k = triple[0];
		int bigR = triple[1];
		int smallR = triple[2];
		int r2 = bigR * bigR - k;

		String res = "\\(" + ResolucaoCirculo.formulaAreaCoroa() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(" + k + "\\pi = \\pi \\left(" + bigR + "^2 - r^2\\right) \\\\ ";
		res += k + " = " + (bigR * bigR) + " - r^2 \\\\ ";
		res += "r^2 = " + (bigR * bigR) + " - " + k + " = " + r2 + " \\\\ ";
		res += "r = \\sqrt{" + r2 + "} = \\mathbf{" + smallR + "}\\)";

		ConfigCoroa config = new ConfigCoroa("" + bigR, "r");
		BufferedImage image = config.criarImagem();

		addParagrafo("A área de uma coroa circular é \\(" + k + "\\pi\\) e o raio externo mede \\(" + bigR + "\\). Qual é o raio interno \\(r\\)?");
		addParagrafoImagem(image);
		gerarAlternativas("" + smallR);
		setResolucao(res);
	}
}
