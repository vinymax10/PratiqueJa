package matematica.intermediario.funcoes.nivel3package;

import matematica.GeradorExercicio;

public class Exercicio17 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// f(x) = af·x + bf,  g(x) = ag·x + bg
		// Calcular (g∘f)(v) = g(f(v))
		int af = 1 + rand.nextInt(4);  // 1..4
		int bf = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(6)); // ±1..6
		int ag = 1 + rand.nextInt(4);
		int bg = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(6));
		int v  = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(5)); // ±1..5

		int fv        = af * v + bf;
		int resultado = ag * fv + bg;

		String ef = equacaoStr(af, bf);
		String eg = equacaoStr(ag, bg);
		String vStr  = v  < 0 ? "\\left(" + v  + "\\right)" : "" + v;
		String fvStr = fv < 0 ? "\\left(" + fv + "\\right)" : "" + fv;

		addParagrafo("Sendo \\(f(x) = " + ef + "\\) e \\(g(x) = " + eg
				+ "\\), calcule \\((g \\circ f)(" + v + ")\\)");

		// Resolução passo a passo
		String res = "Pela definição, \\((g \\circ f)(" + v
				+ ") = g(f(" + v + "))\\). Calculamos \\(f(" + v + ")\\) primeiro: \\(\\\\\\)";

		// Passo f(v)
		if(af > 1)
			res += "\\(f(" + v + ") = " + af + " \\cdot " + vStr + bLatex(bf)
				+ " = " + (af * v) + bLatex(bf) + " = " + fv + "\\). \\(\\\\\\)";
		else
			res += "\\(f(" + v + ") = " + vStr + bLatex(bf) + " = " + fv + "\\). \\(\\\\\\)";

		// Passo g(f(v))
		res += "Calculamos \\(g(f(" + v + ")) = g(" + fv + ")\\): \\(\\\\\\)";
		if(ag > 1)
			res += "\\(g(" + fv + ") = " + ag + " \\cdot " + fvStr + bLatex(bg)
				+ " = " + (ag * fv) + bLatex(bg) + " = " + resultado + "\\). \\(\\\\\\)";
		else
			res += "\\(g(" + fv + ") = " + fvStr + bLatex(bg) + " = " + resultado + "\\). \\(\\\\\\)";

		res += "Portanto, \\((g \\circ f)(" + v + ") = " + resultado + "\\).";

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
