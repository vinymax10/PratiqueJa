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

		String resolucao = ResolucaoAreaPerimetro.formulaPerimetroParalelogramo() + "\\\\";
		resolucao += "b=" + b + "\\\\";
		resolucao += "2 \\cdot(a + " + b + ")=" + perimetro + "\\\\";
		resolucao += "a +" + b + "=\\dfrac{" + perimetro + "}{2}=" + (b + a) + "\\\\";
		resolucao += "a=" + (b + a) + "-" + b + "=" + a + "\\\\";

		ConfigParalelogramo config = new ConfigParalelogramo("" + b, "", "a", false);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Se o perímetro do paralelogramo é \\(" + perimetro + "\\), qual o valor de \\(a\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
