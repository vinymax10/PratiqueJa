package matematica.intermediario.funcoes.nivel2package;

import matematica.GeradorExercicio;

public class Exercicio5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// f(x) = a·x² + c
		int a = 1 + rand.nextInt(4);                                     // 1..4
		int c = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(8));  // ±1..8
		int v = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(5));  // ±1..5

		int v2        = v * v;
		int resultado = a * v2 + c;

		String eq = quadStr(a, c);
		addParagrafo("Sendo \\(f(x) = " + eq + "\\), calcule \\(f(" + v + ")\\)");

		String vStr  = v < 0 ? "\\left(" + v + "\\right)" : "" + v;
		String aStr  = a > 1 ? a + " \\cdot " : "";
		addResolucao("Substituímos \\(x = " + v + "\\) na expressão:");
		addResolucao("\\(f(" + v + ") = " + aStr + vStr + "^2" + bLatex(c)
				+ " = " + aStr + v2 + bLatex(c) + " = " + resultado + "\\).");
		gerarAlternativas("" + resultado);
	}

	private static String quadStr(int a, int c)
	{
		return (a == 1 ? "" : a + "") + "x^2" + bLatex(c);
	}

	private static String bLatex(int b)
	{
		if(b > 0) return " + " + b;
		if(b < 0) return " - " + Math.abs(b);
		return "";
	}
}
