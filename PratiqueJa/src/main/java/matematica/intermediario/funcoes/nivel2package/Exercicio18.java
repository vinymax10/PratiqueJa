package matematica.intermediario.funcoes.nivel2package;

import matematica.GeradorExercicio;

public class Exercicio18 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// f(x) = ax + b, calcular f(v1) + f(v2)
		int a  = 2 + rand.nextInt(4);                                      // 2..5
		int b  = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(6));   // ±1..6
		int v1 = 1 + rand.nextInt(5);                                      // 1..5
		int v2 = 1 + rand.nextInt(5);                                      // 1..5

		int fv1      = a * v1 + b;
		int fv2      = a * v2 + b;
		int resultado = fv1 + fv2;

		String eq   = equacaoStr(a, b);
		String aStr = a > 1 ? a + "" : "";

		addParagrafo("Sendo \\(f(x) = " + eq + "\\), calcule \\(f(" + v1 + ") + f(" + v2 + ")\\)");

		String res = "Calculamos cada valor separadamente: \\(\\\\\\)";
		res += "\\(f(" + v1 + ") = " + aStr + " \\cdot " + v1 + bLatex(b)
				+ " = " + (a * v1) + bLatex(b) + " = " + fv1 + "\\). \\(\\\\\\)";
		res += "\\(f(" + v2 + ") = " + aStr + " \\cdot " + v2 + bLatex(b)
				+ " = " + (a * v2) + bLatex(b) + " = " + fv2 + "\\). \\(\\\\\\)";
		res += "Somando os resultados: \\(\\\\\\)";
		String fv2Str = fv2 < 0 ? "\\left(" + fv2 + "\\right)" : "" + fv2;
		res += "\\(f(" + v1 + ") + f(" + v2 + ") = " + fv1 + " + " + fv2Str
				+ " = " + resultado + "\\).";

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
