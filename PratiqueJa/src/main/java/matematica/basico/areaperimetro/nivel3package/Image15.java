package matematica.basico.areaperimetro.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.Config;
import matematica.basico.areaperimetro.config.ConfigTrapezio;
import matematica.expressao.MyExpression;

public class Image15 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int h = 2 * (2 + rand.nextInt(8));
		int b = (int) (((double) h) * 1.2);
		int B = (int) (((double) h) * 2.1);

		String area = "" + (B + b) * h / 2;

		String resultadoCorreto = "" + b;

		String resolucao = ResolucaoAreaPerimetro.formulaAreaTrapezio() + "\\\\";
		resolucao += "B=" + B + ",\\quad h=" + h + "\\\\";
		resolucao += "\\dfrac{(" + B + "+ b" + ")\\cdot " + h + "}{2} = " + area + "\\\\";
		MyExpression expressao = new MyExpression("(" + B + "+ b" + ") * " + h + "=" + area + "*2");
		resolucao += expressao.resolverLatex();

		Config config = new ConfigTrapezio(B + "", "b", h + "", "", "", true);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Se a área do trapézio é \\(" + area + "\\), qual o valor de \\(b\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
