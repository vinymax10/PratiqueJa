package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigQuadrado;

//Quadrado com lateralEsq
public class Image9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int l = 3 + rand.nextInt(20);

		String strDiagonal = "" + l + "\\sqrt{2}";

		String resultadoCorreto = "" + (4 * l);

		ConfigQuadrado config = new ConfigQuadrado("l", "d", false);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se \\(d=" + strDiagonal + "\\), qual o perímetro do quadrado?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + ResolucaoAreaPerimetro.formulaDiagonalQuadrado() + ", \\quad " + ResolucaoAreaPerimetro.formulaPerimetroQuadrado() + "\\)");
		addResolucao("\\(l=" + l + "\\)");
		addResolucao("\\(P=4 \\cdot " + l + "=" + (4 * l) + "\\)");
	}
}
