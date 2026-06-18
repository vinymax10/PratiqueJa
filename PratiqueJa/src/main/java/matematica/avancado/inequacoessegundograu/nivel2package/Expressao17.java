package matematica.avancado.inequacoessegundograu.nivel2package;

import java.util.ArrayList;
import java.util.List;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.ParCor;

// Inequality requiring rearrangement: x² + bx  ⋈  −c  →  x² + bx + c  ⋈  0
public class Expressao17 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int r1 = 1 + rand.nextInt(5);
		int r2 = r1 + 1 + rand.nextInt(5);
		int b = -(r1 + r2);
		int c = r1 * r2;                    // positive
		int rhs = -c;                        // negative right-hand side displayed

		boolean pedirNegativo = rand.nextBoolean();
		boolean strict = rand.nextBoolean();
		String sinal;
		if (pedirNegativo)
			sinal = strict ? "<" : "\\leq";
		else
			sinal = strict ? ">" : "\\geq";

		// Displayed equation: x² + bx  ⋈  rhs  (i.e. x² + bx  ⋈  -c)
		String eqExibida = "x^2" + Auxiliar.getNumber(b, "x", false) + sinal + rhs;

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

		int delta = b * b - 4 * c;
		int sqrtDelta = r2 - r1;
		int negB = -b;
		String bStr = b < 0 ? "(" + b + ")" : "" + b;

		String eqRearranjada = "x^2" + Auxiliar.getNumber(b, "x", false) + Auxiliar.getNumber(c, "", false);

		String res = "Passar \\(" + rhs + "\\) para o lado esquerdo: \\(\\\\\\)";
		res += "\\(" + eqRearranjada + sinal + "0\\)" + "\\(\\\\\\)";
		res += "\\(" + ParCor.formula("\\Delta=b^2-4ac") + "\\)" + "\\(\\\\\\)";
		res += "\\(a=1, \\quad b=" + b + ", \\quad c=" + c + "\\)" + "\\(\\\\\\)";
		res += "\\(\\Delta=" + bStr + "^2-4\\cdot " + c + " = \\\\ \\)";
		res += "\\(\\Delta=" + (b * b) + Auxiliar.getNumber(-4 * c, "", false) + "=" + delta + "\\)" + "\\(\\\\\\)";
		res += "\\(" + ParCor.formula("x=\\dfrac{-b\\pm\\sqrt{\\Delta}}{2a}") + "\\)" + "\\(\\\\\\)";
		res += "\\(x=\\dfrac{" + negB + "\\pm " + sqrtDelta + "}{2}\\)" + "\\(\\\\\\)";
		res += "\\(x_1=\\dfrac{" + negB + "-" + sqrtDelta + "}{2}=" + r1
				+ ", \\quad x_2=\\dfrac{" + negB + "+" + sqrtDelta + "}{2}=" + r2 + "\\)" + "\\(\\\\\\)";
		if (pedirNegativo)
		{
			res += "Como \\(a=1>0\\), a parábola abre para cima: \\(f(x)" + sinal + "0\\) entre as raízes. \\(\\\\\\)";
			res += "\\(\\mathbf{x \\in " + abre + r1 + ",\\," + r2 + fecha + "}\\)";
		}
		else
		{
			res += "Como \\(a=1>0\\), a parábola abre para cima: \\(f(x)" + sinal + "0\\) fora das raízes. \\(\\\\\\)";
			res += "\\(\\mathbf{x \\in (-\\infty,\\," + r1 + fecha + " \\cup " + abre + r2 + ",\\,+\\infty)}\\)";
		}

		addParagrafo("Resolva a inequação");
		addParagrafo("\\(" + eqExibida + "\\)");
		embaralharEAdicionarAlternativas(correta, distratores);
		setResolucao(res);
	}
}
