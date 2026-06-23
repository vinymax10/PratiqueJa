package matematica.avancado.polinomios.nivel1package;

import java.util.ArrayList;
import java.util.List;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.avancado.polinomios.Polinomio;

// Expand (ax + b)(ax − b) = a²x² − b²
public class Expressao4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(5);
		int b = 2 + rand.nextInt(9);

		int c2 = a * a;
		int c0 = b * b;

		String aStr = (a == 1) ? "" : String.valueOf(a);
		String expr = "(" + aStr + "x + " + b + ")(" + aStr + "x - " + b + ")";

		String correct = Polinomio.termo(c2, 2, true) + Auxiliar.getNumber(-c0, "", false);

		// d1: sum instead of difference
		String d1 = Polinomio.termo(c2, 2, true) + Auxiliar.getNumber(c0, "", false);
		// d2: confused with (a−b)²
		String d2 = Polinomio.termo(c2, 2, true) + Polinomio.termo(-2 * a * b, 1, false)
				+ Auxiliar.getNumber(c0, "", false);
		// d3: off-by-one on b²
		String d3 = Polinomio.termo(c2, 2, true) + Auxiliar.getNumber(-(c0 + 1), "", false);

		List<String> distratores = new ArrayList<>();
		distratores.add("\\(" + d1 + "\\)");
		distratores.add("\\(" + d2 + "\\)");
		distratores.add("\\(" + d3 + "\\)");

		addParagrafo("Expanda a expressão usando casos notáveis.");
		addParagrafo("\\(" + expr + "\\)");
		embaralharEAdicionarAlternativas("\\(" + correct + "\\)", distratores);
		addResolucao("Usando o caso notável \\((a+b)(a-b) = a^2 - b^2\\):");
		addResolucao("\\(a = " + aStr + "x, \\quad b = " + b + "\\)");
		addResolucao("\\((" + aStr + "x)^2 - " + b + "^2 = \\mathbf{" + correct + "}\\)");
	}
}
