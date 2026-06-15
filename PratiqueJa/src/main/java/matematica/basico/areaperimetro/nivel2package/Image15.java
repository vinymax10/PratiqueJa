package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.ConfigImagem;
import matematica.basico.areaperimetro.config.ConfigTrianguloIsosceles;

//	triangulo isosceles
public class Image15 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 2 * (3 + rand.nextInt(13));
		int a = (int) (((double) b) * 0.7);// altura

		int meiaBase = b / 2;

		String resultadoCorreto = "" + ((2 * a) + b);

		String resolucao = "";
		resolucao += ResolucaoAreaPerimetro.formulaPerimetroTrianguloIsosceles() + "\\\\";
		resolucao += "a=" + a + ", \\quad b=" + meiaBase + " \\cdot 2=" + b + "\\\\";
		resolucao += "P=2 \\cdot " + a + "+" + b + "=" + (2 * a) + "+" + b + "=" + ((2 * a) + b);

		ConfigImagem config = new ConfigTrianguloIsosceles("", " ", "" + meiaBase, "" + a, false);
		BufferedImage image = config.criarImagem();

		addParagrafo("Qual o perímetro do triângulo?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
