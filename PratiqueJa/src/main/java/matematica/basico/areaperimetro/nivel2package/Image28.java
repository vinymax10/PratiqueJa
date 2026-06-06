package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigRetangulo2;

public class Image28 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int h = 2 * (2 + rand.nextInt(10));
		int b = (int) (((double) h) * 1.35);
		int raio = h / 2;

		String perimetro = "" + b * raio * 2;

		String resultadoCorreto = "" + raio;

		String resolucao = ResolucaoAreaPerimetro.formulaPerimetroRetangulo() + "\\\\";
		resolucao += "b=" + b + "\\\\";
		resolucao += "2 \\cdot(" + b + " + h)=" + perimetro + "\\\\";
		resolucao += b + " + h=\\dfrac{" + perimetro + "}{2}=" + (b + h) + "\\\\";
		resolucao += "h=" + (b + h) + "-" + b + "=" + h + "\\\\";
		resolucao += "2r=" + h + "\\\\";
		resolucao += "r=\\dfrac{" + h + "}{" + 2 + "}=" + raio + "\\\\";

		ConfigRetangulo2 config = new ConfigRetangulo2("" + b, "h", "r", false);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Se o perímetro do retângulo é \\(" + perimetro + "\\), qual o valor de \\(r\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
