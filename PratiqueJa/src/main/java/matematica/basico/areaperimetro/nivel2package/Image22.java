package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigQuadrado;

public class Image22 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 2 + rand.nextInt(18);

		String perimetro = "" + (4 * a) + "\\sqrt{2}";
		String resultadoCorreto = "" + (a * 2);

		ConfigQuadrado config = new ConfigQuadrado("l", "d", false);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se o perímetro do quadrado é \\(" + perimetro + "\\), qual o valor de \\(d\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + ResolucaoAreaPerimetro.formulaPerimetroQuadrado() + "\\)");
		addResolucao("\\(4 \\cdot l=" + perimetro + "\\)");
		addResolucao("\\(l=" + "\\dfrac{" + perimetro + "}{4}=" + a + " \\sqrt{2} \\)");
		addResolucao("\\(" + ResolucaoAreaPerimetro.formulaDiagonalQuadrado() + "\\)");
		addResolucao("\\(d=" + a + "\\sqrt{2} \\cdot \\sqrt{2}\\)");
		addResolucao("\\(d=" + a + "(\\sqrt{2})^2 = " + a + "\\cdot 2 =\\mathbf{" + (a * 2) + "}\\)");
	}
}
