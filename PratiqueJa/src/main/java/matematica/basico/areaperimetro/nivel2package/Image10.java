package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigQuadradoCircunferencia;

//Quadrado inscrito na circunferência
public class Image10 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int raio = 1 + rand.nextInt(10);

		String strRaio = raio + "\\sqrt{2}";

		int l = (2 * raio);
		String resultadoCorreto = "" + (4 * l);

		ConfigQuadradoCircunferencia config = new ConfigQuadradoCircunferencia("r", "l", false);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se \\(r=" + strRaio + "\\), qual o perímetro do quadrado?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(\\text{Seja~} d \\text{~a diagonal do quadrado}\\)");
		addResolucao("\\(d=2r = 2 \\cdot" + raio + "\\sqrt{2} = " + (2 * raio) + "\\sqrt{2}\\)");
		addResolucao("\\(" + ResolucaoAreaPerimetro.formulaDiagonalQuadrado() + ",\\quad" + ResolucaoAreaPerimetro.formulaPerimetroQuadrado() + "\\)");
		addResolucao("\\(l=" + (2 * raio) + "\\)");
		addResolucao("\\(P=4 \\cdot " + l + "=\\mathbf{" + (4 * l) + "}\\)");
	}
}
