package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigQuadrado;

//Quadrado com lateralEsq
public class Image1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int l = 3 + rand.nextInt(20);

		String strDiagonal = "" + l + "\\sqrt{2}";

		String resultadoCorreto = "" + l * l;

		ConfigQuadrado config = new ConfigQuadrado("l", "d", true);
		BufferedImage image = config.criarImagem();

		addParagrafo("Se \\(d=" + strDiagonal + "\\), qual a área do quadrado?");
		addParagrafoImagem(image);
		gerarAlternativas(resultadoCorreto);
		addResolucao("\\(" + ResolucaoAreaPerimetro.formulaDiagonalQuadrado() + ", \\quad " + ResolucaoAreaPerimetro.formulaAreaQuadrado() + "\\)");
		addResolucao("\\(l=" + l + "\\)");
		addResolucao("\\(A=" + l + "^2=" + l + " \\cdot " + l + "=" + (l * l) + "\\)");
	}
}
