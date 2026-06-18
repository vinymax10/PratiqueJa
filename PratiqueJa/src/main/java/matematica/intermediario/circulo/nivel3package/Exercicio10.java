package matematica.intermediario.circulo.nivel3package;

import java.awt.image.BufferedImage;

import matematica.GeradorExercicio;
import matematica.intermediario.circulo.ResolucaoCirculo;
import matematica.intermediario.circulo.config.ConfigSetor;

// Dado ℓ = kπ e A_s = mπ do mesmo setor, encontrar r = 2m/k (usando A_s/ℓ = r/2)
public class Exercicio10 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// (k, m, r): r = 2m/k; all from same sector so ℓ=kπ, A_s=mπ, r=2m/k
		int[][] triples = {{1, 1, 2}, {1, 2, 4}, {1, 3, 6}, {2, 2, 2}, {2, 3, 3}, {2, 4, 4}, {2, 6, 6}, {3, 3, 2}, {3, 6, 4}, {4, 4, 2}};
		int[] triple = triples[rand.nextInt(triples.length)];
		int k = triple[0];
		int m = triple[1];
		int r = triple[2];

		String res = "\\(" + ResolucaoCirculo.formulaArco() + "\\), ";
		res += "\\(" + ResolucaoCirculo.formulaAreaSetor() + "\\)";
		res += "\\(\\\\\\)";
		res += "Dividindo \\(A_s\\) por \\(\\ell\\): \\(\\\\\\)";
		res += "\\(\\dfrac{A_s}{\\ell} = \\dfrac{\\dfrac{\\theta}{360} \\cdot \\pi r^2}{\\dfrac{\\theta}{360} \\cdot 2\\pi r} = \\dfrac{r}{2}\\)";
		res += "\\(\\\\\\)";
		res += "\\(\\dfrac{A_s}{\\ell} = \\dfrac{" + m + "\\pi}{" + k + "\\pi} = \\dfrac{" + m + "}{" + k + "} = \\dfrac{r}{2} \\\\ ";
		res += "r = \\dfrac{2 \\cdot " + m + "}{" + k + "} = \\mathbf{" + r + "}\\)";

		ConfigSetor config = new ConfigSetor("r", 90);
		BufferedImage image = config.criarImagem();

		addParagrafo("Um setor circular tem comprimento de arco \\(" + k + "\\pi\\) e área \\(" + m + "\\pi\\). Qual é o raio?");
		addParagrafoImagem(image);
		gerarAlternativas("" + r);
		setResolucao(res);
	}
}
