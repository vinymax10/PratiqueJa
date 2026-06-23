package matematica.avancado.funcaomodular.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Expressao4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// |2x + b| ≥ k  →  x ≤ x1 ou x ≥ x2  (união com ≥, coeficiente 2)
		// Construção: x1 < 0 e x2 > 0
		// k = x2 - x1, b = -(x1 + x2) para a=2
		int x1  = -(1 + rand.nextInt(3));  // -1, -2, -3
		int x2  = 1 + rand.nextInt(3);     // 1, 2, 3
		int k   = x2 - x1;                 // = x2 + |x1| > 0
		int b   = -(x1 + x2);              // inteiro

		String bStr = b == 0 ? "2x" : (b > 0 ? "2x + " + b : "2x - " + (-b));

		addParagrafo("Resolva a inequação: \\(|" + bStr + "| \\geq " + k + "\\)");

		String correto = "\\(x \\leq " + x1 + "\\) ou \\(x \\geq " + x2 + "\\)";
		List<String> dist = new ArrayList<>();
		dist.add("\\(" + x1 + " \\leq x \\leq " + x2 + "\\)");
		dist.add("\\(x < " + x1 + "\\) ou \\(x > " + x2 + "\\)");
		dist.add("\\(x \\leq " + (x1 - 1) + "\\) ou \\(x \\geq " + (x2 + 1) + "\\)");
		embaralharEAdicionarAlternativas(correto, dist);

		int kb  = k - b;   // = 2*x2
		int mkb = -k - b;  // = 2*x1

		addResolucao("Regra: \\(|f(x)| \\geq k \\Leftrightarrow f(x) \\leq -k\\) ou \\(f(x) \\geq k\\)");
		addResolucao("Caso 1: \\(" + bStr + " \\leq -" + k + " \\Rightarrow 2x \\leq " + mkb + " \\Rightarrow x \\leq " + x1 + "\\)");
		addResolucao("Caso 2: \\(" + bStr + " \\geq " + k + " \\Rightarrow 2x \\geq " + kb + " \\Rightarrow x \\geq " + x2 + "\\)");
		addResolucao("Solução: \\(\\mathbf{x \\leq " + x1 + "}\\) ou \\(\\mathbf{x \\geq " + x2 + "}\\)");
	}
}
