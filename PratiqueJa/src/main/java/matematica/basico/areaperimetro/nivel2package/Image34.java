package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.Config;
import matematica.basico.areaperimetro.config.ConfigTrapezio2;

public class Image34 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 2 * (3 + rand.nextInt(13));
		int b = (int) (((double) a) * 0.65);
		int h = (int) (((double) a) * 0.55);
		int l = (int) (((double) a) * 0.7);

		String perimetro = "" + (a + b + h + l);

		String resultadoCorreto = "" + a;

		String resolucao = ResolucaoAreaPerimetro.formulaPerimetroTrapezio() + "\\\\";
		resolucao += "b=" + b + ",\\quad c=" + h + ",\\quad d=" + l + "\\\\";
		resolucao += "a+" + b + "+" + h + "+" + l + "=" + perimetro + "\\\\";
		resolucao += "a=" + perimetro + "-" + b + "-" + h + "-" + l + "=" + a;

		Config config = new ConfigTrapezio2("a", "" + b, "" + h, "" + l, false);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Se o perímetro do trapézio é \\(" + perimetro + "\\), qual o valor de \\(a\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
