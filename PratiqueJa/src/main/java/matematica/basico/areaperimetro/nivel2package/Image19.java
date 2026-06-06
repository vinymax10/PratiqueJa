package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.Config;
import matematica.basico.areaperimetro.config.ConfigTrianguloRetangulo;

public class Image19 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 2 * (5 + rand.nextInt(13));
		int a = (int) (((double) b) * 0.6);// altura
		int c = (int) (((double) b) * 1.2);// hipotenusa

		String perimetro = "" + (b + a + c);

		String resultadoCorreto = "" + b;

		String resolucao = ResolucaoAreaPerimetro.formulaPerimetroTriangulo() + "\\\\";
		resolucao += "a=" + a + ",\\quad c=" + c + "\\\\";
		resolucao += a + "+b+" + c + "=" + perimetro + "\\\\";
		resolucao += "b=" + perimetro + "-" + a + "-" + c + "=" + b;

		Config config = new ConfigTrianguloRetangulo("b", "" + a, "" + c, false);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Se o perímetro do triângulo é \\(" + perimetro + "\\), qual o valor de \\(b\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
