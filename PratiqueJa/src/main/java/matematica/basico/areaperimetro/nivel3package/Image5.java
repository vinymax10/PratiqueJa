package matematica.basico.areaperimetro.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigQuadrado3;

public class Image5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(10);

		String area = "" + (4 * a * a);
		String resultadoCorreto = "" + a;

		ConfigQuadrado3 config = new ConfigQuadrado3("l", "r", true);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se a área do quadrado é \\(" + area + "\\), qual o valor de \\(r\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + ResolucaoAreaPerimetro.formulaAreaQuadrado() + "\\)");
		addResolucao("\\(l^2=" + area + "\\)");
		addResolucao("\\(l=" + "\\sqrt{" + area + "} = " + (2 * a) + "\\)");
		addResolucao("\\(r=\\dfrac{l}{2}=\\dfrac{" + (2 * a) + "}{2}=" + a + "\\)");
	}
}
