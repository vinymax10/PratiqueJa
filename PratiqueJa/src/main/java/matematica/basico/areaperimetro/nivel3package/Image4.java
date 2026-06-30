package matematica.basico.areaperimetro.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigParalelogramo;

public class Image4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 2 * (3 + rand.nextInt(12));
		int h = (int) (((double) b) * 0.7);

		String area = "" + b * h;
		String resultadoCorreto = "" + h;

		ConfigParalelogramo config = new ConfigParalelogramo("" + b, "h", "", true);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se a área do paralelogramo é \\(" + area + "\\), qual o valor de \\(h\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + ResolucaoAreaPerimetro.formulaAreaRetangulo() + "\\)");
		addResolucao("\\(b=" + b + "\\)");
		addResolucao("\\(" + b + "h=" + area + "\\)");
		addResolucao("\\(h=\\dfrac{" + area + "}{" + b + "}=\\mathbf{" + h + "}\\)");
	}
}
