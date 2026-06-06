package matematica.basico.areaperimetro.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.Config;
import matematica.basico.areaperimetro.config.ConfigTrianguloRetangulo;

//	triângulo
public class Image13 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 2 * (5 + rand.nextInt(13));
		int a = (int) (((double) b) * 0.6);// altura
		int c = (int) (((double) b) * 1.2);// hipotenusa

		String resultadoCorreto = "" + (b + a + c);
		String resolucao = ResolucaoAreaPerimetro.perimetroTriangulo(a, b, c);

		Config config = new ConfigTrianguloRetangulo("" + b, "" + a, "" + c, false);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Qual o perímetro do triângulo?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
