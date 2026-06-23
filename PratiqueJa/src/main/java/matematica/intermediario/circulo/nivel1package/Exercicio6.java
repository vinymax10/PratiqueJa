package matematica.intermediario.circulo.nivel1package;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;
import matematica.intermediario.circulo.ResolucaoCirculo;
import matematica.intermediario.circulo.config.ConfigCirculoDiametro;

// Dado o diâmetro d, encontrar o comprimento C = πd
public class Exercicio6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int d = 2 + rand.nextInt(17); // d = 2..18
		int k = d;                    // C = kπ = dπ

		ConfigCirculoDiametro config = new ConfigCirculoDiametro("" + d, false);
		BufferedImage image = config.criarImagem();

		addParagrafo("Qual o comprimento da circunferência de diâmetro \\(" + d + "\\)?");
		addParagrafoImagem(image);
		gerarAlternativasPi(k);
		addResolucao("\\(" + ResolucaoCirculo.formulaComprimentoDiametro() + "\\)");
		addResolucao("\\(C = \\pi \\cdot " + d + " = \\mathbf{" + k + "}\\pi\\)");
	}

	private void gerarAlternativasPi(int k)
	{
		List<String> distr = new ArrayList<>();
		List<Integer> usados = new ArrayList<>();
		usados.add(k);
		int tentativas = 0;
		while(distr.size() < 3 && tentativas++ < 50)
		{
			int delta = 1 + rand.nextInt(5);
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
