package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigRetangulo;

//retângulo
public class Image14 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int h = 4 + rand.nextInt(14);
		String strLado = "" + h;

		String resultadoCorreto = "" + (2 * ((2 * h) + h));

		String resolucao = "";
		resolucao += ResolucaoAreaPerimetro.formulaPerimetroRetangulo() + "\\\\";
		resolucao += "b=2 \\cdot" + h + "=" + (2 * h) + "\\\\";
		resolucao += "P=2 \\cdot (" + (2 * h) + "+" + h + ")=2 \\cdot " + ((2 * h) + h) + "=" + (2 * ((2 * h) + h));

		ConfigRetangulo config = new ConfigRetangulo("2h", "h", false);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Se \\(h=" + strLado + "\\), qual o perímetro do retângulo?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
