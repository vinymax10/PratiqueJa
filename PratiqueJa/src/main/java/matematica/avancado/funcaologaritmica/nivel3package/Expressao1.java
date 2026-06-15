package matematica.avancado.funcaologaritmica.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Expressao1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Inequação com 0 < a < 1 (sentido inverte)
		// log_{1/den}(x) ≤ -n  →  x ≥ den^n
		int den = rand.nextBoolean() ? 2 : 3;
		int n   = 1 + rand.nextInt(2); // 1 ou 2
		int xMin = (int) Math.pow(den, n);

		String baseStr = "\\dfrac{1}{" + den + "}";
		String rhs     = (n == 1) ? "-1" : "-" + n;

		addParagrafo("Resolva a inequação: \\(\\log_{\\frac{1}{" + den + "}}(x) \\leq " + rhs + "\\)");

		String correto = "\\(x \\geq " + xMin + "\\)";
		List<String> dist = new ArrayList<>();
		dist.add("\\(x \\leq " + xMin + "\\)");
		dist.add("\\(x \\geq " + (1 > xMin - 1 ? xMin + 1 : xMin - 1) + "\\)");
		dist.add("\\(0 < x \\leq " + xMin + "\\)");
		embaralharEAdicionarAlternativas(correto, dist);

		String res = "Base \\(" + baseStr + " < 1\\): função \\(\\mathbf{decrescente}\\), "
			+ "o sentido da desigualdade \\(\\mathbf{inverte}\\) ao remover o logaritmo. \\(\\\\\\)";
		res += "\\(\\log_{\\frac{1}{" + den + "}}(x) \\leq " + rhs
			+ " = \\log_{\\frac{1}{" + den + "}} " + xMin + "\\\\";
		res += "\\Leftrightarrow x \\geq " + xMin + "\\) (sentido invertido) \\(\\\\\\)";
		res += "Solução: \\(\\mathbf{x \\geq " + xMin + "}\\)";
		setResolucao(res);
	}
}
