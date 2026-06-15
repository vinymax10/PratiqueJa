package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigLozango;

public class Image31 extends GeradorExercicio
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

		ConfigLozango config = new ConfigLozango("", "", "", "", "l", false);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se o perímetro do lozango é \\(" + perimetro + "\\), qual o valor de \\(l\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
