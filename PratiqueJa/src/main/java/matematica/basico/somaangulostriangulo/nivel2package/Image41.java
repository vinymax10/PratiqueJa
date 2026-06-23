package matematica.basico.somaangulostriangulo.nivel2package;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import matematica.GeradorExercicio;

public class Image41 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 10 + 2 * rand.nextInt(35); // ângulo do vértice, par: 10..78
		int a = (180 - b) / 2;             // ângulo da base: inteiro, 51..85
		int ext = a + b;                   // ângulo externo na base = (180+b)/2

		addParagrafo("Num triângulo isósceles, o ângulo do vértice mede \\(" + b + "^\\circ\\). Qual é o ângulo externo formado em um dos vértices da base?");

		Set<String> usados = new HashSet<>();
		usados.add("\\(" + ext + "^\\circ\\)");
		List<String> distratores = new ArrayList<>();

		String alt1 = "\\(" + a + "^\\circ\\)";
		if (a != ext && usados.add(alt1)) distratores.add(alt1);

		String alt2 = "\\(" + (180 - b) + "^\\circ\\)";
		if ((180 - b) != ext && (180 - b) != a && usados.add(alt2)) distratores.add(alt2);

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

		addResolucao("Calculamos o ângulo da base do triângulo isósceles:");
		addResolucao("\\(\\alpha = \\dfrac{180^\\circ - " + b + "^\\circ}{2} = \\mathbf{" + a + "^\\circ}\\)");
		addResolucao("O ângulo externo na base é igual à soma dos ângulos internos não adjacentes (ângulo do vértice e da outra base):");
		addResolucao("\\(\\text{ext} = " + b + "^\\circ + " + a + "^\\circ = \\mathbf{" + ext + "^\\circ}\\)");
	}
}
