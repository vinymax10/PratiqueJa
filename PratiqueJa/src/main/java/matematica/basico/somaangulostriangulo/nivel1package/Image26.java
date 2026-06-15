package matematica.basico.somaangulostriangulo.nivel1package;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import matematica.GeradorExercicio;

public class Image26 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 20 + rand.nextInt(60); // 20..79
		int b = 20 + rand.nextInt(60); // 20..79
		int ext = a + b;               // 40..158

		addParagrafo("Um ângulo externo de um triângulo mede \\(" + ext + "^\\circ\\). Um dos ângulos internos não adjacentes mede \\(" + a + "^\\circ\\). Qual é o outro ângulo interno não adjacente?");

		Set<String> usados = new HashSet<>();
		usados.add("\\(" + b + "^\\circ\\)");
		List<String> distratores = new ArrayList<>();

		int errado = 180 - ext;
		String alt = "\\(" + errado + "^\\circ\\)";
		if (errado > 0 && errado != b && usados.add(alt)) distratores.add(alt);

		int[] deltas = { 10, -10, 5, -5, 15, -15, 20, -20 };
		for (int d : deltas)
		{
			if (distratores.size() >= 3) break;
			int cand = b + d;
			if (cand > 0 && cand < 180)
			{
				String s = "\\(" + cand + "^\\circ\\)";
				if (usados.add(s)) distratores.add(s);
			}
		}

		embaralharEAdicionarAlternativas("\\(" + b + "^\\circ\\)", distratores);

		String res = "O ângulo externo é igual à soma dos dois ângulos internos não adjacentes:";
		res += "\\(\\\\\\)";
		res += "\\(\\text{ext} = \\alpha + \\beta \\\\";
		res += ext + "^\\circ = " + a + "^\\circ + \\beta \\\\";
		res += "\\beta = " + ext + "^\\circ - " + a + "^\\circ = \\mathbf{" + b + "^\\circ}\\)";
		setResolucao(res);
	}
}
