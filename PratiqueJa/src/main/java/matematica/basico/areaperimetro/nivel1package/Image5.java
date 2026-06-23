package matematica.basico.areaperimetro.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.ConfigImagem;
import matematica.basico.areaperimetro.config.ConfigTrapezio;

//	trapézio
public class Image5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int h = 2 * (2 + rand.nextInt(8));
		int b = (int) (((double) h) * 1.2);
		int B = (int) (((double) h) * 2.1);

		String resultadoCorreto = "" + (((B + b) * h) / 2);
		String[] passosResolucao = ResolucaoAreaPerimetro.areaTrapezio(h, B, b);

		ConfigImagem config = new ConfigTrapezio(B + "", b + "", h + "", "", "", true);
		BufferedImage image = config.criarImagem();

		addParagrafo("Qual a área do trapézio?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		for(String passoResolucao : passosResolucao)
			addResolucao(passoResolucao);
	}
}
