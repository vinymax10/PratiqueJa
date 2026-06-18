package matematica.intermediario.semelhancaangulos.nivel1package;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import matematica.GeradorExercicio;

public class Exercicio14 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int alpha = 5 + rand.nextInt(80); // 5..84
		int beta = 90 - alpha;

		addParagrafo("Dois ângulos são complementares. Um deles mede \\(" + alpha + "^\\circ\\). Qual é a medida do outro ângulo?");

		Set<String> usados = new HashSet<>();
		usados.add("\\(" + beta + "^\\circ\\)");
		List<String> distratores = new ArrayList<>();

		// Erro comum: confundir com suplementar
		String supl = "\\(" + (180 - alpha) + "^\\circ\\)";
		if(usados.add(supl)) distratores.add(supl);

		int[] deltas = {10, -10, 5, -5, 15, -15, 20, -20};
		for(int d : deltas)
		{
			if(distratores.size() >= 3) break;
			int cand = beta + d;
			if(cand > 0 && cand < 90)
			{
				String alt = "\\(" + cand + "^\\circ\\)";
				if(usados.add(alt)) distratores.add(alt);
			}
		}

		embaralharEAdicionarAlternativas("\\(" + beta + "^\\circ\\)", distratores);

		String res = "Ângulos complementares têm soma igual a \\(90^\\circ\\):";
		res += "\\(\\\\\\)";
		res += "\\(\\alpha + \\beta = 90^\\circ \\\\";
		res += alpha + "^\\circ + \\beta = 90^\\circ \\\\";
		res += "\\beta = 90^\\circ - " + alpha + "^\\circ = \\mathbf{" + beta + "^\\circ}\\)";
		setResolucao(res);
	}
}
