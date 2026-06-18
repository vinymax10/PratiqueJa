package matematica.avancado.polinomios.nivel2package;

import java.util.ArrayList;
import java.util.List;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.avancado.polinomios.Polinomio;

// Factor difference of squares: a²x² − b² = (ax + b)(ax − b)
public class Expressao4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(5);
		int b = 2 + rand.nextInt(9);

		int c2 = a * a;
		int c0 = b * b;

		String poly = Polinomio.termo(c2, 2, true) + Auxiliar.getNumber(-c0, "", false);

		String aStr = (a == 1) ? "" : String.valueOf(a);
		String correct = "(" + aStr + "x + " + b + ")(" + aStr + "x - " + b + ")";

		// d1: confused with (ax+b)²
		String d1 = "(" + aStr + "x + " + b + ")^2";
		// d2: confused with (ax-b)²
		String d2 = "(" + aStr + "x - " + b + ")^2";
		// d3: wrong value of b
		String d3 = "(" + aStr + "x + " + (b + 1) + ")(" + aStr + "x - " + (b + 1) + ")";

		List<String> distratores = new ArrayList<>();
		distratores.add("\\(" + d1 + "\\)");
		distratores.add("\\(" + d2 + "\\)");
		distratores.add("\\(" + d3 + "\\)");

		String res = "Reconhecer o padrão \\(a^2 - b^2 = (a+b)(a-b)\\):\\(\\\\\\)";
		res += "\\(a = " + aStr + "x, \\quad b = " + b + "\\)\\(\\\\\\)";
		res += "\\((" + aStr + "x)^2 - " + b + "^2 = (" + aStr + "x + " + b + ")("
				+ aStr + "x - " + b + ")\\)\\(\\\\\\)";
		res += "\\(\\mathbf{" + correct + "}\\)";

		addParagrafo("Fatore usando diferença de quadrados.");
		addParagrafo("\\(" + poly + "\\)");
		embaralharEAdicionarAlternativas("\\(" + correct + "\\)", distratores);
		setResolucao(res);
	}
}
