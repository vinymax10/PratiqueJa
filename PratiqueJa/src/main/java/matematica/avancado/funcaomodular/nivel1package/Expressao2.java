package matematica.avancado.funcaomodular.nivel1package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Expressao2 extends GeradorExercicio
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
		// Quando h>0 e h==k, distrator (k,h) == correto (h,k); deslocar k
		int kDist3 = (h > 0 && h == k) ? k + 1 : k;
		List<String> dist = new ArrayList<>();
		dist.add("\\((" + (-h) + ",\\," + k + ")\\)");
		dist.add("\\((" + h + ",\\," + (-k) + ")\\)");
		dist.add("\\((" + kDist3 + ",\\," + h + ")\\)");
		embaralharEAdicionarAlternativas(correto, dist);

		addResolucao("Na forma \\(f(x) = |x - h| + k\\), o vértice é \\((h,\\,k)\\).");
		addResolucao("Comparando: \\(h = " + h + "\\) e \\(k = " + k + "\\).");
		addResolucao("Vértice: \\(\\mathbf{(" + h + ",\\," + k + ")}\\)");
	}
}
