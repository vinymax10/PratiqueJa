package matematica.avancado.funcaologaritmica.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Expressao3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Análise de sinal combinada: log_{1/a}(x) > 0  →  0 < x < 1  (base < 1, decrescente)
		int[] dens = {2, 3, 4};
		int den = dens[rand.nextInt(dens.length)];

		addParagrafo("Determine os valores de \\(x\\) para os quais \\(f(x) > 0\\), "
			+ "sabendo que \\(f(x) = \\log_{\\frac{1}{" + den + "}}(x)\\).");

		String correto = "\\(0 < x < 1\\)";
		List<String> dist = new ArrayList<>();
		dist.add("\\(x > 1\\)");
		dist.add("\\(x > 0\\)");
		dist.add("\\(x < 1\\)");
		embaralharEAdicionarAlternativas(correto, dist);

		String res = "Base \\(\\dfrac{1}{" + den + "} < 1\\): função \\(\\mathbf{decrescente}\\). \\(\\\\\\)";
		res += "\\(f(x) > 0 \\Leftrightarrow \\log_{\\frac{1}{" + den + "}}(x) > 0 = \\log_{\\frac{1}{" + den + "}}(1)\\) \\(\\\\\\)";
		res += "Como a função é decrescente, o sentido inverte: \\(x < 1\\). \\(\\\\\\)";
		res += "Com o domínio \\((x > 0)\\): \\(\\mathbf{0 < x < 1}\\)";
		setResolucao(res);
	}
}
