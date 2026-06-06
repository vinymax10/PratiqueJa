package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.Config;
import matematica.basico.areaperimetro.config.ConfigTrianguloIsosceles;

public class Image16 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 2 * (5 + rand.nextInt(13));
		int a = (int) (((double) b) * 0.7);// altura
		int c = a;

		int perimetro = (b + a + c);

		String resultadoCorreto = "" + b;

		String resolucao = ResolucaoAreaPerimetro.formulaPerimetroTrianguloIsosceles() + "\\\\";
		resolucao += "a=" + a + "\\\\";
		resolucao += "2 \\cdot" + a + "+b=" + perimetro + "\\\\";
		resolucao += (2 * a) + "+b=" + perimetro + "\\\\";
		resolucao += "b=" + perimetro + "-" + (2 * a) + "=" + b;

		Config config = new ConfigTrianguloIsosceles("b", "", "", "" + a, false);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Se o perímetro do triângulo é \\(" + perimetro + "\\), qual o valor de \\(b\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
