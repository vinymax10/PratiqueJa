package matematica.basico.regratres.nivel3package;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import matematica.GeradorExercicio;

// Dois aumentos percentuais sucessivos
public class RegraTres13 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int[] opcoes = {10, 20, 30, 40, 50};
		int p1 = opcoes[rand.nextInt(opcoes.length)];
		int p2 = opcoes[rand.nextInt(opcoes.length)];

		int produto = p1 * p2 / 100;
		int total = p1 + p2 + produto;

		addParagrafo("Um produto sofreu dois aumentos sucessivos de \\(" + p1 + "\\%\\) e \\(" + p2 + "\\%\\). Qual o percentual de aumento total equivalente?");

		List<String> distratores = new ArrayList<>();
		Set<Integer> usados = new HashSet<>();
		usados.add(total);
		if(usados.add(p1 + p2))
			distratores.add("\\(" + (p1 + p2) + "\\%\\)");
		int[] tentativas = {5, -5, 10, -10, 15, -15, 20};
		for(int delta : tentativas)
		{
			if(distratores.size() >= 3) break;
			int alt = total + delta;
			if(alt > 0 && usados.add(alt))
				distratores.add("\\(" + alt + "\\%\\)");
		}
		embaralharEAdicionarAlternativas("\\(" + total + "\\%\\)", distratores);

		addResolucao("Aumento total \\(= p_1 + p_2 + \\dfrac{p_1 \\cdot p_2}{100}\\).");
		addResolucao("\\(" + p1 + " + " + p2 + " + \\dfrac{" + p1 + " \\cdot " + p2 + "}{100} =\\)");
		addResolucao("\\(" + (p1 + p2) + " + " + produto + " = \\mathbf{" + total + "\\%}\\)");
	}
}
