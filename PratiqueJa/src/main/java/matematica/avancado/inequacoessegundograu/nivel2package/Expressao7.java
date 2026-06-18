package matematica.avancado.inequacoessegundograu.nivel2package;

import java.util.ArrayList;
import java.util.List;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.ParCor;

// Quadratic inequality with a > 1, two positive integer roots, strict or non-strict
public class Expressao7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int r1 = 1 + rand.nextInt(5);
		int r2 = r1 + 1 + rand.nextInt(4);
		int a = 2 + rand.nextInt(2);
		int b = -a * (r1 + r2);
		int c = a * r1 * r2;

		boolean pedirNegativo = rand.nextBoolean();
		boolean strict = rand.nextBoolean();
		String sinal;
		if (pedirNegativo)
			sinal = strict ? "<" : "\\leq";
		else
			sinal = strict ? ">" : "\\geq";

		String eq = Auxiliar.getNumber(a, "x^2", true)
				+ Auxiliar.getNumber(b, "x", false)
				+ Auxiliar.getNumber(c, "", false);
		String inequacao = eq + sinal + "0";

		String abre = strict ? "(" : "[";
		String fecha = strict ? ")" : "]";

		String correta, d1, d2, d3;
		if (pedirNegativo)
		{
			correta = "\\(x \\in " + abre + r1 + ",\\," + r2 + fecha + "\\)";
			d1 = "\\(x \\in (-\\infty,\\," + r1 + ") \\cup (" + r2 + ",\\,+\\infty)\\)";
			d2 = strict
					? "\\(x \\in [" + r1 + ",\\," + r2 + "]\\)"
					: "\\(x \\in (" + r1 + ",\\," + r2 + ")\\)";
			d3 = "\\(x \\in (" + r1 + ",\\," + (r2 + 1) + ")\\)";
		}
		else
		{
			correta = "\\(x \\in (-\\infty,\\," + r1 + fecha + " \\cup " + abre + r2 + ",\\,+\\infty)\\)";
			d1 = "\\(x \\in (" + r1 + ",\\," + r2 + ")\\)";
			d2 = strict
					? "\\(x \\in (-\\infty,\\," + r1 + "] \\cup [" + r2 + ",\\,+\\infty)\\)"
					: "\\(x \\in (-\\infty,\\," + r1 + ") \\cup (" + r2 + ",\\,+\\infty)\\)";
			d3 = "\\(x \\in (-\\infty,\\," + (r1 - 1) + ") \\cup (" + (r2 + 1) + ",\\,+\\infty)\\)";
		}

		List<String> distratores = new ArrayList<>();
		distratores.add(d1);
		distratores.add(d2);
		distratores.add(d3);

		int delta = b * b - 4 * a * c;
		int sqrtDelta = a * (r2 - r1);
		int negB = -b;
		int twoA = 2 * a;
		String bStr = b < 0 ? "(" + b + ")" : "" + b;

		String res = "\\(" + ParCor.formula("\\Delta=b^2-4ac") + "\\)" + "\\(\\\\\\)";
		res += "\\(a=" + a + ", \\quad b=" + b + ", \\quad c=" + c + "\\)" + "\\(\\\\\\)";
		res += "\\(\\Delta=" + bStr + "^2-4\\cdot " + a + "\\cdot " + c + " = \\\\ \\)";
		res += "\\(\\Delta=" + (b * b) + Auxiliar.getNumber(-4 * a * c, "", false) + "=" + delta + "\\)" + "\\(\\\\\\)";
		res += "\\(" + ParCor.formula("x=\\dfrac{-b\\pm\\sqrt{\\Delta}}{2a}") + "\\)" + "\\(\\\\\\)";
		res += "\\(x=\\dfrac{" + negB + "\\pm " + sqrtDelta + "}{" + twoA + "}\\)" + "\\(\\\\\\)";
		res += "\\(x_1=\\dfrac{" + negB + "-" + sqrtDelta + "}{" + twoA + "}="
				+ "\\dfrac{" + (negB - sqrtDelta) + "}{" + twoA + "}=" + r1 + "\\)" + "\\(\\\\\\)";
		res += "\\(x_2=\\dfrac{" + negB + "+" + sqrtDelta + "}{" + twoA + "}="
				+ "\\dfrac{" + (negB + sqrtDelta) + "}{" + twoA + "}=" + r2 + "\\)" + "\\(\\\\\\)";
		if (pedirNegativo)
		{
			res += "Como \\(a=" + a + ">0\\), a parábola abre para cima: \\(f(x)" + sinal + "0\\) entre as raízes. \\(\\\\\\)";
			res += "\\(\\mathbf{x \\in " + abre + r1 + ",\\," + r2 + fecha + "}\\)";
		}
		else
		{
			res += "Como \\(a=" + a + ">0\\), a parábola abre para cima: \\(f(x)" + sinal + "0\\) fora das raízes. \\(\\\\\\)";
			res += "\\(\\mathbf{x \\in (-\\infty,\\," + r1 + fecha + " \\cup " + abre + r2 + ",\\,+\\infty)}\\)";
		}

		addParagrafo("Resolva a inequação");
		addParagrafo("\\(" + inequacao + "\\)");
		embaralharEAdicionarAlternativas(correta, distratores);
		setResolucao(res);
	}
}
