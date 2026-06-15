package matematica.basico.multiplicacaonatural.nivel3package;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import matematica.GeradorExercicio;

public class Estimativa extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// fator de 3 dígitos não múltiplo de 100; fator de 2 dígitos não múltiplo de 10
		int a, b;
		do { a = 150 + rand.nextInt(751); } while (a % 100 == 0);
		do { b = 15 + rand.nextInt(76);  } while (b % 10 == 0);

		int ar = (int) Math.round(a / 100.0) * 100;
		int br = (int) Math.round(b / 10.0) * 10;
		int estimativa = ar * br;

		addParagrafo("Estime o produto de \\(" + a + " \\times " + b
			+ "\\), arredondando o primeiro fator para a centena mais próxima"
			+ " e o segundo para a dezena mais próxima.");

		Set<Integer> usados = new HashSet<>();
		usados.add(estimativa);
		List<String> distrais = new ArrayList<>();

		int[] candidatos = {
			(ar + 100) * br,
			(ar > 100 ? ar - 100 : ar + 200) * br,
			ar * (br + 10),
			ar * (br > 10 ? br - 10 : br + 20),
			(ar + 100) * (br + 10)
		};
		for (int cand : candidatos)
		{
			if (distrais.size() >= 3) break;
			if (cand > 0 && usados.add(cand))
				distrais.add(formatarNumero(cand));
		}

		embaralharEAdicionarAlternativas(formatarNumero(estimativa), distrais);

		String res = "Arredondamos os fatores: \\(\\\\\\)";
		res += "\\(" + a + " \\approx " + ar + "\\) (centena mais próxima)"
			+ " e \\(" + b + " \\approx " + br + "\\) (dezena mais próxima) \\(\\\\\\)";
		res += "Estimativa: \\(" + ar + " \\times " + br + " = \\mathbf{" + estimativa + "}\\)";
		setResolucao(res);
	}
}
