package matematica.avancado.funcaomodular.nivel2package;

import matematica.GeradorExercicio;

public class Expressao2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// |2x + b| = k  →  soluções x1 = m e x2 = -n
		// Construção: k = m+n, b = n-m  (garante soluções inteiras)
		int m = 1 + rand.nextInt(4);  // 1..4 (solução positiva)
		int n = 1 + rand.nextInt(4);  // 1..4 (|solução negativa|)
		while (n == m) n = 1 + rand.nextInt(4);

		int k = m + n;
		int b = n - m;  // b != 0 pois m != n

		String bStr = b > 0 ? "2x + " + b : "2x - " + (-b);
		int soma = m + (-n);  // soma das soluções = m - n = -b

		addParagrafo("Determine a soma das soluções de \\(|" + bStr + "| = " + k + "\\).");

		String res = "Caso 1: \\(" + bStr + " = " + k + " \\Rightarrow 2x = " + (k - b) + " \\Rightarrow x = " + m + "\\) \\(\\\\\\)";
		res += "Caso 2: \\(" + bStr + " = -" + k + " \\Rightarrow 2x = " + (-k - b) + " \\Rightarrow x = " + (-n) + "\\) \\(\\\\\\)";
		res += "Soma: \\(" + m + " + (" + (-n) + ") = \\mathbf{" + soma + "}\\)";

		gerarAlternativas("" + soma);
		setResolucao(res);
	}
}
