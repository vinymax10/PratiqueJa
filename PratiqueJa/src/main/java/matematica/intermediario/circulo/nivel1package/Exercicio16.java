package matematica.intermediario.circulo.nivel1package;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;
import matematica.intermediario.circulo.ResolucaoCirculo;
import matematica.intermediario.circulo.config.ConfigSetor;

// Dado r e θ, encontrar a área do setor As = (θ/360)·πr²
public class Exercicio16 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int theta, r, k;
		int caseN = rand.nextInt(3);

		if(caseN == 0)
		{
			// θ=90°, r=2m → As = m²π
			theta = 90;
			int m = 1 + rand.nextInt(4);
			r = 2 * m;
			k = m * m;
		}
		else if(caseN == 1)
		{
			// θ=120°, r=3m → As = 3m²π
			theta = 120;
			int m = 1 + rand.nextInt(3);
			r = 3 * m;
			k = 3 * m * m;
		}
		else
		{
			// θ=180°, r=2m → As = 2m²π
			theta = 180;
			int m = 1 + rand.nextInt(4);
			r = 2 * m;
			k = 2 * m * m;
		}

		String res = "\\(" + ResolucaoCirculo.formulaAreaSetor() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(A_s = \\dfrac{" + theta + "}{360} \\cdot \\pi \\cdot " + r + "^2 \\\\ ";
		res += "A_s = \\dfrac{" + theta + " \\cdot " + (r * r) + "}{360}\\pi \\\\ ";
		res += "A_s = \\dfrac{" + (theta * r * r) + "}{360}\\pi = \\mathbf{" + k + "}\\pi\\)";

		ConfigSetor config = new ConfigSetor("" + r, theta);
		BufferedImage image = config.criarImagem();

		addParagrafo("Qual a área do setor circular de raio \\(" + r + "\\) e ângulo central \\(" + theta + "^\\circ\\)?");
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
