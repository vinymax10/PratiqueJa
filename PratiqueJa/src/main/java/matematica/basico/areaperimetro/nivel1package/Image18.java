package matematica.basico.areaperimetro.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigLozango;

public class Image18 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int l = 3 + rand.nextInt(20);

		String resultadoCorreto = "" + (4 * l);
		String resolucao = ResolucaoAreaPerimetro.perimetroQuadrado(l);

		ConfigLozango config = new ConfigLozango("", "", "", "", "" + l, false);
		BufferedImage image = config.criarImagem();

		addParagrafo("Qual o perímetro do losango?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
