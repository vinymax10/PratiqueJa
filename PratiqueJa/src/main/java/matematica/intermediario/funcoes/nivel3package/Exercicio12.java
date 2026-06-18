package matematica.intermediario.funcoes.nivel3package;

import matematica.GeradorExercicio;

public class Exercicio12 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// f(x) = a·x + b,  (f∘f)(v) = f(f(v)) = a·(a·v + b) + b
		int a = 2 + rand.nextInt(3);                                     // 2..4
		int b = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(5));  // ±1..5
		int v = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(5));  // ±1..5

		int fv  = a * v + b;
		int ffv = a * fv + b;
		String eq = equacaoStr(a, b);

		addParagrafo("Sendo \\(f(x) = " + eq + "\\), calcule \\((f \\circ f)(" + v + ")\\)");

		String vStr  = v  < 0 ? "\\left(" + v  + "\\right)" : "" + v;
		String fvStr = fv < 0 ? "\\left(" + fv + "\\right)" : "" + fv;

		String res = "\\((f \\circ f)(" + v + ") = f\\!\\left(f(" + v + ")\\right)\\). \\(\\\\\\)";
		res += "Calculamos \\(f(" + v + ")\\): \\(\\\\\\)";
		res += "\\(f(" + v + ") = " + a + " \\cdot " + vStr + bLatex(b)
				+ " = " + (a * v) + bLatex(b) + " = " + fv + "\\). \\(\\\\\\)";
		res += "Agora calculamos \\(f(" + fv + ")\\): \\(\\\\\\)";
		res += "\\(f(" + fv + ") = " + a + " \\cdot " + fvStr + bLatex(b)
				+ " = " + (a * fv) + bLatex(b) + " = " + ffv + "\\). \\(\\\\\\)";
		res += "Portanto, \\((f \\circ f)(" + v + ") = " + ffv + "\\).";

		setResolucao(res);
		gerarAlternativas("" + ffv);
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
