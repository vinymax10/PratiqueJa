package matematica.basico.areaperimetro.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigQuadrado;

public class Image1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int l = 3 + rand.nextInt(20);

		String area = "" + l * l;
		String resultadoCorreto = "" + l;

		String resolucao = ResolucaoAreaPerimetro.formulaAreaQuadrado() + "\\\\";
		resolucao += "l^2=" + area + "\\\\";
		resolucao += "l=" + "\\sqrt{" + area + "} = " + l + "\\\\";

		ConfigQuadrado config = new ConfigQuadrado("l", "", true);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se a área do quadrado é \\(" + area + "\\), qual o valor de \\(l\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
