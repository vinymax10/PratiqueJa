package matematica.basico.areaperimetro.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigCircunferencia;

//	Circunferência
public class Image19 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int r = (3 + rand.nextInt(20));

		String resultadoCorreto = "" + (2 * r);
		String[] passosResolucao = ResolucaoAreaPerimetro.comprimentoCircunferencia(r);

		ConfigCircunferencia config = new ConfigCircunferencia("" + r, false);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se o comprimento da circunferência é \\(C=x\\pi\\), qual o valor de \\(x\\)?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		for(String passoResolucao : passosResolucao)
			addResolucao(passoResolucao);
	}
}
