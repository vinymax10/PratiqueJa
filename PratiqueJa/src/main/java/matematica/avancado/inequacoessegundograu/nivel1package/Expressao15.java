package matematica.avancado.inequacoessegundograu.nivel1package;

import java.util.ArrayList;
import java.util.List;

import matematica.Auxiliar;
import matematica.GeradorExercicio;

// Incomplete quadratic inequality (b=0): x² + c  ⋈  0, roots ±r
public class Expressao15 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int r = 1 + rand.nextInt(8);
		int c = -(r * r);

		boolean pedirNegativo = rand.nextBoolean();
		boolean strict = rand.nextBoolean();

		String sinal;
		if (pedirNegativo)
			sinal = strict ? "<" : "\\leq";
		else
			sinal = strict ? ">" : "\\geq";

		String eq = "x^2" + Auxiliar.getNumber(c, "", false);
		String inequacao = eq + sinal + "0";

		// Interval notation depends on strict vs non-strict
		String abre = strict ? "(" : "[";
		String fecha = strict ? ")" : "]";

		// Correct answer and distractors
		String correta, d1, d2, d3;
		if (pedirNegativo)
		{
			// f < 0 or f ≤ 0  →  between roots
			correta = "\\(x \\in " + abre + "-" + r + ",\\," + r + fecha + "\\)";
			d1 = "\\(x \\in (-\\infty,\\,-" + r + ") \\cup (" + r + ",\\,+\\infty)\\)";
			d2 = strict
					? "\\(x \\in [-" + r + ",\\," + r + "]\\)"
					: "\\(x \\in (-" + r + ",\\," + r + ")\\)";
			d3 = "\\(x \\in (-" + r + ",\\," + (r + 1) + ")\\)";
		}
		else
		{
			// f > 0 or f ≥ 0  →  outside roots
			String lEsq = "(-\\infty,\\,-" + r + fecha;
			String lDir = abre + r + ",\\,+\\infty)";
			correta = "\\(x \\in " + lEsq + " \\cup " + lDir + "\\)";
			d1 = "\\(x \\in (-" + r + ",\\," + r + ")\\)";
			d2 = strict
					? "\\(x \\in (-\\infty,\\,-" + r + "] \\cup [" + r + ",\\,+\\infty)\\)"
					: "\\(x \\in (-\\infty,\\,-" + r + ") \\cup (" + r + ",\\,+\\infty)\\)";
			d3 = "\\(x \\in (-\\infty,\\,-" + (r + 1) + ") \\cup (" + (r + 1) + ",\\,+\\infty)\\)";
		}

		List<String> distratores = new ArrayList<>();
		distratores.add(d1);
		distratores.add(d2);
		distratores.add(d3);

		// Resolution
		String res = "Equação incompleta (\\(b=0\\)): isolar \\(x^2\\): \\(\\\\\\)";
		res += "\\(x^2=" + (r * r) + "\\)" + "\\(\\\\\\)";
		res += "\\(x=\\pm " + r + "\\), logo \\(x_1=-" + r + "\\) e \\(x_2=" + r + "\\)" + "\\(\\\\\\)";
		if (pedirNegativo)
		{
			res += "Como \\(a=1>0\\), a parábola abre para cima: \\(f(x)" + sinal + "0\\) entre as raízes. \\(\\\\\\)";
			res += "\\(\\mathbf{x \\in " + abre + "-" + r + ",\\," + r + fecha + "}\\)";
		}
		else
		{
			String lEsq = "(-\\infty,\\,-" + r + fecha;
			String lDir = abre + r + ",\\,+\\infty)";
			res += "Como \\(a=1>0\\), a parábola abre para cima: \\(f(x)" + sinal + "0\\) fora das raízes. \\(\\\\\\)";
			res += "\\(\\mathbf{x \\in " + lEsq + " \\cup " + lDir + "}\\)";
		}

		addParagrafo("Resolva a inequação");
		addParagrafo("\\(" + inequacao + "\\)");
		embaralharEAdicionarAlternativas(correta, distratores);
		setResolucao(res);
	}
}
