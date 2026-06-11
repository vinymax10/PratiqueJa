package matematica.avancado.polinomios.nivel2package;

import java.util.ArrayList;
import java.util.List;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.avancado.polinomios.Polinomio;

// Expand (ax − b)² = a²x² − 2abx + b²
public class Expressao1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 2 + rand.nextInt(2);
		int b = 2 + rand.nextInt(5);

		int c2 = a * a;
		int c1 = -2 * a * b;
		int c0 = b * b;

		String expr = "(" + a + "x - " + b + ")^2";

		String correct = Polinomio.termo(c2, 2, true)
				+ Polinomio.termo(c1, 1, false)
				+ Auxiliar.getNumber(c0, "", false);

		// d1: positive middle term — confused with (ax+b)²
		String d1 = Polinomio.termo(c2, 2, true) + Polinomio.termo(-c1, 1, false)
				+ Auxiliar.getNumber(c0, "", false);
		// d2: missing factor 2 in middle term
		String d2 = Polinomio.termo(c2, 2, true) + Polinomio.termo(-a * b, 1, false)
				+ Auxiliar.getNumber(c0, "", false);
		// d3: confused with (ax+b)(ax-b) = a²x²-b²
		String d3 = Polinomio.termo(c2, 2, true) + Auxiliar.getNumber(-c0, "", false);

		List<String> distratores = new ArrayList<>();
		distratores.add("\\(" + d1 + "\\)");
		distratores.add("\\(" + d2 + "\\)");
		distratores.add("\\(" + d3 + "\\)");

		String res = "Usando o caso notável \\((a-b)^2 = a^2 - 2ab + b^2\\):\\(\\\\\\)";
		res += "\\(a = " + a + "x, \\quad b = " + b + "\\)\\(\\\\\\)";
		res += "\\((" + a + "x)^2 - 2 \\cdot " + a + "x \\cdot " + b + " + " + b + "^2 = \\\\ \\)";
		res += "\\(\\mathbf{" + correct + "}\\)";

		addParagrafo("Expanda a expressão usando casos notáveis.");
		addParagrafo("\\(" + expr + "\\)");
		embaralharEAdicionarAlternativas("\\(" + correct + "\\)", distratores);
		setResolucao(res);
	}
}
