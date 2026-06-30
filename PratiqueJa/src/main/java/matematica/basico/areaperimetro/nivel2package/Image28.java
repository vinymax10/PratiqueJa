package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigRetangulo2;

public class Image28 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int h = 2 * (2 + rand.nextInt(10));
		int b = (int) (((double) h) * 1.35);
		int raio = h / 2;

		String perimetro = "" + b * raio * 2;

		String resultadoCorreto = "" + raio;

		ConfigRetangulo2 config = new ConfigRetangulo2("" + b, "h", "r", false);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se o perímetro do retângulo é \\(" + perimetro + "\\), qual o valor de \\(r\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + ResolucaoAreaPerimetro.formulaPerimetroRetangulo() + "\\)");
		addResolucao("\\(b=" + b + "\\)");
		addResolucao("\\(2 \\cdot(" + b + " + h)=" + perimetro + "\\)");
		addResolucao("\\(" + b + " + h=\\dfrac{" + perimetro + "}{2}=" + (b + h) + "\\)");
		addResolucao("\\(h=" + (b + h) + "-" + b + "=" + h + "\\)");
		addResolucao("\\(2r=" + h + "\\)");
		addResolucao("\\(r=\\dfrac{" + h + "}{" + 2 + "}=\\mathbf{" + raio + "}\\)");
	}
}
