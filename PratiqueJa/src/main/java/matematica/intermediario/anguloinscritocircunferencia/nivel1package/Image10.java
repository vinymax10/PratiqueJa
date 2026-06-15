package matematica.intermediario.anguloinscritocircunferencia.nivel1package;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import matematica.GeradorExercicio;

public class Image10 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 10 + rand.nextInt(160); // arco dado: 10..169°
		int b = 180 - a;               // arco complementar: 11..170°

		addParagrafo("Um ponto \\(P\\) sobre uma circunferência forma dois arcos com as extremidades de um diâmetro. Se um dos arcos mede \\(" + a + "^\\circ\\), qual é a medida do outro arco?");

		Set<String> usados = new HashSet<>();
		usados.add("\\(" + b + "^\\circ\\)");
		List<String> distratores = new ArrayList<>();

		int errado1 = 360 - a;
		String alt1 = "\\(" + errado1 + "^\\circ\\)";
		if (errado1 > 0 && errado1 < 360 && errado1 != b && usados.add(alt1)) distratores.add(alt1);

		int errado2 = a / 2;
		String alt2 = "\\(" + errado2 + "^\\circ\\)";
		if (errado2 > 0 && errado2 != b && usados.add(alt2)) distratores.add(alt2);

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

		String res = "Os dois arcos formados pelo diâmetro e o ponto \\(P\\) somam \\(180^\\circ\\):";
		res += "\\(\\\\\\)";
		res += "\\(\\alpha + \\beta = 180^\\circ \\\\";
		res += a + "^\\circ + \\beta = 180^\\circ \\\\";
		res += "\\beta = 180^\\circ - " + a + "^\\circ = \\mathbf{" + b + "^\\circ}\\)";
		setResolucao(res);
	}
}
