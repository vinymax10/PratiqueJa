package matematica.basico.somaangulostriangulo.nivel2package;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import matematica.GeradorExercicio;

public class Image40 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 20 + rand.nextInt(50); // ângulo da base: 20..69
		int vertex = 180 - 2 * a;     // ângulo do vértice: 42..140
		int ext = 2 * a;              // ângulo externo no vértice = a + a

		addParagrafo("Num triângulo isósceles, o ângulo da base mede \\(" + a + "^\\circ\\). Qual é o ângulo externo formado no vértice oposto à base?");

		Set<String> usados = new HashSet<>();
		usados.add("\\(" + ext + "^\\circ\\)");
		List<String> distratores = new ArrayList<>();

		String alt1 = "\\(" + vertex + "^\\circ\\)";
		if (vertex != ext && usados.add(alt1)) distratores.add(alt1);

		String alt2 = "\\(" + a + "^\\circ\\)";
		if (a != ext && usados.add(alt2)) distratores.add(alt2);

		int[] deltas = { 10, -10, 5, -5, 15, -15, 20, -20 };
		for (int d : deltas)
		{
			if (distratores.size() >= 3) break;
			int cand = ext + d;
			if (cand > 0 && cand < 180)
			{
				String s = "\\(" + cand + "^\\circ\\)";
				if (usados.add(s)) distratores.add(s);
			}
		}

		embaralharEAdicionarAlternativas("\\(" + ext + "^\\circ\\)", distratores);

		addResolucao("O ângulo externo é igual à soma dos dois ângulos internos não adjacentes. No triângulo isósceles, os dois ângulos da base são iguais:");
		addResolucao("\\(\\text{ext} = \\alpha + \\alpha = 2 \\times " + a + "^\\circ = \\mathbf{" + ext + "^\\circ}\\)");
	}
}
