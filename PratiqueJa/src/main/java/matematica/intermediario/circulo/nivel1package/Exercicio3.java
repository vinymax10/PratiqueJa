package matematica.intermediario.circulo.nivel1package;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;
import matematica.intermediario.circulo.ResolucaoCirculo;
import matematica.intermediario.circulo.config.ConfigSetor;

// Dado r e θ, encontrar o comprimento do arco ℓ = (θ/360)·2πr
public class Exercicio3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int theta, r, k;
		int caseN = rand.nextInt(4);

		if(caseN == 0)
		{
			// θ=60°, r=3m → ℓ = mπ
			theta = 60;
			int m = 1 + rand.nextInt(4);
			r = 3 * m;
			k = m;
		}
		else if(caseN == 1)
		{
			// θ=90°, r=2m → ℓ = mπ
			theta = 90;
			int m = 1 + rand.nextInt(5);
			r = 2 * m;
			k = m;
		}
		else if(caseN == 2)
		{
			// θ=120°, r=3m → ℓ = 2mπ
			theta = 120;
			int m = 1 + rand.nextInt(3);
			r = 3 * m;
			k = 2 * m;
		}
		else
		{
			// θ=180°, r=1..8 → ℓ = rπ
			theta = 180;
			r = 1 + rand.nextInt(8);
			k = r;
		}

		String res = "\\(" + ResolucaoCirculo.formulaArco() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(\\ell = \\dfrac{" + theta + "}{360} \\cdot 2\\pi \\cdot " + r + " \\\\ ";
		res += "\\ell = \\dfrac{" + (theta * 2 * r) + "}{360}\\pi \\\\ ";
		res += "\\ell = \\mathbf{" + k + "}\\pi\\)";

		ConfigSetor config = new ConfigSetor("" + r, theta);
		BufferedImage image = config.criarImagem(1 + rand.nextInt(10));

		addParagrafo("Qual o comprimento do arco de círculo de raio \\(" + r + "\\) e ângulo central \\(" + theta + "^\\circ\\)?");
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
