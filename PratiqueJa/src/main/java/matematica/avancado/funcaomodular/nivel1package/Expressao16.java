package matematica.avancado.funcaomodular.nivel1package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Expressao16 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Imagem de f(x) = |x - h| + k  →  [k, +∞)
		int h = rand.nextInt(9) - 4;  // -4..4
		int k = 1 + rand.nextInt(5);  // 1..5 (k > 0 para alternativas não-triviais)

		String modStr  = h == 0 ? "|x|" : (h > 0 ? "|x - " + h + "|" : "|x + " + (-h) + "|");
		String funcStr = modStr + " + " + k;

		addParagrafo("Qual é a imagem (conjunto-imagem) da função \\(f(x) = " + funcStr + "\\)?");

		String correto = "\\([" + k + ",\\,+\\infty)\\)";
		List<String> dist = new ArrayList<>();
		dist.add("\\([0,\\,+\\infty)\\)");
		dist.add("\\(\\mathbb{R}\\)");
		dist.add("\\([-" + k + ",\\,+\\infty)\\)");
		embaralharEAdicionarAlternativas(correto, dist);

		String res = "\\(|x - h| \\geq 0\\) para todo \\(x \\in \\mathbb{R}\\). \\(\\\\\\)";
		res += "Logo o mínimo de \\(f\\) é atingido no vértice \\((h, k)\\): \\(\\\\\\)";
		res += "\\(f_{\\min} = 0 + " + k + " = " + k + "\\) \\(\\\\\\)";
		res += "Imagem: \\(\\mathbf{[" + k + ",\\,+\\infty)}\\)";
		setResolucao(res);
	}
}
