package matematica.intermediario.circulo.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.circulo.ResolucaoCirculo;
import matematica.intermediario.circulo.config.ConfigCoroa;

// Dado A_coroa = kπ e r (raio interno), encontrar R = √(k + r²)
public class Exercicio11 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// (k, smallR, bigR): k + smallR² = bigR²
		int[][] triples = {{16, 3, 5}, {9, 4, 5}, {20, 4, 6}, {32, 2, 6}, {24, 5, 7}, {12, 2, 4}, {8, 1, 3}};
		int[] triple = triples[rand.nextInt(triples.length)];
		int k = triple[0];
		int smallR = triple[1];
		int bigR = triple[2];

		String res = "\\(" + ResolucaoCirculo.formulaAreaCoroa() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(" + k + "\\pi = \\pi \\left(R^2 - " + smallR + "^2\\right) \\\\ ";
		res += k + " = R^2 - " + (smallR * smallR) + " \\\\ ";
		res += "R^2 = " + k + " + " + (smallR * smallR) + " = " + (k + smallR * smallR) + " \\\\ ";
		res += "R = \\sqrt{" + (k + smallR * smallR) + "} = \\mathbf{" + bigR + "}\\)";

		ConfigCoroa config = new ConfigCoroa("R", "" + smallR);
		BufferedImage image = config.criarImagem();

		addParagrafo("A área de uma coroa circular é \\(" + k + "\\pi\\) e o raio interno mede \\(" + smallR + "\\). Qual é o raio externo \\(R\\)?");
		addParagrafoImagem(image);
		gerarAlternativas("" + bigR);
		setResolucao(res);
	}
}
