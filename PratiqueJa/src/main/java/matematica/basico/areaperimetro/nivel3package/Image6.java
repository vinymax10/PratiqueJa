package matematica.basico.areaperimetro.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigRetangulo;

public class Image6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 2 * (3 + rand.nextInt(12));
		int h = (int) (((double) b) * 0.7);

		int area = b * h;

		String resultadoCorreto = "" + b;

		String resolucao = ResolucaoAreaPerimetro.formulaAreaRetangulo() + "\\\\";
		resolucao += "h=" + h + "\\\\";
		resolucao += h + "b=" + area + "\\\\";
		resolucao += "b=\\dfrac{" + area + "}{" + h + "}=" + b + "\\\\";

		ConfigRetangulo config = new ConfigRetangulo("b", "" + h, true);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se a área do retângulo é \\(" + area + "\\), qual o valor de \\(b\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
