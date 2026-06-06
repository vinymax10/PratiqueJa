package matematica.basico.areaperimetro.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.Config;
import matematica.basico.areaperimetro.config.ConfigTrapezio2;

//	trapézio
public class Image6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int h = 2 * (2 + rand.nextInt(8));
		int b = (int) (((double) h) * 1.2);
		int B = (int) (((double) h) * 1.8);

		String resultadoCorreto = "" + (((B + b) * h) / 2);
		String resolucao = ResolucaoAreaPerimetro.areaTrapezio(h, B, b);

		Config config = new ConfigTrapezio2(B + "", b + "", h + "", "", true);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Qual a área do trapézio?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
