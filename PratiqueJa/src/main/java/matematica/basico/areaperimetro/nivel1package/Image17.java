package matematica.basico.areaperimetro.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.Config;
import matematica.basico.areaperimetro.config.ConfigTrapezio2;

//	trapézio
public class Image17 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int B = 2 * (3 + rand.nextInt(13));
		int b = (int) (((double) B) * 0.65);
		int h = (int) (((double) B) * 0.55);
		int l = (int) (((double) B) * 0.7);

		String resultadoCorreto = "" + (B + b + h + l);
		String resolucao = ResolucaoAreaPerimetro.perimetroTrapezio(B, b, h, l);

		Config config = new ConfigTrapezio2(B + "", b + "", "" + h, "" + l, false);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Qual o perímetro do trapézio?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
