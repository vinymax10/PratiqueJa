package matematica.intermediario.funcoes.nivel2package;

import matematica.GeradorExercicio;

public class Exercicio4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// f(x) = ax + b; verificar se o ponto (v, w) pertence ao gráfico
		int a = 2 + rand.nextInt(4);                                     // 2..5
		int b = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(6));  // ±1..6
		int v = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(5));  // ±1..5

		boolean pertence = rand.nextBoolean();
		int fv = a * v + b;
		int w  = pertence ? fv : fv + 1 + rand.nextInt(4);

		String eq = equacaoStr(a, b);
		addParagrafo("Dada \\(f(x) = " + eq + "\\), o ponto \\(\\left(" + v
				+ ",\\ " + w + "\\right)\\) pertence ao gráfico de \\(f\\)?");

		gerarAlternativasBoolean(pertence);

		String vStr = v < 0 ? "\\left(" + v + "\\right)" : "" + v;
		String res = "Um ponto \\((v,\\ w)\\) pertence ao gráfico de \\(f\\) "
				+ "se, e somente se, \\(f(v) = w\\). \\(\\\\\\)";
		res += "Calculamos \\(f(" + v + ")\\): \\(\\\\\\)";
		res += "\\(f(" + v + ") = " + a + " \\cdot " + vStr + bLatex(b)
				+ " = " + (a * v) + bLatex(b) + " = " + fv + "\\). \\(\\\\\\)";
		if(pertence)
			res += "Como \\(f(" + v + ") = " + fv + " = " + w
					+ "\\), o ponto \\(\\textbf{pertence}\\) ao gráfico.";
		else
			res += "Como \\(f(" + v + ") = " + fv + " \\neq " + w
					+ "\\), o ponto \\(\\textbf{não pertence}\\) ao gráfico.";

		setResolucao(res);
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
