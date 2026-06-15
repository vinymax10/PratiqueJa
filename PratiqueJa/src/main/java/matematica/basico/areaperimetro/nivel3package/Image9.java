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

		String resolucao = ResolucaoAreaPerimetro.formulaAreaRetangulo() + "\\\\";
		resolucao += "2h \\cdot h=" + area + "\\\\";
		resolucao += "2h^2=" + area + "\\\\";
		resolucao += "h^2=\\dfrac{" + area + "}{" + 2 + "}=" + (h * h) + "\\\\";
		resolucao += "h=\\sqrt{" + (h * h) + "}=" + h + "\\\\";

		ConfigRetangulo config = new ConfigRetangulo("2h", "h", true);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se a área do retângulo é \\(" + area + "\\), qual o valor de \\(h\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
