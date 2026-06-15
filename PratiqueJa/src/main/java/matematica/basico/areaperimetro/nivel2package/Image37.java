package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.ConfigImagem;
import matematica.basico.areaperimetro.config.ConfigTriangulo;

public class Image37 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 2 * (3 + rand.nextInt(13));
		int a = (int) (((double) b) * 0.6);
		int c = (int) (((double) b) * 0.85);

		String perimetro = "" + (b + a + c);

		String resultadoCorreto = "" + b;

		String resolucao = ResolucaoAreaPerimetro.formulaPerimetroTriangulo() + "\\\\";
		resolucao += "a=" + a + ",\\quad c=" + c + "\\\\";
		resolucao += a + "+b+" + c + "=" + perimetro + "\\\\";
		resolucao += "b=" + perimetro + "-" + a + "-" + c + "=" + b;

		ConfigImagem config = new ConfigTriangulo("b", "", "" + a, "" + c, false);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se o perímetro do triângulo é \\(" + perimetro + "\\), qual o valor de \\(b\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
