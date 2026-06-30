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

		ConfigRetangulo3 config = new ConfigRetangulo3("d", "" + sobra, true);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se \\(d=" + strD + "\\), qual a área do retângulo?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + ResolucaoAreaPerimetro.formulaDiagonalQuadrado() + ", \\quad " + ResolucaoAreaPerimetro.formulaAreaRetangulo() + "\\)");
		addResolucao("\\(l=" + h + "\\)");
		addResolucao("\\(h=" + h + "\\)");
		addResolucao("\\(b=l+" + sobra + " = " + h + "+" + sobra + "=" + (h + sobra) + "\\)");
		addResolucao("\\(A=" + b + " \\cdot " + h + "=\\mathbf{" + (b * h) + "}\\)");
	}
}
