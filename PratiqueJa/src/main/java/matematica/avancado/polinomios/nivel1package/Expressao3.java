package matematica.avancado.polinomios.nivel1package;

import java.util.ArrayList;
import java.util.List;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.avancado.polinomios.Polinomio;

// Expand (ax + b)² using the notable case (a+b)² = a² + 2ab + b²
public class Expressao3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(5);
		int b = 2 + rand.nextInt(9);

		int c2 = a * a;
		int c1 = 2 * a * b;
		int c0 = b * b;

		String aStr = (a == 1) ? "" : String.valueOf(a);
		String expr = "(" + aStr + "x + " + b + ")^2";

		String correct = Polinomio.termo(c2, 2, true)
				+ Polinomio.termo(c1, 1, false)
				+ Auxiliar.getNumber(c0, "", false);

		// d1: missing middle term
		String d1 = Polinomio.termo(c2, 2, true) + Auxiliar.getNumber(c0, "", false);
		// d2: middle term without factor 2
		String d2 = Polinomio.termo(c2, 2, true) + Polinomio.termo(a * b, 1, false)
				+ Auxiliar.getNumber(c0, "", false);
		// d3: wrong sign on constant
		String d3 = Polinomio.termo(c2, 2, true) + Polinomio.termo(c1, 1, false)
				+ Auxiliar.getNumber(-c0, "", false);

		List<String> distratores = new ArrayList<>();
		distratores.add("\\(" + d1 + "\\)");
		distratores.add("\\(" + d2 + "\\)");
		distratores.add("\\(" + d3 + "\\)");

		addParagrafo("Expanda a expressão usando casos notáveis.");
		addParagrafo("\\(" + expr + "\\)");
		embaralharEAdicionarAlternativas("\\(" + correct + "\\)", distratores);
		addResolucao("Usando o caso notável \\((a+b)^2 = a^2 + 2ab + b^2\\):");
		addResolucao("\\(a = " + aStr + "x, \\quad b = " + b + "\\)");
		addResolucao("\\((" + aStr + "x)^2 + 2 \\cdot " + aStr + "x \\cdot " + b + " + " + b + "^2 = \\mathbf{" + correct + "}\\)");
	}
}
