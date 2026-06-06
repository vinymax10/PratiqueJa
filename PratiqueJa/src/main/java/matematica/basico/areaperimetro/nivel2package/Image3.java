package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigRetangulo3;

//Retangulo com quadrado dentro
public class Image3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 2 * (3 + rand.nextInt(12));
		int h = (int) (((double) b) * 0.7);
		int sobra = b - h;

		String strD = "" + h + "\\sqrt{2}";
		String resultadoCorreto = "" + h * (h + sobra);

		String resolucao = ResolucaoAreaPerimetro.formulaDiagonalQuadrado() +
		", \\quad " + ResolucaoAreaPerimetro.formulaAreaRetangulo() + "\\\\";
		resolucao += "l=" + h + "\\\\";
		resolucao += "h=" + h + "\\\\";
		resolucao += "b=l+" + sobra + " = " + h + "+" + sobra + "=" + (h + sobra) + "\\\\";
		resolucao += "A=" + b + " \\cdot " + h + "=" + (b * h);

		ConfigRetangulo3 config = new ConfigRetangulo3("d", "" + sobra, true);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Se \\(d=" + strD + "\\), qual a área do retângulo?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
