package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.ConfigImagem;
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

		ConfigImagem config = new ConfigTrapezio2("a", "" + b, "" + h, "" + l, false);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se o perímetro do trapézio é \\(" + perimetro + "\\), qual o valor de \\(a\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + ResolucaoAreaPerimetro.formulaPerimetroTrapezio() + "\\)");
		addResolucao("\\(b=" + b + ",\\quad c=" + h + ",\\quad d=" + l + "\\)");
		addResolucao("\\(a+" + b + "+" + h + "+" + l + "=" + perimetro + "\\)");
		addResolucao("\\(a=" + perimetro + "-" + b + "-" + h + "-" + l + "=" + a + "\\)");
	}
}
