package matematica.intermediario.anguloinscritocircunferencia.nivel1package;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import matematica.GeradorExercicio;

public class Image9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 10 + rand.nextInt(70); // ângulo agudo dado: 10..79°
		int b = 90 - a;               // outro ângulo agudo: 11..80°

		addParagrafo("Um triângulo retângulo está inscrito numa semicircunferência, com a hipotenusa como diâmetro. Um dos ângulos agudos mede \\(" + a + "^\\circ\\). Qual é a medida do outro ângulo agudo?");

		Set<String> usados = new HashSet<>();
		usados.add("\\(" + b + "^\\circ\\)");
		List<String> distratores = new ArrayList<>();

		int errado1 = 180 - a;
		String alt1 = "\\(" + errado1 + "^\\circ\\)";
		if (errado1 != b && errado1 > 0 && usados.add(alt1)) distratores.add(alt1);

		int[] deltas = { 10, -10, 5, -5, 15, -15, 20, -20 };
		for (int d : deltas)
		{
			if (distratores.size() >= 3) break;
			int cand = b + d;
			if (cand > 0 && cand < 90)
			{
				String s = "\\(" + cand + "^\\circ\\)";
				if (usados.add(s)) distratores.add(s);
			}
		}

		embaralharEAdicionarAlternativas("\\(" + b + "^\\circ\\)", distratores);

		addResolucao("Pelo Teorema de Tales, o ângulo oposto ao diâmetro é \\(90^\\circ\\). Pela soma dos ângulos do triângulo:");
		addResolucao("\\(90^\\circ + " + a + "^\\circ + x = 180^\\circ\\)");
		addResolucao("\\(x = 180^\\circ - 90^\\circ - " + a + "^\\circ = \\mathbf{" + b + "^\\circ}\\)");
	}
}
