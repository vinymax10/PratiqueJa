package matematica.intermediario.notacaocientifica.nivel2package;

import java.util.Arrays;
import matematica.GeradorExercicio;

// Multiplicação com ajuste: a*b ≥ 10 → reajustar mantissa e incrementar expoente
public class NotacaoCientifica3 extends GeradorExercicio
{
	// pares (a, b) com a*b múltiplo de 10 e a*b/10 dígito único
	private static final int[][] PARES = {
		{2, 5}, {5, 2}, {4, 5}, {5, 4}, {6, 5}, {5, 6}, {8, 5}, {5, 8}
	};

	@Override
	protected void construir()
	{
		int[] par = PARES[rand.nextInt(PARES.length)];
		int a = par[0], b = par[1];
		int prod = a * b;      // 10, 20, 30 ou 40
		int r    = prod / 10;  // mantissa ajustada: 1..4

		int m = 2 + rand.nextInt(5); // 2..6
		int n = 2 + rand.nextInt(5); // 2..6
		int expResult = m + n + 1;   // +1 pelo reajuste (prod ≥ 10)

		String correta = "\\(" + r + " \\times 10^{" + expResult + "}\\)";
		String e1     = "\\(" + r + " \\times 10^{" + (expResult - 1) + "}\\)"; // esqueceu o +1
		String e2     = "\\(" + r + " \\times 10^{" + (expResult + 1) + "}\\)"; // expoente a mais
		String e3     = "\\(" + prod + " \\times 10^{" + (m + n) + "}\\)";      // sem ajuste

		addParagrafo("Calcule, deixando o resultado em notação científica:");
		addParagrafo("\\((" + a + " \\times 10^{" + m + "}) \\times (" + b + " \\times 10^{" + n + "})\\)");
		embaralharEAdicionarAlternativas(correta, Arrays.asList(e1, e2, e3));
		addResolucao("Multiplicar as mantissas e somar os expoentes:");
		addResolucao("\\(" + a + " \\times " + b + " = " + prod + "\\)");
		addResolucao("\\(" + prod + " \\times 10^{" + (m + n) + "}\\)");
		addResolucao("Reajustar: \\(" + prod + " \\geq 10\\), escrever como \\(" + r + " \\times 10^1\\):");
		addResolucao("\\(" + r + " \\times 10^1 \\times 10^{" + (m + n) + "} = \\mathbf{" + r + " \\times 10^{" + expResult + "}}\\)");
	}
}
