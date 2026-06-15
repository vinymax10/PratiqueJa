package matematica.basico.areaperimetro.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigRetangulo;

//	Perímetro retângulo
public class Image10 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 2 * (3 + rand.nextInt(12));
		int h = (int) (((double) b) * 0.7);

		String resultadoCorreto = "" + (2 * (b + h));
		String resolucao = ResolucaoAreaPerimetro.perimetroRetangulo(b, h);

		ConfigRetangulo config = new ConfigRetangulo("" + b, "" + h, false);
		BufferedImage image = config.criarImagem();

		addParagrafo("Qual o perímetro do retângulo?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
