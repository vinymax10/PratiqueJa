package matematica.intermediario.circulo.nivel2package;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;
import matematica.basico.areaperimetro.config.ConfigCircunferencia;
import matematica.intermediario.circulo.ResolucaoCirculo;

// Dado C = kπ (k par), encontrar A = π(k/2)² — problema de dois passos: C → r → A
public class Exercicio5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int m = 2 + rand.nextInt(7); // r = m = 2..8
		int k = 2 * m;               // C = kπ (k par)
		int aCoef = m * m;           // A = m²π

		String res = "\\(" + ResolucaoCirculo.formulaComprimento() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(" + k + "\\pi = 2\\pi \\cdot r \\\\ ";
		res += "r = \\dfrac{" + k + "}{2} = " + m + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(" + ResolucaoCirculo.formulaArea() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(A = \\pi \\cdot " + m + "^2 = \\mathbf{" + aCoef + "}\\pi\\)";

		ConfigCircunferencia config = new ConfigCircunferencia("r", true);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("O comprimento de uma circunferência é \\(" + k + "\\pi\\). Qual é a área do círculo?");
		addParagrafoImagem(image);
		gerarAlternativasPi(aCoef);
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
