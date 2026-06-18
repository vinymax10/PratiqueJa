package matematica.avancado.funcaologaritmica.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Expressao6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Comparação: log_a(x) < log_a(k) com a > 1 → 0 < x < k
		int a = rand.nextBoolean() ? 2 : 3;
		int n = 1 + rand.nextInt(2); // expoente 1 ou 2
		int k = (int) Math.pow(a, n); // ex: 2^2=4, 2^1=2, 3^2=9

		addParagrafo("Para quais valores de \\(x\\) é verdade que "
			+ "\\(\\log_{" + a + "}(x) < \\log_{" + a + "}(" + k + ")\\)?");

		String correto = "\\(0 < x < " + k + "\\)";
		List<String> dist = new ArrayList<>();
		dist.add("\\(x < " + k + "\\)");
		dist.add("\\(x > " + k + "\\)");
		dist.add("\\(0 < x < " + (k - 1) + "\\)");
		embaralharEAdicionarAlternativas(correto, dist);

		String res = "Base \\(" + a + " > 1\\): \\(\\log_{" + a + "}\\) é crescente, "
			+ "preserva a ordem: \\(\\\\\\)";
		res += "\\(\\log_{" + a + "}(x) < \\log_{" + a + "}(" + k + ") "
			+ "\\Leftrightarrow x < " + k + "\\) \\(\\\\\\)";
		res += "Incluindo o domínio \\((x > 0)\\): \\(\\mathbf{0 < x < " + k + "}\\)";
		setResolucao(res);
	}
}
