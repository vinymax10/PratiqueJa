package matematica.intermediario.circulo.nivel1package;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.config.ConfigCircunferencia;
import matematica.intermediario.circulo.ResolucaoCirculo;

// Dado r, encontrar o comprimento C = 2πr
public class Exercicio1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int r = 2 + rand.nextInt(9); // r = 2..10
		int k = 2 * r;               // C = kπ

		ConfigCircunferencia config = new ConfigCircunferencia("" + r, false);
		BufferedImage image = config.criarImagem();

		addParagrafo("Qual o comprimento da circunferência de raio \\(" + r + "\\)?");
		addParagrafoImagem(image);
		gerarAlternativasPi(k);
		addResolucao("\\(" + ResolucaoCirculo.formulaComprimento() + "\\)");
		addResolucao("\\(C = 2\\pi \\cdot " + r + " = \\mathbf{" + k + "}\\pi\\)");
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
