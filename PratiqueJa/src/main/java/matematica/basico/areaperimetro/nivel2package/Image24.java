package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigParalelogramo;

public class Image24 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 2 * (3 + rand.nextInt(12));
		int a = (int) (((double) b) * 0.7);

		String perimetro = "" + (2 * (b + a));
		String resultadoCorreto = "" + a;

		ConfigParalelogramo config = new ConfigParalelogramo("" + b, "", "a", false);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se o perímetro do paralelogramo é \\(" + perimetro + "\\), qual o valor de \\(a\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + ResolucaoAreaPerimetro.formulaPerimetroParalelogramo() + "\\)");
		addResolucao("\\(b=" + b + "\\)");
		addResolucao("\\(2 \\cdot(a + " + b + ")=" + perimetro + "\\)");
		addResolucao("\\(a +" + b + "=\\dfrac{" + perimetro + "}{2}=" + (b + a) + "\\)");
		addResolucao("\\(a=" + (b + a) + "-" + b + "=" + a + "\\)");
	}
}
