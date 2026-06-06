package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.Config;
import matematica.basico.areaperimetro.config.ConfigTrapezio;

public class Image33 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 2 * (5 + rand.nextInt(15));
		int b = (int) (((double) a) * 0.65);
		int l = (int) (((double) a) * 0.7);

		String perimetro = "" + (a + b + l + l);

		String resultadoCorreto = "" + b;

		String resolucao = ResolucaoAreaPerimetro.formulaPerimetroTrapezio() + "\\\\";
		resolucao += "a=" + a + ",\\quad c=" + l + ",\\quad d=" + l + "\\\\";
		resolucao += a + "+b+" + l + "+" + l + "=" + perimetro + "\\\\";
		resolucao += "b=" + perimetro + "-" + a + "-" + l + "-" + l + "=" + b;

		Config config = new ConfigTrapezio("" + a, "b", "", l + "", l + "", false);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Se o perímetro do trapézio é \\(" + perimetro + "\\), qual o valor de \\(b\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
