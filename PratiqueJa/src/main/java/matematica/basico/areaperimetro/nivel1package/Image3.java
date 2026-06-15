package matematica.basico.areaperimetro.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigParalelogramo;

//	paralelogramo
public class Image3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 2 * (3 + rand.nextInt(12));
		int h = (int) (((double) b) * 0.7);

		String resultadoCorreto = "" + (b * h);
		String resolucao = ResolucaoAreaPerimetro.areaRetangulo(b, h);

		ConfigParalelogramo config = new ConfigParalelogramo("" + b, "" + h, "", true);
		BufferedImage image = config.criarImagem();

		addParagrafo("Qual a área do paralelogramo?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
