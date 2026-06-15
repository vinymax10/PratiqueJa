package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigRetangulo;

public class Image27 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 2 * (3 + rand.nextInt(12));
		int h = (int) (((double) b) * 0.7);

		int perimetro = 2 * (b + h);

		String resultadoCorreto = "" + h;

		String resolucao = ResolucaoAreaPerimetro.formulaPerimetroRetangulo() + "\\\\";
		resolucao += "b=" + b + "\\\\";
		resolucao += "2 \\cdot(" + b + " + h)=" + perimetro + "\\\\";
		resolucao += b + " + h=\\dfrac{" + perimetro + "}{2}=" + (b + h) + "\\\\";
		resolucao += "h=" + (b + h) + "-" + b + "=" + h + "\\\\";

		ConfigRetangulo config = new ConfigRetangulo("" + b, "h", false);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se o perímetro do retângulo é \\(" + perimetro + "\\), qual o valor de \\(h\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
