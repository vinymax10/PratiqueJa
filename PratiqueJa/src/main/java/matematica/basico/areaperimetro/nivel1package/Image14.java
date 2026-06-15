package matematica.basico.areaperimetro.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.ConfigImagem;
import matematica.basico.areaperimetro.config.ConfigTrianguloIsosceles;

//	triângulo
public class Image14 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 2 * (3 + rand.nextInt(13));
		int a = (int) (((double) b) * 0.7);// altura
		int c = a;

		String resultadoCorreto = "" + (b + a + c);
		String resolucao = ResolucaoAreaPerimetro.perimetroTrianguloIsosceles(a, b);

		ConfigImagem config = new ConfigTrianguloIsosceles("" + b, "", "", "" + a, false);
		BufferedImage image = config.criarImagem();

		addParagrafo("Qual o perímetro do triângulo?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
