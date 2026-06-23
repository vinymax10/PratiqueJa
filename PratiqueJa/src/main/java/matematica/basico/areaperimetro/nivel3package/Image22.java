package matematica.basico.areaperimetro.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigTrianguloEquilatero;

// Dado A = k√3, encontrar o lado l do triângulo equilátero
public class Image22 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// A = l²√3/4 = k√3 → l² = 4k → l = 2√k
		// Para l inteiro, escolher k = m²: l = 2m
		int m = 2 + rand.nextInt(5);   // m = 2..6 → l = 4,6,8,10,12
		int l = 2 * m;
		int k = m * m;                 // A = k√3

		ConfigTrianguloEquilatero config = new ConfigTrianguloEquilatero("l", true);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se a área do triângulo equilátero é \\(" + k + "\\sqrt{3}\\), qual o valor de \\(l\\)?");
		addParagrafoImagem(image);
		gerarAlternativas("" + l);
		addResolucao("\\(" + ResolucaoAreaPerimetro.formulaAreaTrianguloEquilatero() + "\\)");
		addResolucao("\\(\\dfrac{l^2 \\cdot \\sqrt{3}}{4} = " + k + "\\sqrt{3} \\)");
		addResolucao("\\(l^2 = 4 \\cdot " + k + " = " + (4 * k) + " \\)");
		addResolucao("\\(l = \\sqrt{" + (4 * k) + "} = \\mathbf{" + l + "}\\)");
	}
}
