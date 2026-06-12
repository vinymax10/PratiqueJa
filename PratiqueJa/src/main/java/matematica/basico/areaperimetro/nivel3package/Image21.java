package matematica.basico.areaperimetro.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.ResolucaoAreaPerimetro;
import matematica.basico.areaperimetro.config.ConfigLozango;

// Dado P e d (diagonal menor), encontrar D (diagonal maior)
public class Image21 extends GeradorExercicio
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

		String res = "\\(" + ResolucaoAreaPerimetro.formulaPerimetroQuadrado() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(4 \\cdot l=" + perimetro + " \\\\";
		res += "l=\\dfrac{" + perimetro + "}{4}=" + l + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(" + ResolucaoAreaPerimetro.formulaLadoLosango() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(" + l + "=\\sqrt{\\left(\\dfrac{D}{2}\\right)^2+" + halfd + "^2} \\\\";
		res += "\\left(\\dfrac{D}{2}\\right)^2=" + l + "^2-" + halfd + "^2=" + (l * l) + "-" + (halfd * halfd) + "=" + (halfD * halfD) + " \\\\";
		res += "\\dfrac{D}{2}=\\sqrt{" + (halfD * halfD) + "}=" + halfD + " \\Rightarrow D=\\mathbf{" + D + "}\\)";

		ConfigLozango config = new ConfigLozango("D", "" + d, "", "", "", false);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Se o perímetro do losango é \\(" + perimetro + "\\) e \\(d=" + d + "\\), qual o valor de \\(D\\)?");
		addParagrafoImagem(image);
		gerarAlternativas("" + D);
		setResolucao(res);
	}
}
