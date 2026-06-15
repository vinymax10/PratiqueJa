package matematica.basico.areaperimetro.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.ConfigImagem;
import matematica.basico.areaperimetro.config.ConfigTriangulo;
import matematica.basico.areaperimetro.config.ConfigTrianguloIsosceles;
import matematica.basico.areaperimetro.config.ConfigTrianguloRetangulo;

//	triângulo
public class Image4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 2 * (3 + rand.nextInt(13));
		int h = (int) (((double) b) * 0.6);

		String resultadoCorreto = "" + ((b * h) / 2);
		String resolucao = ResolucaoAreaPerimetro.areaTriangulo(b, h);

		ConfigImagem config = null;

		int num = rand.nextInt(3);
		switch(num)
		{
			case 0: config = new ConfigTriangulo("" + b, "" + h, "", "", true); break;
			case 1: config = new ConfigTrianguloRetangulo("" + b, "" + h, "", true); break;
			case 2: config = new ConfigTrianguloIsosceles("" + b, "" + h, "", "", true); break;
		}

		BufferedImage image = config.criarImagem();

		addParagrafo("Qual a área do triângulo?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
