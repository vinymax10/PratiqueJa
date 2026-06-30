package matematica.basico.areaperimetro.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigRetangulo;

public class Image9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int h = 4 + rand.nextInt(16);

		String resultadoCorreto = "" + h;
		int area = h * (2 * h);

		ConfigRetangulo config = new ConfigRetangulo("2h", "h", true);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se a área do retângulo é \\(" + area + "\\), qual o valor de \\(h\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + ResolucaoAreaPerimetro.formulaAreaRetangulo() + "\\)");
		addResolucao("\\(2h \\cdot h=" + area + "\\)");
		addResolucao("\\(2h^2=" + area + "\\)");
		addResolucao("\\(h^2=\\dfrac{" + area + "}{" + 2 + "}=" + (h * h) + "\\)");
		addResolucao("\\(h=\\sqrt{" + (h * h) + "}=\\mathbf{" + h + "}\\)");
	}
}
