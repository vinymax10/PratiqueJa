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

		ConfigRetangulo config = new ConfigRetangulo("2h", "h", false);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se \\(h=" + strLado + "\\), qual o perímetro do retângulo?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + ResolucaoAreaPerimetro.formulaPerimetroRetangulo() + "\\)");
		addResolucao("\\(b=2 \\cdot" + h + "=" + (2 * h) + "\\)");
		addResolucao("\\(P=2 \\cdot (" + (2 * h) + "+" + h + ")=2 \\cdot " + ((2 * h) + h) + "=" + (2 * ((2 * h) + h)) + "\\)");
	}
}
