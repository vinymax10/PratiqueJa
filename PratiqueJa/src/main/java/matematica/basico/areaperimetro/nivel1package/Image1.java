package matematica.basico.areaperimetro.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigQuadrado;

//	Quadrado
public class Image1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int l = 3 + rand.nextInt(20);

		String resultadoCorreto = "" + (l * l);
		String resolucao = ResolucaoAreaPerimetro.areaQuadrado(l);

		ConfigQuadrado config = new ConfigQuadrado("" + l, "", true);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Qual a área do quadrado?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
