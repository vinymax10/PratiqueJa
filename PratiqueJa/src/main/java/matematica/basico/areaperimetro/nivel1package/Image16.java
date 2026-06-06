package matematica.basico.areaperimetro.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.Config;
import matematica.basico.areaperimetro.config.ConfigTrapezio;

//	trapézio
public class Image16 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int B = 2 * (3 + rand.nextInt(13));
		int b = (int) (((double) B) * 0.65);
		int l = (int) (((double) B) * 0.7);

		String resultadoCorreto = "" + (B + b + l + l);
		String resolucao = ResolucaoAreaPerimetro.perimetroTrapezio(B, b, l, l);

		Config config = new ConfigTrapezio(B + "", b + "", "", "" + l, "" + l, false);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Qual o perímetro do trapézio?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
