package matematica.intermediario.notacaocientifica.nivel2package;

import java.util.Arrays;
import matematica.GeradorExercicio;

// Multiplicação sem ajuste: (a×10^m)(b×10^n) = (a·b)×10^{m+n}, com a·b < 10
public class NotacaoCientifica1 extends GeradorExercicio
{
	// pares (a, b) com a*b < 10
	private static final int[][] PARES = {
		{2, 3}, {3, 2}, {2, 4}, {4, 2}, {2, 2},
		{1, 5}, {5, 1}, {1, 7}, {7, 1}, {1, 9},
		{9, 1}, {1, 6}, {6, 1}, {3, 3}, {1, 8}
	};

	@Override
	protected void construir()
	{
		int[] par = PARES[rand.nextInt(PARES.length)];
		int a = par[0], b = par[1];
		int prod = a * b;

		int m = 2 + rand.nextInt(5); // 2..6
		int n = 2 + rand.nextInt(5); // 2..6
		int exp = m + n;

		String correta = "\\(" + prod + " \\times 10^{" + exp + "}\\)";
		String e1     = "\\(" + prod + " \\times 10^{" + (exp + 1) + "}\\)";
		String e2     = "\\(" + prod + " \\times 10^{" + (exp - 1) + "}\\)";
		String e3     = "\\(" + (prod + 1) + " \\times 10^{" + exp + "}\\)";

		addParagrafo("Calcule, deixando o resultado em notação científica:");
		addParagrafo("\\((" + a + " \\times 10^{" + m + "}) \\times (" + b + " \\times 10^{" + n + "})\\)");
		embaralharEAdicionarAlternativas(correta, Arrays.asList(e1, e2, e3));
		addResolucao("Multiplicar as mantissas e somar os expoentes:");
		addResolucao("\\(" + a + " \\times " + b + " = " + prod + "\\)");
		addResolucao("\\(10^{" + m + "} \\times 10^{" + n + "} = 10^{" + m + " + " + n + "} = 10^{" + exp + "}\\)");
		addResolucao("\\(\\mathbf{" + prod + " \\times 10^{" + exp + "}}\\)");
	}
}
