package matematica.basico.somaangulostriangulo.nivel1package;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import matematica.GeradorExercicio;

public class Image25 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b;
		do
		{
			a = 20 + rand.nextInt(60);
			b = 20 + rand.nextInt(60);
		}
		while (a + b >= 160);

		int ext = a + b;

		addParagrafo("Dois ângulos internos de um triângulo medem \\(" + a + "^\\circ\\) e \\(" + b + "^\\circ\\). Qual é a medida do ângulo externo formado no terceiro vértice?");

		Set<String> usados = new HashSet<>();
		usados.add("\\(" + ext + "^\\circ\\)");
		List<String> distratores = new ArrayList<>();

		int interior = 180 - a - b;
		String alt = "\\(" + interior + "^\\circ\\)";
		if (interior != ext && interior > 0 && usados.add(alt)) distratores.add(alt);

		int supl = 180 - ext;
		String alt2 = "\\(" + supl + "^\\circ\\)";
		if (supl > 0 && supl != ext && usados.add(alt2)) distratores.add(alt2);

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

		String res = "O ângulo externo de um triângulo é igual à soma dos dois ângulos internos não adjacentes:";
		res += "\\(\\\\\\)";
		res += "\\(\\text{ext} = \\alpha + \\beta \\\\";
		res += "= " + a + "^\\circ + " + b + "^\\circ = \\mathbf{" + ext + "^\\circ}\\)";
		setResolucao(res);
	}
}
