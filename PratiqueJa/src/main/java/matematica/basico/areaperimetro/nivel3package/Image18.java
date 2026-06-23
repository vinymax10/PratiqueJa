package matematica.basico.areaperimetro.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.ConfigImagem;
import matematica.basico.areaperimetro.config.ConfigTrapezio2;
import matematica.expressao.MyExpression;

public class Image18 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int h = 2 * (2 + rand.nextInt(8));
		int b = (int) (((double) h) * 1.2);
		int B = (int) (((double) h) * 1.8);

		String area = "" + (B + b) * h / 2;

		String resultadoCorreto = "" + b;

		MyExpression expressao = new MyExpression("(" + B + "+ b" + ") * " + h + "=" + area + "*2");

		ConfigImagem config = new ConfigTrapezio2(B + "", "b", h + "", "", true);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se a área do trapézio é \\(" + area + "\\), qual o valor de \\(b\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + ResolucaoAreaPerimetro.formulaAreaTrapezio() + "\\)");
		addResolucao("\\(B=" + B + ",\\quad h=" + h + "\\)");
		addResolucao("\\(\\dfrac{(" + B + "+ b" + ")\\cdot " + h + "}{2} = " + area + "\\)");
		addResolucao("\\(" + expressao.resolverLatex() + "\\)");
	}
}
