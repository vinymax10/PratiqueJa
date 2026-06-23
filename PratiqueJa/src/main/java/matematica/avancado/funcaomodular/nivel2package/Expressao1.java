package matematica.avancado.funcaomodular.nivel2package;

import matematica.GeradorExercicio;

public class Expressao1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Resolver |x + b| = k → maior solução = k - b
		int b = rand.nextInt(7) - 3;  // -3..3
		int k = 2 + rand.nextInt(7);  // 2..8 (k > 0)
		int x1 = k - b;   // maior solução
		int x2 = -k - b;  // menor solução

		String bStr = b == 0 ? "x" : (b > 0 ? "x + " + b : "x - " + (-b));

		addParagrafo("Qual é a maior solução de \\(|" + bStr + "| = " + k + "\\)?");

		gerarAlternativas("" + x1);

		addResolucao("\\(|f(x)| = k \\Rightarrow f(x) = k\\) ou \\(f(x) = -k\\):");
		addResolucao("Caso 1: \\(" + bStr + " = " + k + " \\Rightarrow x = " + x1 + "\\)");
		addResolucao("Caso 2: \\(" + bStr + " = -" + k + " \\Rightarrow x = " + x2 + "\\)");
		addResolucao("Maior solução: \\(\\mathbf{" + x1 + "}\\)");
	}
}
