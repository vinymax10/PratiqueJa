package matematica.avancado.inequacoessegundograu.nivel2package;

import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;

// Factored form (x-r1)(x-r2)  ⋈  0 — sign table analysis
public class Expressao3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int r1 = 1 + rand.nextInt(6);
		int r2 = r1 + 1 + rand.nextInt(5);

		boolean pedirPositivo = rand.nextBoolean();
		boolean strict = rand.nextBoolean();
		String sinal;
		if (pedirPositivo)
			sinal = strict ? ">" : "\\geq";
		else
			sinal = strict ? "<" : "\\leq";

		String eq = "(x-" + r1 + ")(x-" + r2 + ")" + sinal + "0";

		String abre = strict ? "(" : "[";
		String fecha = strict ? ")" : "]";

		String correta, d1, d2, d3;
		if (pedirPositivo)
		{
			// product > 0 or ≥ 0  →  outside roots
			correta = "\\(x \\in (-\\infty,\\," + r1 + fecha + " \\cup " + abre + r2 + ",\\,+\\infty)\\)";
			d1 = "\\(x \\in (" + r1 + ",\\," + r2 + ")\\)";
			d2 = strict
					? "\\(x \\in (-\\infty,\\," + r1 + "] \\cup [" + r2 + ",\\,+\\infty)\\)"
					: "\\(x \\in (-\\infty,\\," + r1 + ") \\cup (" + r2 + ",\\,+\\infty)\\)";
			d3 = "\\(x \\in (-\\infty,\\," + (r1 - 1) + ") \\cup (" + (r2 + 1) + ",\\,+\\infty)\\)";
		}
		else
		{
			// product < 0 or ≤ 0  →  between roots
			correta = "\\(x \\in " + abre + r1 + ",\\," + r2 + fecha + "\\)";
			d1 = "\\(x \\in (-\\infty,\\," + r1 + ") \\cup (" + r2 + ",\\,+\\infty)\\)";
			d2 = strict
					? "\\(x \\in [" + r1 + ",\\," + r2 + "]\\)"
					: "\\(x \\in (" + r1 + ",\\," + r2 + ")\\)";
			d3 = "\\(x \\in (" + r1 + ",\\," + (r2 + 1) + ")\\)";
		}

		List<String> distratores = new ArrayList<>();
		distratores.add(d1);
		distratores.add(d2);
		distratores.add(d3);

		String res = "Zeros dos fatores: \\(x_1=" + r1 + "\\) e \\(x_2=" + r2 + "\\)" + "\\(\\\\\\)";
		res += "Estudar o sinal do produto em cada intervalo: \\(\\\\\\)";
		res += "Para \\(x<" + r1 + "\\): \\((x-" + r1 + ")<0\\) e \\((x-" + r2 + ")<0\\), produto \\((-)(-)=+\\)" + "\\(\\\\\\)";
		res += "Para \\(" + r1 + "<x<" + r2 + "\\): \\((x-" + r1 + ")>0\\) e \\((x-" + r2 + ")<0\\), produto \\((+)(-)=-\\)" + "\\(\\\\\\)";
		res += "Para \\(x>" + r2 + "\\): \\((x-" + r1 + ")>0\\) e \\((x-" + r2 + ")>0\\), produto \\((+)(+)=+\\)" + "\\(\\\\\\)";
		if (pedirPositivo)
		{
			String cond = strict ? "produto \\(>0\\)" : "produto \\(\\geq 0\\)";
			res += "A condição " + cond + " é satisfeita fora das raízes" + (strict ? "." : " (incluindo os zeros).") + " \\(\\\\\\)";
			res += "\\(\\mathbf{x \\in (-\\infty,\\," + r1 + fecha + " \\cup " + abre + r2 + ",\\,+\\infty)}\\)";
		}
		else
		{
			String cond = strict ? "produto \\(<0\\)" : "produto \\(\\leq 0\\)";
			res += "A condição " + cond + " é satisfeita entre as raízes" + (strict ? "." : " (incluindo os zeros).") + " \\(\\\\\\)";
			res += "\\(\\mathbf{x \\in " + abre + r1 + ",\\," + r2 + fecha + "}\\)";
		}

		addParagrafo("Resolva a inequação");
		addParagrafo("\\(" + eq + "\\)");
		embaralharEAdicionarAlternativas(correta, distratores);
		setResolucao(res);
	}
}
