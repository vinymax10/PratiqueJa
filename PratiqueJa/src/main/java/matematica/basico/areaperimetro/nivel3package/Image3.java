package matematica.basico.areaperimetro.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigQuadradoCircunferencia;

public class Image3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(10);

		String area = "" + a * 2 * a;

		String resultadoCorreto = "" + a;

		ConfigQuadradoCircunferencia config = new ConfigQuadradoCircunferencia("r", "l", true);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se a área do quadrado é \\(" + area + "\\), qual o valor de \\(r\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + ResolucaoAreaPerimetro.formulaAreaQuadrado() + "\\)");
		addResolucao("\\(l^2=" + area + "\\)");
		addResolucao("\\(l=" + "\\sqrt{" + area + "} = " + a + "\\sqrt{2}\\)");
		addResolucao("\\(" + ResolucaoAreaPerimetro.formulaDiagonalQuadrado() + ", \\quad d=2r\\)");
		addResolucao("\\(2r=l\\sqrt{2}\\)");
		addResolucao("\\(2r=" + a + "\\sqrt{2} \\cdot \\sqrt{2}\\)");
		addResolucao("\\(2r=" + a + "(\\sqrt{2})^2 = " + a + "\\cdot 2 =" + (a * 2) + "\\)");
		addResolucao("\\(r=\\dfrac{" + (a * 2) + "}{2} = \\mathbf{" + a + "}\\)");
	}
}
