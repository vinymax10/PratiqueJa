package matematica.basico.areaperimetro.nivel2package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigLozango;

// Perímetro do losango dado as duas diagonais (usa Pitágoras para encontrar l)
public class Image39 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Triplas múltiplas de (3,4,5): halfD=4k, halfd=3k, l=5k
		int k = 1 + rand.nextInt(5);
		int halfD = 4 * k;
		int halfd = 3 * k;
		int l = 5 * k;
		int D = 2 * halfD;
		int d = 2 * halfd;
		int perimetro = 4 * l;

		String res = "\\(" + ResolucaoAreaPerimetro.formulaLadoLosango() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(D=" + D + ",\\quad d=" + d + " \\\\";
		res += "l=\\sqrt{\\left(\\dfrac{" + D + "}{2}\\right)^2+\\left(\\dfrac{" + d + "}{2}\\right)^2} \\\\";
		res += "l=\\sqrt{" + halfD + "^2+" + halfd + "^2}=\\sqrt{" + (halfD * halfD) + "+" + (halfd * halfd) + "}=\\sqrt{" + (halfD * halfD + halfd * halfd) + "}=" + l + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(" + ResolucaoAreaPerimetro.formulaPerimetroQuadrado() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(P=4 \\cdot " + l + "=\\mathbf{" + perimetro + "}\\)";

		ConfigLozango config = new ConfigLozango("" + D, "" + d, "", "", "", false);
		BufferedImage image = config.criarImagem();

		addParagrafo("Qual o perímetro do losango?");
		addParagrafoImagem(image);
		gerarAlternativas("" + perimetro);
		setResolucao(res);
	}
}
