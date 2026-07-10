package matematica.basico.areaperimetro.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.ConfigImagem;
import matematica.basico.areaperimetro.config.ConfigTriangulo;
import matematica.basico.areaperimetro.config.ConfigTrianguloIsosceles;
import matematica.basico.areaperimetro.config.ConfigTrianguloRetangulo;
import matematica.expressao.MyExpression;

public class Image19 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 2 * (3 + rand.nextInt(13));
		int h = (int) (((double) b) * 0.6);

		String area = "" + h * b / 2;
		String resultadoCorreto = "" + b;

		MyExpression expressao = new MyExpression("b * " + h + "=" + area + "*2");

		ConfigImagem config = null;

		int num = rand.nextInt(3);
		switch(num)
		{
			case 0: config = new ConfigTriangulo("b", h + "", "", "", true); break;
			case 1: config = new ConfigTrianguloRetangulo("b", h + "", "", true); break;
			case 2: config = new ConfigTrianguloIsosceles("b", h + "", "", "", true); break;
		}
		BufferedImage image = config.criarImagem();

		addParagrafo("Se a área do triângulo é \\(" + area + "\\), qual o valor de \\(b\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + ResolucaoAreaPerimetro.formulaAreaTriangulo() + "\\)");
		addResolucao("\\(h=" + h + "\\)");
		addResolucao("\\(\\dfrac{b \\cdot " + h + "}{2} = " + area + "\\)");
		addResolucao("\\(" + boldLastResult(expressao.resolverLatex()) + "\\)");
	}
}
