package matematica.basico.areaperimetro.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigCircunferencia;

//	Círculo
public class Image8 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int r = (3 + rand.nextInt(20));

		String resultadoCorreto = "" + (r * r);
		String resolucao = ResolucaoAreaPerimetro.areaCirculo(r);

		ConfigCircunferencia config = new ConfigCircunferencia("" + r, true);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Se a área do círculo é \\(A=x\\pi\\), qual o valor de \\(x\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
