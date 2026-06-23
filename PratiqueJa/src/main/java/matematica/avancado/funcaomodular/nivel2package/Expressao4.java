package matematica.avancado.funcaomodular.nivel2package;

import matematica.GeradorExercicio;

public class Expressao4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// |x + a| = |x + b| com a ≠ b
		// Caso 1: x+a = x+b → impossível
		// Caso 2: x+a = -(x+b) → 2x = -a-b → x = -(a+b)/2
		// Garante x inteiro: a+b par (ambos ímpares)
		int a = 1 + 2 * rand.nextInt(4);  // ímpar: 1,3,5,7
		int b = 1 + 2 * rand.nextInt(4);  // ímpar: 1,3,5,7
		while (b == a) b = 1 + 2 * rand.nextInt(4);

		int x = -(a + b) / 2;  // inteiro pois a+b é par (dois ímpares)

		String aStr = a > 0 ? "x + " + a : "x - " + (-a);
		String bStr = b > 0 ? "x + " + b : "x - " + (-b);

		addParagrafo("Resolva: \\(|" + aStr + "| = |" + bStr + "|\\)");

		gerarAlternativas("" + x);

		addResolucao("\\(|f| = |g| \\Rightarrow f = g\\) ou \\(f = -g\\):");
		addResolucao("Caso 1: \\(x + " + a + " = x + " + b + " \\Rightarrow " + a + " = " + b + "\\) (impossível)");
		addResolucao("Caso 2: \\(x + " + a + " = -(x + " + b + ")\\)");
		addResolucao("\\(x + " + a + " = -x - " + b + " \\Rightarrow 2x = -" + (a + b) + "\\)");
		addResolucao("\\(x = \\mathbf{" + x + "}\\)");
	}
}
