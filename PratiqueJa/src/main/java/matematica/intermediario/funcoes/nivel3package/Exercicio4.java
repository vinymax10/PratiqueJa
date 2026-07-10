package matematica.intermediario.funcoes.nivel3package;

import matematica.GeradorExercicio;

public class Exercicio4 extends GeradorExercicio
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

		addResolucao("\\((f \\circ f)(" + v + ") = f\\!\\left(f(" + v + ")\\right)\\).");
		addResolucao("Calculamos \\(f(" + v + ")\\):");
		addResolucao("\\(f(" + v + ") = " + a + " \\cdot " + vStr + bLatex(b)
				+ " = " + (a * v) + bLatex(b) + " = " + fv + "\\).");
		addResolucao("Agora calculamos \\(f(" + fv + ")\\):");
		addResolucao("\\(f(" + fv + ") = " + a + " \\cdot " + fvStr + bLatex(b)
				+ " = " + (a * fv) + bLatex(b) + " = " + ffv + "\\).");
		addResolucao("Portanto, \\((f \\circ f)(" + v + ") = \\mathbf{" + ffv + "}\\).");
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
