package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigRetangulo2;

//Retangulo com circunferencia dentro
public class Image5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int h = 2 * (2 + rand.nextInt(9));
		int b = (int) (((double) h) * 1.35);
		int raio = h / 2;

		String resultadoCorreto = "" + (b * h);

		String resolucao = "h=2r= 2 \\cdot" + raio + "=" + h + "\\\\";
		resolucao += ResolucaoAreaPerimetro.formulaAreaRetangulo() + "\\\\";
		resolucao += "b=" + b + "\\\\";
		resolucao += "A=" + b + " \\cdot " + h + "=" + (b * h);

		ConfigRetangulo2 config = new ConfigRetangulo2(b + "", "h", "r", true);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se \\(r=" + raio + "\\), qual a área do retângulo?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
