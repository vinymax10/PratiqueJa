package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigQuadrado;

public class Image21 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int l = 3 + rand.nextInt(20);

		String perimetro = "" + (4 * l);
		String resultadoCorreto = "" + l;

		String resolucao = ResolucaoAreaPerimetro.formulaPerimetroQuadrado() + "\\\\";
		resolucao += "4 \\cdot l=" + perimetro + "\\\\";
		resolucao += "l=\\dfrac{" + perimetro + "}{4}=" + l + "\\\\";

		ConfigQuadrado config = new ConfigQuadrado("l", "", false);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se o perímetro do quadrado é \\(" + perimetro + "\\), qual o valor de \\(l\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
