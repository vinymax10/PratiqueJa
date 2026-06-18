package matematica.basico.areaperimetro.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigLozango;
import matematica.expressao.MyExpression;

public class Image12 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int D = 2 * (3 + rand.nextInt(13));
		int d = (int) (((double) D) * 0.7);

		String area = "" + D / 2 * d;

		String resultadoCorreto = D + "";

		String resolucao = ResolucaoAreaPerimetro.formulaLosango() + "\\\\";
		resolucao += "d=" + d + "\\\\";
		resolucao += "\\dfrac{D \\cdot " + d + "}{2} = " + area + "\\\\";
		MyExpression expressao = new MyExpression("D * " + d + "=" + area + "*2");
		resolucao += expressao.resolverLatex();

		ConfigLozango config = new ConfigLozango("D", d + "", "", "", "", true);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se a área do losango é \\(" + area + "\\), qual o valor de \\(D\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
