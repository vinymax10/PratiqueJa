package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigRetangulo;

//retângulo
public class Image6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int h = 4 + rand.nextInt(14);
		String strLado = "" + h;

		String resultadoCorreto = "" + h * (2 * h);

		ConfigRetangulo config = new ConfigRetangulo("2h", "h", true);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se \\(h=" + strLado + "\\), qual a área do retângulo?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + ResolucaoAreaPerimetro.formulaAreaRetangulo() + "\\)");
		addResolucao("\\(b=2 \\cdot" + h + "=" + (2 * h) + "\\)");
		addResolucao("\\(A=" + (2 * h) + " \\cdot " + h + "=" + (2 * h * h) + "\\)");
	}
}
