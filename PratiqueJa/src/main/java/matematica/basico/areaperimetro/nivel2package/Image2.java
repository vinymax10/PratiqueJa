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

		String resolucao = "";
		resolucao += "\\text{Seja~} d \\text{~a diagonal do quadrado}\\\\";
		resolucao += "d=2r = 2 \\cdot" + raio + "\\sqrt{2} = " + (2 * raio) + "\\sqrt{2}\\\\";
		resolucao += ResolucaoAreaPerimetro.formulaDiagonalQuadrado() + ""
		+ ",\\quad" + ResolucaoAreaPerimetro.formulaAreaQuadrado() + "\\\\";
		resolucao += "l=" + (2 * raio) + "\\\\";
		resolucao += "A=" + l + "^2=" + l + " \\cdot " + l + "=" + (l * l);

		ConfigQuadradoCircunferencia config = new ConfigQuadradoCircunferencia("r", "l", true);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Se \\(r=" + strRaio + "\\), qual a área do quadrado?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
