package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.ConfigImagem;
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

		ConfigImagem config = new ConfigTrapezio("" + a, "b", "", l + "", l + "", false);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se o perímetro do trapézio é \\(" + perimetro + "\\), qual o valor de \\(b\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + ResolucaoAreaPerimetro.formulaPerimetroTrapezio() + "\\)");
		addResolucao("\\(a=" + a + ",\\quad c=" + l + ",\\quad d=" + l + "\\)");
		addResolucao("\\(" + a + "+b+" + l + "+" + l + "=" + perimetro + "\\)");
		addResolucao("\\(b=" + perimetro + "-" + a + "-" + l + "-" + l + "=\\mathbf{" + b + "}\\)");
	}
}
