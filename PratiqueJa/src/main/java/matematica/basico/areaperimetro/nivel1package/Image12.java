package matematica.basico.areaperimetro.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.ConfigImagem;
import matematica.basico.areaperimetro.config.ConfigTriangulo;

//	triângulo
public class Image12 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 2 * (3 + rand.nextInt(13));
		int a = (int) (((double) b) * 0.6);
		int c = (int) (((double) b) * 0.85);

		String resultadoCorreto = "" + (b + a + c);
		String[] passosResolucao = ResolucaoAreaPerimetro.perimetroTriangulo(a, b, c);

		ConfigImagem config = new ConfigTriangulo("" + b, "h", "" + a, "" + c, false);
		BufferedImage image = config.criarImagem();

		addParagrafo("Qual o perímetro do triângulo?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		for(String passoResolucao : passosResolucao)
			addResolucao(passoResolucao);
	}
}
