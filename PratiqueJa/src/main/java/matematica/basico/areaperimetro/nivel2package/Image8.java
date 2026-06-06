package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.Config;
import matematica.basico.areaperimetro.config.ConfigTrianguloEquilatero;

//	triangulo equilatero
public class Image8 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int l = 2 * (2 + rand.nextInt(10));

		String resultadoCorreto = "" + (l * l) / 4;

		String resolucao = "";
		resolucao += ResolucaoAreaPerimetro.areaTrianguloEquilatero(l);

		Config config = new ConfigTrianguloEquilatero("" + l, true);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Se a área do triângulo é \\(A=x\\sqrt{3}\\), qual o valor de \\(x\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
