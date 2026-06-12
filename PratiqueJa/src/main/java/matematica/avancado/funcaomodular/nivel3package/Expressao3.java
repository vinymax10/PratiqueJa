package matematica.avancado.funcaomodular.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Expressao3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// |x + b| ≤ k  →  intervalo fechado [low, high]
		int b    = rand.nextInt(9) - 4;  // -4..4
		int k    = 2 + rand.nextInt(5);  // 2..6
		int low  = -k - b;
		int high = k - b;

		String bStr = b == 0 ? "x" : (b > 0 ? "x + " + b : "x - " + (-b));

		addParagrafo("Resolva a inequação: \\(|" + bStr + "| \\leq " + k + "\\)");

		String correto = "\\(" + low + " \\leq x \\leq " + high + "\\)";
		List<String> dist = new ArrayList<>();
		dist.add("\\(" + low + " < x < " + high + "\\)");
		dist.add("\\(x \\leq " + low + "\\) ou \\(x \\geq " + high + "\\)");
		dist.add("\\(" + (low - 1) + " \\leq x \\leq " + (high + 1) + "\\)");
		embaralharEAdicionarAlternativas(correto, dist);

		String res  = "Regra: \\(|f(x)| \\leq k \\Leftrightarrow -k \\leq f(x) \\leq k\\) \\(\\\\\\)";
		res += "\\(-" + k + " \\leq " + bStr + " \\leq " + k + "\\) \\(\\\\\\)";
		res += "Subtraindo \\(" + b + "\\) em todos os termos: \\(\\\\\\)";
		res += "\\(\\mathbf{" + low + " \\leq x \\leq " + high + "}\\)";
		setResolucao(res);
	}
}
