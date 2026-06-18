package matematica.intermediario.semelhancaangulos.nivel1package;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import matematica.GeradorExercicio;

public class Exercicio15 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int alpha = 10 + rand.nextInt(160); // 10..169
		int beta = 180 - alpha;

		addParagrafo("Dois ângulos são suplementares. Um deles mede \\(" + alpha + "^\\circ\\). Qual é a medida do outro ângulo?");

		Set<String> usados = new HashSet<>();
		usados.add("\\(" + beta + "^\\circ\\)");
		List<String> distratores = new ArrayList<>();

		// Erro comum: confundir com complementar
		int complem = Math.abs(90 - alpha);
		String comp = "\\(" + complem + "^\\circ\\)";
		if(complem != beta && usados.add(comp)) distratores.add(comp);

		int[] deltas = {10, -10, 15, -15, 5, -5, 20, -20};
		for(int d : deltas)
		{
			if(distratores.size() >= 3) break;
			int cand = beta + d;
			if(cand > 0 && cand < 180)
			{
				String alt = "\\(" + cand + "^\\circ\\)";
				if(usados.add(alt)) distratores.add(alt);
			}
		}

		embaralharEAdicionarAlternativas("\\(" + beta + "^\\circ\\)", distratores);

		String res = "Ângulos suplementares têm soma igual a \\(180^\\circ\\):";
		res += "\\(\\\\\\)";
		res += "\\(\\alpha + \\beta = 180^\\circ \\\\";
		res += alpha + "^\\circ + \\beta = 180^\\circ \\\\";
		res += "\\beta = 180^\\circ - " + alpha + "^\\circ = \\mathbf{" + beta + "^\\circ}\\)";
		setResolucao(res);
	}
}
