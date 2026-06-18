package matematica.avancado.funcaomodular.nivel1package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Expressao18 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Identificar o vértice de f(x) = |x - h| + k
		int h = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(5)); // ±1..±5
		int k = 1 + rand.nextInt(5); // 1..5

		String modStr = h > 0 ? "|x - " + h + "|" : "|x + " + (-h) + "|";
		String funcStr = modStr + " + " + k;

		addParagrafo("Qual é o vértice da função \\(f(x) = " + funcStr + "\\)?");

		String correto = "\\((" + h + ",\\," + k + ")\\)";
		List<String> dist = new ArrayList<>();
		dist.add("\\((" + (-h) + ",\\," + k + ")\\)");
		dist.add("\\((" + h + ",\\," + (-k) + ")\\)");
		dist.add("\\((" + k + ",\\," + h + ")\\)");
		embaralharEAdicionarAlternativas(correto, dist);

		String res = "Na forma \\(f(x) = |x - h| + k\\), o vértice é \\((h,\\,k)\\). \\(\\\\\\)";
		res += "Comparando: \\(h = " + h + "\\) e \\(k = " + k + "\\). \\(\\\\\\)";
		res += "Vértice: \\(\\mathbf{(" + h + ",\\," + k + ")}\\)";
		setResolucao(res);
	}
}
