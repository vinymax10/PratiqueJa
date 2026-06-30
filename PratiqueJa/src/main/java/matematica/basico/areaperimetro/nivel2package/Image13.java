package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigRetangulo2;

//Retangulo com circunferencia dentro
public class Image13 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int h = 2 * (2 + rand.nextInt(9));
		int b = (int) (((double) h) * 1.35);
		int raio = h / 2;

		String resultadoCorreto = "" + (2 * (b + h));

		ConfigRetangulo2 config = new ConfigRetangulo2(b + "", "h", "r", false);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se \\(r=" + raio + "\\), qual o perímetro do retângulo?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(h=2r= 2 \\cdot" + raio + "=" + h + "\\)");
		addResolucao("\\(" + ResolucaoAreaPerimetro.formulaPerimetroRetangulo() + "\\)");
		addResolucao("\\(b=" + b + "\\)");
		addResolucao("\\(P=2 \\cdot (" + b + "+" + h + ")=2 \\cdot " + (b + h) + "=\\mathbf{" + (2 * (b + h)) + "}\\)");
	}
}
