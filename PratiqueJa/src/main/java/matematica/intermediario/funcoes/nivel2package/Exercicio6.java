package matematica.intermediario.funcoes.nivel2package;

import matematica.GeradorExercicio;

public class Exercicio6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// f(x) = ax + b, calcular f(v)
		int a = 2 + rand.nextInt(5);                 // 2..6
		int b = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(8)); // ±1..8, b ≠ 0
		int v = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(5)); // ±1..5, v ≠ 0
		int resultado = a * v + b;

		String equacao = equacaoStr(a, b);
		String vStr    = v < 0 ? "\\left(" + v + "\\right)" : "" + v;
		int    prod    = a * v;

		addParagrafo("Sendo \\(f(x) = " + equacao + "\\), calcule \\(f(" + v + ")\\)");

		// Resolução: substitui v, mostra produto, resultado
		String res = "Substituindo \\(x = " + v + "\\) em \\(f(x) = " + equacao + "\\): \\(\\\\\\)";
		res += "\\(f(" + v + ") = " + a + " \\cdot " + vStr + bLatex(b) + " = "
			+ prod + bLatex(b) + " = " + resultado + "\\).";

		setResolucao(res);
		gerarAlternativas("" + resultado);
	}

	private static String equacaoStr(int a, int b)
	{
		return (a == 1 ? "" : "" + a) + "x" + bLatex(b);
	}

	private static String bLatex(int b)
	{
		if(b > 0) return " + " + b;
		if(b < 0) return " - " + Math.abs(b);
		return "";
	}
}
