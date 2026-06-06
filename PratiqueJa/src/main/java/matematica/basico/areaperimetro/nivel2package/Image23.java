package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigQuadradoCircunferencia;

public class Image23 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 2 + rand.nextInt(18);

		String perimetro = "" + (4 * a) + "\\sqrt{2}";

		String resultadoCorreto = "" + a;

		String resolucao = ResolucaoAreaPerimetro.formulaPerimetroQuadrado() + "\\\\";
		resolucao += "4 \\cdot l=" + perimetro + "\\\\";
		resolucao += "l=" + "\\dfrac{" + perimetro + "}{4}=" + a + " \\sqrt{2} \\\\";
		resolucao += ResolucaoAreaPerimetro.formulaDiagonalQuadrado() + ", \\quad ";
		resolucao += "d=2r\\\\";
		resolucao += "2r=l\\sqrt{2}\\\\";
		resolucao += "2r=" + a + "\\sqrt{2} \\cdot \\sqrt{2}\\\\";
		resolucao += "2r=" + a + "(\\sqrt{2})^2 = " + a + "\\cdot 2 =" + (a * 2) + "\\\\";
		resolucao += "r=\\dfrac{" + (a * 2) + "}{2} = " + a + "\\\\";

		ConfigQuadradoCircunferencia config = new ConfigQuadradoCircunferencia("r", "l", false);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Se o perímetro do quadrado é \\(" + perimetro + "\\), qual o valor de \\(r\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
