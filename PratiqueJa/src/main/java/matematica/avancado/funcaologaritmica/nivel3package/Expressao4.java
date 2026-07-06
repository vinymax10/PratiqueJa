package matematica.avancado.funcaologaritmica.nivel3package;

import matematica.GeradorExercicio;

public class Expressao4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Encontrar base: g(x)=a^x passa por (n, val) → a = val^(1/n) → a inteiro
		int[] bases = {2, 3, 4, 5};
		int a = bases[rand.nextInt(bases.length)];
		int n = 2 + rand.nextInt(2); // n = 2 ou 3
		int val = (int) Math.pow(a, n);

		String raizStr = (n == 2) ? "\\sqrt{" + val + "}" : "\\sqrt[3]{" + val + "}";

		addParagrafo("A função \\(g(x) = a^x\\) passa pelo ponto \\((" + n + ",\\," + val + ")\\).");
		addParagrafo("Sabendo que \\(f(x) = \\log_a(x)\\) é a inversa de \\(g\\), determine o valor de \\(a\\).");

		addResolucao("\\(g(" + n + ") = a^{" + n + "} = " + val + "\\)");
		addResolucao("\\(a = " + raizStr + " = \\mathbf{" + a + "}\\)");
		addResolucao("Portanto a base de \\(f\\) é \\(\\mathbf{" + a + "}\\).");

		gerarAlternativas("" + a);
	}
}
