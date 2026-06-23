package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigQuadradoCircunferencia;

//Quadrado inscrito na circunferência
public class Image2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int raio = 1 + rand.nextInt(10);

		String strRaio = raio + "\\sqrt{2}";

		String resultadoCorreto = "" + (2 * raio) * (2 * raio);

		int l = (2 * raio);

		ConfigQuadradoCircunferencia config = new ConfigQuadradoCircunferencia("r", "l", true);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se \\(r=" + strRaio + "\\), qual a área do quadrado?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(\\text{Seja~} d \\text{~a diagonal do quadrado}\\)");
		addResolucao("\\(d=2r = 2 \\cdot" + raio + "\\sqrt{2} = " + (2 * raio) + "\\sqrt{2}\\)");
		addResolucao("\\(" + ResolucaoAreaPerimetro.formulaDiagonalQuadrado() + ",\\quad" + ResolucaoAreaPerimetro.formulaAreaQuadrado() + "\\)");
		addResolucao("\\(l=" + (2 * raio) + "\\)");
		addResolucao("\\(A=" + l + "^2=" + l + " \\cdot " + l + "=" + (l * l) + "\\)");
	}
}
