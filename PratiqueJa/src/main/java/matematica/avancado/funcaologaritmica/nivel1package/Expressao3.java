package matematica.avancado.funcaologaritmica.nivel1package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Expressao3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Zero da função logarítmica: f(x) = log_a(x) = 0 → x = 1, independente da base
		int[] bases = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 20};
		int a = bases[rand.nextInt(bases.length)];

		addParagrafo("Em qual valor de \\(x\\) a função \\(f(x) = \\log_{" + a + "}(x)\\) se anula?");

		List<String> dist = new ArrayList<>();
		dist.add("\\(x = 0\\)");
		dist.add("\\(x = " + a + "\\)");
		dist.add("\\(x = -1\\)");
		embaralharEAdicionarAlternativas("\\(x = 1\\)", dist);

		addResolucao("O zero ocorre quando \\(f(x) = 0\\):");
		addResolucao("\\(\\log_{" + a + "}(x) = 0 \\Leftrightarrow x = " + a + "^0 = \\mathbf{1}\\)");
		addResolucao("Toda função logarítmica passa pelo ponto \\((1,\\,0)\\).");
	}
}
