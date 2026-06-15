package matematica.basico.areaperimetro.nivel1package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigQuadrado;

//	Perímetro Quadrado
public class Image9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int l = 3 + rand.nextInt(20);

		String resultadoCorreto = "" + (4 * l);
		String resolucao = ResolucaoAreaPerimetro.perimetroQuadrado(l);

		ConfigQuadrado config = new ConfigQuadrado("" + l, "", false);
		BufferedImage image = config.criarImagem();

		addParagrafo("Qual o perímetro do quadrado?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		setResolucao("\\(" + resolucao + "\\)");
	}
}
