package matematica.basico.areaperimetro.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.Config;
import matematica.basico.areaperimetro.config.ConfigTrianguloEquilatero;

//	triângulo
public class Image15 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int l = 2 * (3 + rand.nextInt(13));

		String resultadoCorreto = "" + (3 * l);
		String resolucao = ResolucaoAreaPerimetro.perimetroTrianguloEquilatero(l);

		Config config = new ConfigTrianguloEquilatero("" + l, false);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Qual o perímetro do triângulo?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
