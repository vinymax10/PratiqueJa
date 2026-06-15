package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigRetangulo3;

//Retangulo com quadrado dentro
public class Image11 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 2 * (3 + rand.nextInt(12));
		int h = (int) (((double) b) * 0.7);
		int sobra = b - h;

		String strD = "" + h + "\\sqrt{2}";
		String resultadoCorreto = "" + (2 * (b + h));

		String resolucao = ResolucaoAreaPerimetro.formulaDiagonalQuadrado() +
		", \\quad " + ResolucaoAreaPerimetro.formulaPerimetroRetangulo() + "\\\\";
		resolucao += "l=" + h + "\\\\";
		resolucao += "h=" + h + "\\\\";
		resolucao += "b=l+" + sobra + " = " + h + "+" + sobra + "=" + (h + sobra) + "\\\\";
		resolucao += "P=2 \\cdot (" + b + " + " + h + ")=2 \\cdot " + (b + h) + "=" + (2 * (b + h));

		ConfigRetangulo3 config = new ConfigRetangulo3("d", "" + sobra, false);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se \\(d=" + strD + "\\), qual o perímetro do retângulo?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
