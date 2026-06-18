package matematica.avancado.funcaomodular.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Expressao5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// |x + b| < k  →  -k-b < x < k-b  (intervalo aberto)
		int b   = rand.nextInt(9) - 4;  // -4..4
		int k   = 2 + rand.nextInt(5);  // 2..6
		int low = -k - b;
		int high = k - b;

		String bStr = b == 0 ? "x" : (b > 0 ? "x + " + b : "x - " + (-b));

		addParagrafo("Resolva a inequação: \\(|" + bStr + "| < " + k + "\\)");

		String correto = "\\(" + low + " < x < " + high + "\\)";
		List<String> dist = new ArrayList<>();
		dist.add("\\(x < " + low + "\\) ou \\(x > " + high + "\\)");
		dist.add("\\(" + low + " \\leq x \\leq " + high + "\\)");
		dist.add("\\(" + (low - 1) + " < x < " + (high + 1) + "\\)");
		embaralharEAdicionarAlternativas(correto, dist);

		String res  = "Regra: \\(|f(x)| < k \\Leftrightarrow -k < f(x) < k\\\\";
		res += "-" + k + " < " + bStr + " < " + k + "\\) \\(\\\\\\)";
		res += "Subtraindo \\(" + b + "\\) em todos os termos: \\(\\\\\\)";
		res += "\\(\\mathbf{" + low + " < x < " + high + "}\\)";
		setResolucao(res);
	}
}
