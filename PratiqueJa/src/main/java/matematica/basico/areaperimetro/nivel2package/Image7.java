package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.Config;
import matematica.basico.areaperimetro.config.ConfigTrianguloIsosceles;

//	triangulo isosceles
public class Image7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 2 * (5 + rand.nextInt(16));
		int h = (int) (((double) b) * 0.6);

		int meiaBase = b / 2;

		String strAltura = "" + h;

		String resultadoCorreto = "" + (b * h) / 2;

		String resolucao = "";
		resolucao += ResolucaoAreaPerimetro.formulaAreaTriangulo() + "\\\\";
		resolucao += "h=" + h + ", \\quad b=" + meiaBase + " \\cdot 2=" + b + "\\\\";
		resolucao += "A=\\dfrac{" + b + " \\cdot " + h + "}{2}=";
		resolucao += "\\dfrac{" + b * h + "}{2}=";
		resolucao += (b * h) / 2;

		Config config = new ConfigTrianguloIsosceles("", strAltura, "" + meiaBase, "", true);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Qual a área do triângulo?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
