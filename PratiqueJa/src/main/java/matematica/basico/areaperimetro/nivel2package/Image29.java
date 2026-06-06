package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigRetangulo;

public class Image29 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int h = 4 + rand.nextInt(16);

		String resultadoCorreto = "" + h;
		int perimetro = 2 * ((2 * h) + h);

		String resolucao = ResolucaoAreaPerimetro.formulaPerimetroRetangulo() + "\\\\";
		resolucao += "2 \\cdot (2h + h)=" + perimetro + "\\\\";
		resolucao += "2h + h=\\dfrac{" + perimetro + "}{2}=" + (perimetro / 2) + "\\\\";
		resolucao += "3h=" + (perimetro / 2) + "\\\\";
		resolucao += "h=\\dfrac{" + (perimetro / 2) + "}{3}=" + h + "\\\\";

		ConfigRetangulo config = new ConfigRetangulo("2h", "h", false);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Se o perímetro do retângulo é \\(" + perimetro + "\\), qual o valor de \\(h\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
