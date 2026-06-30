package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigRetangulo;

public class Image29 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int h = 4 + rand.nextInt(16);

		String resultadoCorreto = "" + h;
		int perimetro = 2 * ((2 * h) + h);

		ConfigRetangulo config = new ConfigRetangulo("2h", "h", false);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se o perímetro do retângulo é \\(" + perimetro + "\\), qual o valor de \\(h\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + ResolucaoAreaPerimetro.formulaPerimetroRetangulo() + "\\)");
		addResolucao("\\(2 \\cdot (2h + h)=" + perimetro + "\\)");
		addResolucao("\\(2h + h=\\dfrac{" + perimetro + "}{2}=" + (perimetro / 2) + "\\)");
		addResolucao("\\(3h=" + (perimetro / 2) + "\\)");
		addResolucao("\\(h=\\dfrac{" + (perimetro / 2) + "}{3}=\\mathbf{" + h + "}\\)");
	}
}
