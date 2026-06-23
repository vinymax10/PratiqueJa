package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigQuadrado3;

//Quadrado com circunferencia dentro
public class Image4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int raio = 1 + rand.nextInt(10);
		String strRaio = "" + raio;

		String resultadoCorreto = "" + (4 * raio * raio);
		int l = 2 * raio;
		ConfigQuadrado3 config = new ConfigQuadrado3("l", "r", true);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se \\(r=" + strRaio + "\\), qual a área do quadrado?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(l=2r= 2 \\cdot" + raio + "=" + l + "\\)");
		addResolucao("\\(" + ResolucaoAreaPerimetro.formulaAreaQuadrado() + "\\)");
		addResolucao("\\(A=" + l + "^2=" + l + " \\cdot " + l + "=" + (l * l) + "\\)");
	}
}
