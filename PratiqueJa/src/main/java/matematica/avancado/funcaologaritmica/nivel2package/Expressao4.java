package matematica.avancado.funcaologaritmica.nivel2package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Expressao4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Inequação: log_a(x) ≥ n (a > 1) → x ≥ a^n
		int[] bases = {2, 3, 4};
		int a = bases[rand.nextInt(bases.length)];
		int n = 2 + rand.nextInt(2); // 2 ou 3
		int xMin = (int) Math.pow(a, n);

		addParagrafo("Resolva a inequação: \\(\\log_{" + a + "}(x) \\geq " + n + "\\)");

		String correto = "\\(x \\geq " + xMin + "\\)";
		List<String> dist = new ArrayList<>();
		dist.add("\\(x \\leq " + xMin + "\\)");
		dist.add("\\(x > " + xMin + "\\)");
		dist.add("\\(x \\geq " + (xMin + 1) + "\\)");
		embaralharEAdicionarAlternativas(correto, dist);

		String res = "Base \\(" + a + " > 1\\): função crescente, a desigualdade é preservada. \\(\\\\\\)";
		res += "\\(\\log_{" + a + "}(x) \\geq " + n + " \\Leftrightarrow x \\geq " + a + "^{" + n + "}\\) \\(\\\\\\)";
		res += "\\(x \\geq \\mathbf{" + xMin + "}\\)";
		setResolucao(res);
	}
}
