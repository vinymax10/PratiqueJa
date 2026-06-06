package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigQuadrado3;

//Quadrado com circunferencia dentro
public class Image12 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int raio = 1 + rand.nextInt(10);
		String strRaio = "" + raio;

		int l = 2 * raio;

		String resultadoCorreto = "" + (4 * l);
		String resolucao = "l=2r= 2 \\cdot" + raio + "=" + l + "\\\\";
		resolucao += ResolucaoAreaPerimetro.formulaPerimetroQuadrado() + "\\\\";
		resolucao += "P=4 \\cdot " + l + "=" + (4 * l);

		ConfigQuadrado3 config = new ConfigQuadrado3("l", "r", false);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Se \\(r=" + strRaio + "\\), qual o perímetro do quadrado?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
