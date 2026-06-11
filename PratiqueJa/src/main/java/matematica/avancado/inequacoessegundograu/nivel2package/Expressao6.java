package matematica.avancado.inequacoessegundograu.nivel2package;

import java.util.ArrayList;
import java.util.List;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.ParCor;

// Word problem: sum of two reals is k; product must exceed A → rearranges to x² - kx + A ⋈ 0
public class Expressao6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int r1 = 1 + rand.nextInt(5);
		int r2 = r1 + 1 + rand.nextInt(5);
		int k = r1 + r2;      // sum of the two numbers
		int A = r1 * r2;      // product threshold
		int b = -k;           // negative
		int c = A;            // positive

		boolean strict = rand.nextBoolean();
		String sinalOriginal = strict ? ">" : "\\geq";
		String sinalInvertido = strict ? "<" : "\\leq";
		String abre = strict ? "(" : "[";
		String fecha = strict ? ")" : "]";

		String correta = "\\(x \\in " + abre + r1 + ",\\," + r2 + fecha + "\\)";
		String d1 = "\\(x \\in (-\\infty,\\," + r1 + ") \\cup (" + r2 + ",\\,+\\infty)\\)";
		String d2 = strict
				? "\\(x \\in [" + r1 + ",\\," + r2 + "]\\)"
				: "\\(x \\in (" + r1 + ",\\," + r2 + ")\\)";
		String d3 = "\\(x \\in (" + r1 + ",\\," + (r2 + 1) + ")\\)";

		List<String> distratores = new ArrayList<>();
		distratores.add(d1);
		distratores.add(d2);
		distratores.add(d3);

		int delta = b * b - 4 * c;   // = (r2-r1)²
		int sqrtDelta = r2 - r1;
		int negB = -b;               // = k, positive
		String bStr = "(" + b + ")"; // b always negative here

		String eq = "x^2" + Auxiliar.getNumber(b, "x", false) + Auxiliar.getNumber(c, "", false);

		String maiorOuIgual = strict ? "maior que" : "maior ou igual a";

		String res = "Sendo \\(x\\) um dos números, o outro é \\(" + k + "-x\\). O produto deve ser " + maiorOuIgual + " \\(" + A + "\\): \\(\\\\\\)";
		res += "\\(x(" + k + "-x)" + sinalOriginal + A + "\\)" + "\\(\\\\\\)";
		res += "\\(" + k + "x-x^2" + sinalOriginal + A + "\\)" + "\\(\\\\\\)";
		res += "\\(-x^2+" + k + "x-" + A + sinalOriginal + "0\\)" + "\\(\\\\\\)";
		res += "Multiplicar por \\(-1\\) (inverter o sinal): \\(\\\\\\)";
		res += "\\(" + eq + sinalInvertido + "0\\)" + "\\(\\\\\\)";
		res += "\\(" + ParCor.formula("\\Delta=b^2-4ac") + "\\)" + "\\(\\\\\\)";
		res += "\\(a=1, \\quad b=" + b + ", \\quad c=" + c + "\\)" + "\\(\\\\\\)";
		res += "\\(\\Delta=" + bStr + "^2-4\\cdot " + c + " = \\\\ \\)";
		res += "\\(\\Delta=" + (b * b) + Auxiliar.getNumber(-4 * c, "", false) + "=" + delta + "\\)" + "\\(\\\\\\)";
		res += "\\(" + ParCor.formula("x=\\dfrac{-b\\pm\\sqrt{\\Delta}}{2a}") + "\\)" + "\\(\\\\\\)";
		res += "\\(x=\\dfrac{" + negB + "\\pm " + sqrtDelta + "}{2}\\)" + "\\(\\\\\\)";
		res += "\\(x_1=\\dfrac{" + negB + "-" + sqrtDelta + "}{2}=" + r1
				+ ", \\quad x_2=\\dfrac{" + negB + "+" + sqrtDelta + "}{2}=" + r2 + "\\)" + "\\(\\\\\\)";
		res += "Como \\(a=1>0\\), a parábola abre para cima: \\(f(x)" + sinalInvertido + "0\\) entre as raízes. \\(\\\\\\)";
		res += "\\(\\mathbf{x \\in " + abre + r1 + ",\\," + r2 + fecha + "}\\)";

		addParagrafo("A soma de dois números reais é \\(" + k + "\\). Determine os valores de \\(x\\) para os quais o produto dos dois números é " + maiorOuIgual + " \\(" + A + "\\).");
		embaralharEAdicionarAlternativas(correta, distratores);
		setResolucao(res);
	}
}
