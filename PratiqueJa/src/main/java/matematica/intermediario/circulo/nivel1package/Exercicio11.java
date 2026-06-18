package matematica.intermediario.circulo.nivel1package;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;
import matematica.intermediario.circulo.ResolucaoCirculo;
import matematica.intermediario.circulo.config.ConfigCoroa;

// Dados R e r, encontrar a área da coroa A = π(R²-r²)
public class Exercicio11 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// (bigR, smallR, k=bigR²-smallR²)
		int[][] pairs = {{5, 3, 16}, {5, 4, 9}, {6, 4, 20}, {6, 2, 32}, {7, 5, 24}, {4, 2, 12}, {3, 1, 8}};
		int[] pair = pairs[rand.nextInt(pairs.length)];
		int bigR = pair[0];
		int smallR = pair[1];
		int k = pair[2];

		String res = "\\(" + ResolucaoCirculo.formulaAreaCoroa() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(A = \\pi \\left(" + bigR + "^2 - " + smallR + "^2\\right) \\\\ ";
		res += "A = \\pi \\left(" + (bigR * bigR) + " - " + (smallR * smallR) + "\\right) \\\\ ";
		res += "A = \\mathbf{" + k + "}\\pi\\)";

		ConfigCoroa config = new ConfigCoroa("" + bigR, "" + smallR);
		BufferedImage image = config.criarImagem();

		addParagrafo("Qual a área da coroa circular de raio externo \\(R=" + bigR + "\\) e raio interno \\(r=" + smallR + "\\)?");
		addParagrafoImagem(image);
		gerarAlternativasPi(k);
		setResolucao(res);
	}

	private void gerarAlternativasPi(int k)
	{
		List<String> distr = new ArrayList<>();
		List<Integer> usados = new ArrayList<>();
		usados.add(k);
		int tentativas = 0;
		while(distr.size() < 3 && tentativas++ < 50)
		{
			int delta = 1 + rand.nextInt(8);
			int cand = rand.nextBoolean() ? k + delta : k - delta;
			if(cand > 0 && !usados.contains(cand))
			{
				usados.add(cand);
				distr.add("\\(" + cand + "\\pi\\)");
			}
		}
		embaralharEAdicionarAlternativas("\\(" + k + "\\pi\\)", distr);
	}
}
