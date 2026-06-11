package matematica.avancado.polinomios.nivel2package;

import java.util.ArrayList;
import java.util.List;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.avancado.polinomios.Polinomio;

// Factor perfect square trinomial: a²x² ± 2abx + b² = (ax ± b)²
public class Expressao3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(3);
		int b = 2 + rand.nextInt(5);
		boolean plus = rand.nextBoolean();

		int c2 = a * a;
		int c1 = plus ? 2 * a * b : -2 * a * b;
		int c0 = b * b;

		String poly = Polinomio.termo(c2, 2, true)
				+ Polinomio.termo(c1, 1, false)
				+ Auxiliar.getNumber(c0, "", false);

		String aStr = (a == 1) ? "" : String.valueOf(a);
		String sign = plus ? "+" : "-";
		String correct = "(" + aStr + "x " + sign + " " + b + ")^2";

		// d1: confused with difference of squares: (ax+b)(ax-b)
		String d1 = "(" + aStr + "x + " + b + ")(" + aStr + "x - " + b + ")";
		// d2: wrong sign inside square
		String oppSign = plus ? "-" : "+";
		String d2 = "(" + aStr + "x " + oppSign + " " + b + ")^2";
		// d3: wrong constant
		String d3 = "(" + aStr + "x " + sign + " " + (b + 1) + ")^2";

		List<String> distratores = new ArrayList<>();
		distratores.add("\\(" + d1 + "\\)");
		distratores.add("\\(" + d2 + "\\)");
		distratores.add("\\(" + d3 + "\\)");

		String res = "Reconhecer o padrão \\(a^2 " + (plus ? "+" : "-") + " 2ab + b^2 = (a "
				+ sign + " b)^2\\):\\(\\\\\\)";
		res += "\\(a = " + aStr + "x, \\quad b = " + b + "\\)\\(\\\\\\)";
		res += "Verificando: \\(2ab = 2 \\cdot " + (a == 1 ? "" : a) + "x \\cdot " + b + " = "
				+ Math.abs(c1) + "x \\; \\checkmark\\)\\(\\\\\\)";
		res += "\\(\\mathbf{" + correct + "}\\)";

		addParagrafo("Fatore o trinômio quadrado perfeito.");
		addParagrafo("\\(" + poly + "\\)");
		embaralharEAdicionarAlternativas("\\(" + correct + "\\)", distratores);
		setResolucao(res);
	}
}
