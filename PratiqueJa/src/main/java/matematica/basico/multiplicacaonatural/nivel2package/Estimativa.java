package matematica.basico.multiplicacaonatural.nivel2package;

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
		// garantir que nenhum fator seja múltiplo de 10 (senão a estimativa é trivial)
		int a, b;
		do { a = 15 + rand.nextInt(76); } while (a % 10 == 0);
		do { b = 15 + rand.nextInt(76); } while (b % 10 == 0);

		int ar = (int) Math.round(a / 10.0) * 10;
		int br = (int) Math.round(b / 10.0) * 10;
		int estimativa = ar * br;

		addParagrafo("Estime o produto de \\(" + a + " \\times " + b
			+ "\\), arredondando cada fator para a dezena mais próxima.");

		Set<Integer> usados = new HashSet<>();
		usados.add(estimativa);
		List<String> distrais = new ArrayList<>();

		int[] candidatos = {
			(ar + 10) * br,
			(ar > 10 ? ar - 10 : ar + 20) * br,
			ar * (br + 10),
			ar * (br > 10 ? br - 10 : br + 20),
			(ar + 10) * (br + 10)
		};
		for (int cand : candidatos)
		{
			if (distrais.size() >= 3) break;
			if (cand > 0 && usados.add(cand))
				distrais.add(formatarNumero(cand));
		}

		embaralharEAdicionarAlternativas(formatarNumero(estimativa), distrais);

		String res = "Arredondamos cada fator para a dezena mais próxima: \\(\\\\\\)";
		res += "\\(" + a + " \\approx " + ar + "\\) e \\(" + b + " \\approx " + br + "\\) \\(\\\\\\)";
		res += "Estimativa: \\(" + ar + " \\times " + br + " = \\mathbf{" + estimativa + "}\\)";
		setResolucao(res);
	}
}
