package matematica.basico.areaperimetro.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigQuadrado;

public class Image2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(10);

		String area = "" + 2 * a * a;
		String resultadoCorreto = "" + a * 2;

		String resolucao = ResolucaoAreaPerimetro.formulaAreaQuadrado() + "\\\\";
		resolucao += "l^2=" + area + "\\\\";
		resolucao += "l=" + "\\sqrt{" + area + "} = \\sqrt{" + a + "^2 \\cdot 2} = " + a + "\\sqrt{2}\\\\";
		resolucao += ResolucaoAreaPerimetro.formulaDiagonalQuadrado() + "\\\\";
		resolucao += "d=" + a + "\\sqrt{2} \\cdot \\sqrt{2}\\\\";
		resolucao += "d=" + a + "(\\sqrt{2})^2 = " + a + "\\cdot 2 =" + (a * 2) + "\\\\";

		ConfigQuadrado config = new ConfigQuadrado("l", "d", true);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Se a área do quadrado é \\(" + area + "\\), qual o valor de \\(d\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
