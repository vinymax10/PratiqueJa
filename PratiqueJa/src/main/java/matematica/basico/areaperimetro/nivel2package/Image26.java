package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigRetangulo;

public class Image26 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 2 * (3 + rand.nextInt(12));
		int h = (int) (((double) b) * 0.7);

		int perimetro = 2 * (b + h);

		String resultadoCorreto = "" + b;

		String resolucao = ResolucaoAreaPerimetro.formulaPerimetroRetangulo() + "\\\\";
		resolucao += "h=" + h + "\\\\";
		resolucao += "2 \\cdot(b + " + h + ")=" + perimetro + "\\\\";
		resolucao += "b + " + h + "=\\dfrac{" + perimetro + "}{2}=" + (b + h) + "\\\\";
		resolucao += "b=" + (b + h) + "-" + h + "=" + b + "\\\\";

		ConfigRetangulo config = new ConfigRetangulo("b", "" + h, false);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Se o perímetro do retângulo é \\(" + perimetro + "\\), qual o valor de \\(b\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
