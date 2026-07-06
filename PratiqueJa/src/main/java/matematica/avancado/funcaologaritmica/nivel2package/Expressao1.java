package matematica.avancado.funcaologaritmica.nivel2package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Expressao1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Análise de sinal: para quais x é f(x) < 0 ou f(x) > 0?
		boolean positivo = rand.nextBoolean();
		int[] bases = {2, 3, 4, 5, 6, 7, 8, 9, 10};
		int a = bases[rand.nextInt(bases.length)];

		String sinal    = positivo ? "> 0" : "< 0";
		String correto  = positivo ? "\\(x > 1\\)" : "\\(0 < x < 1\\)";
		String errado1  = positivo ? "\\(0 < x < 1\\)" : "\\(x > 1\\)";
		String errado2  = positivo ? "\\(x > 0\\)" : "\\(x < 1\\)";
		String errado3  = positivo ? "\\(x \\geq 1\\)" : "\\(0 < x \\leq 1\\)";

		String justificativa;
		if (positivo)
			justificativa = "\\(f(x) > 0 \\Leftrightarrow \\log_{" + a + "}(x) > 0 = \\log_{" + a + "}(1) "
				+ "\\Leftrightarrow x > 1\\) (base \\(> 1\\), função crescente)";
		else
			justificativa = "\\(f(x) < 0 \\Leftrightarrow \\log_{" + a + "}(x) < 0 = \\log_{" + a + "}(1) "
				+ "\\Leftrightarrow x < 1\\), com domínio \\(x > 0\\), logo \\(0 < x < 1\\)";

		addParagrafo("Para \\(f(x) = \\log_{" + a + "}(x)\\), determine os valores de \\(x\\) para os quais \\(f(x) " + sinal + "\\).");

		List<String> dist = new ArrayList<>();
		dist.add(errado1);
		dist.add(errado2);
		dist.add(errado3);
		embaralharEAdicionarAlternativas(correto, dist);

		addResolucao("O zero da função é \\(x = 1\\) (pois \\(\\log_{" + a + "}(1) = 0\\)).");
		addResolucao(justificativa);
	}
}
