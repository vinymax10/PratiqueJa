package matematica.basico.areaperimetro.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigRetangulo2;

public class Image8 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int h = 2 * (2 + rand.nextInt(10));
		int b = (int) (((double) h) * 1.35);
		int raio = h / 2;

		String area = "" + b * raio * 2;

		String resultadoCorreto = "" + raio;

		String resolucao = ResolucaoAreaPerimetro.formulaAreaRetangulo() + "\\\\";
		resolucao += "b=" + b + "\\\\";
		resolucao += b + "h=" + area + "\\\\";
		resolucao += "h=\\dfrac{" + area + "}{" + b + "}=" + h + "\\\\";
		resolucao += "2r=" + h + "\\\\";
		resolucao += "r=\\dfrac{" + h + "}{" + 2 + "}=" + raio + "\\\\";

		ConfigRetangulo2 config = new ConfigRetangulo2("" + b, "h", "r", true);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Se a área do retângulo é \\(" + area + "\\), qual o valor de \\(r\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
