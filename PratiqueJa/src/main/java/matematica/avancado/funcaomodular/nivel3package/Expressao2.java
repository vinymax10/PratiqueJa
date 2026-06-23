package matematica.avancado.funcaomodular.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Expressao2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// |x + b| > k  →  x < -k-b  ou  x > k-b  (união de intervalos abertos)
		int b    = rand.nextInt(7) - 3;  // -3..3
		int k    = 1 + rand.nextInt(5);  // 1..5
		int low  = -k - b;               // limite superior do ramo esquerdo
		int high = k - b;                // limite inferior do ramo direito

		String bStr = b == 0 ? "x" : (b > 0 ? "x + " + b : "x - " + (-b));

		addParagrafo("Resolva a inequação: \\(|" + bStr + "| > " + k + "\\)");

		String correto = "\\(x < " + low + "\\) ou \\(x > " + high + "\\)";
		List<String> dist = new ArrayList<>();
		dist.add("\\(" + low + " < x < " + high + "\\)");
		dist.add("\\(x \\leq " + low + "\\) ou \\(x \\geq " + high + "\\)");
		dist.add("\\(x < " + (low - 1) + "\\) ou \\(x > " + (high + 1) + "\\)");
		embaralharEAdicionarAlternativas(correto, dist);

		addResolucao("Regra: \\(|f(x)| > k \\Leftrightarrow f(x) < -k\\) ou \\(f(x) > k\\)");
		addResolucao("Caso 1: \\(" + bStr + " < -" + k + " \\Rightarrow x < " + low + "\\)");
		addResolucao("Caso 2: \\(" + bStr + " > " + k + " \\Rightarrow x > " + high + "\\)");
		addResolucao("Solução: \\(\\mathbf{x < " + low + "}\\) ou \\(\\mathbf{x > " + high + "}\\)");
	}
}
