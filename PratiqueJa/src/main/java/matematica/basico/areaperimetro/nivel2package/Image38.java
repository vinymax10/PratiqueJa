package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.ConfigImagem;
import matematica.basico.areaperimetro.config.ConfigTriangulo;

public class Image38 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 2 * (3 + rand.nextInt(13));
		int a = (int) (((double) b) * 0.6);
		int c = (int) (((double) b) * 0.85);

		String perimetro = "" + (b + a + c);

		String resultadoCorreto = "" + c;

		String resolucao = ResolucaoAreaPerimetro.formulaPerimetroTriangulo() + "\\\\";
		resolucao += "a=" + a + ",\\quad b=" + b + "\\\\";
		resolucao += a + "+" + b + "+c=" + perimetro + "\\\\";
		resolucao += "c=" + perimetro + "-" + a + "-" + b + "=" + c;

		ConfigImagem config = new ConfigTriangulo("" + b, "", "" + a, "c", false);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se o perímetro do triângulo é \\(" + perimetro + "\\), qual o valor de \\(c\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
