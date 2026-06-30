package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigQuadrado3;

public class Image25 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int r = 2 + rand.nextInt(18);
		int l = 2 * r;
		String perimetro = "" + (4 * l);
		String resultadoCorreto = "" + r;

		ConfigQuadrado3 config = new ConfigQuadrado3("l", "r", false);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se o perímetro do quadrado é \\(" + perimetro + "\\), qual o valor de \\(r\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + ResolucaoAreaPerimetro.formulaPerimetroQuadrado() + "\\)");
		addResolucao("\\(4 \\cdot l=" + perimetro + "\\)");
		addResolucao("\\(l=" + "\\dfrac{" + perimetro + "}{4}=" + l + " \\)");
		addResolucao("\\(r=\\dfrac{l}{2}=\\dfrac{" + l + "}{2}=\\mathbf{" + r + "}\\)");
	}
}
