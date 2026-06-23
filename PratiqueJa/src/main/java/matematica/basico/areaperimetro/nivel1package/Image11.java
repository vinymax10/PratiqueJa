package matematica.basico.areaperimetro.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigParalelogramo;

//	perímetro do paralelogramo
public class Image11 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 2 * (3 + rand.nextInt(12));
		int a = (int) (((double) b) * 0.75);

		String resultadoCorreto = "" + (2 * (b + a));
		String[] passosResolucao = ResolucaoAreaPerimetro.formulaPerimetroParalelogramo(a, b);

		ConfigParalelogramo config = new ConfigParalelogramo("" + b, "h", "" + a, false);
		BufferedImage image = config.criarImagem();

		addParagrafo("Qual o perímetro do paralelogramo?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		for(String passoResolucao : passosResolucao)
			addResolucao(passoResolucao);
	}
}
