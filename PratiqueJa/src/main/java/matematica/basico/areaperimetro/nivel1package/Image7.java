package matematica.basico.areaperimetro.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigLozango;

public class Image7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int D = 2 * (3 + rand.nextInt(13));
		int d = (int) (((double) D) * 0.7);

		String resultadoCorreto = "" + (D / 2 * d);
		String resolucao = ResolucaoAreaPerimetro.losango(D, d);

		ConfigLozango config = new ConfigLozango("" + D, "" + d, "", "", "", true);
		BufferedImage image = config.criarImagem();

		addParagrafo("Qual a área do losango?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
