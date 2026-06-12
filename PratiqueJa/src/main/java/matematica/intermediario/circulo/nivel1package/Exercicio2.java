package matematica.intermediario.circulo.nivel1package;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.config.ConfigCircunferencia;
import matematica.intermediario.circulo.ResolucaoCirculo;

// Dado r, encontrar a área A = πr²
public class Exercicio2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int r = 2 + rand.nextInt(7); // r = 2..8
		int k = r * r;               // A = kπ

		String res = "\\(" + ResolucaoCirculo.formulaArea() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(A = \\pi \\cdot " + r + "^2 = \\mathbf{" + k + "}\\pi\\)";

		ConfigCircunferencia config = new ConfigCircunferencia("" + r, true);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Qual a área do círculo de raio \\(" + r + "\\)?");
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
