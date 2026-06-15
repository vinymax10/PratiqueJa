package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.ConfigImagem;
import matematica.basico.areaperimetro.config.ConfigTrianguloRetangulo;

public class Image20 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 2 * (5 + rand.nextInt(13));
		int a = (int) (((double) b) * 0.6);// altura
		int c = (int) (((double) b) * 1.2);// hipotenusa

		String perimetro = "" + (b + a + c);

		String resultadoCorreto = "" + a;

		String resolucao = ResolucaoAreaPerimetro.formulaPerimetroTriangulo() + "\\\\";
		resolucao += "b=" + b + ",\\quad c=" + c + "\\\\";
		resolucao += "a+" + b + "+" + c + "=" + perimetro + "\\\\";
		resolucao += "a=" + perimetro + "-" + b + "-" + c + "=" + a;

		ConfigImagem config = new ConfigTrianguloRetangulo("" + b, "a", "" + c, false);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se o perímetro do triângulo é \\(" + perimetro + "\\), qual o valor de \\(a\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
