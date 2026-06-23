package matematica.basico.regratres.nivel3package;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import matematica.GeradorExercicio;

// Aumento seguido de desconto (variação percentual líquida, pode ser negativa)
public class RegraTres15 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int[] opcoes = {10, 20, 30, 40, 50};
		int p1 = opcoes[rand.nextInt(opcoes.length)];
		int p2 = opcoes[rand.nextInt(opcoes.length)];

		int produto = p1 * p2 / 100;
		int liquido = p1 - p2 - produto; // variação percentual total

		addParagrafo("Um produto teve um aumento de \\(" + p1 + "\\%\\) e, em seguida, um desconto de \\(" + p2 + "\\%\\). Qual a variação percentual total (negativa indica redução)?");

		List<String> distratores = new ArrayList<>();
		Set<Integer> usados = new HashSet<>();
		usados.add(liquido);
		int[] candidatos = {p1 - p2, p2 - p1, p1 + p2, -(p1 + p2), liquido + 5, liquido - 5, liquido + 10, liquido - 10};
		for(int c : candidatos)
		{
			if(distratores.size() >= 3) break;
			if(usados.add(c))
				distratores.add("\\(" + c + "\\%\\)");
		}
		embaralharEAdicionarAlternativas("\\(" + liquido + "\\%\\)", distratores);

		addResolucao("A variação total é \\(p_1 - p_2 - \\dfrac{p_1 \\cdot p_2}{100}\\) (o produto representa o efeito combinado).");
		addResolucao("\\(" + p1 + " - " + p2 + " - \\dfrac{" + p1 + " \\cdot " + p2 + "}{100} =\\)");
		addResolucao("\\(" + (p1 - p2) + " - " + produto + " = \\mathbf{" + liquido + "\\%}\\)");
	}
}
