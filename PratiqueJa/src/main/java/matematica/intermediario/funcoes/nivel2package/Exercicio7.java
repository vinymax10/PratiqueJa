package matematica.intermediario.funcoes.nivel2package;

import matematica.GeradorExercicio;

public class Exercicio7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// f(x) = ax + b = k  →  x = (k - b) / a
		int a      = 2 + rand.nextInt(5);                                     // 2..6
		int b      = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(8));   // ±1..8
		int xResult = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(8));  // ±1..8
		int k      = a * xResult + b;

		String equacao = equacaoStr(a, b);
		String aStr    = a > 1 ? a + "" : "";

		addParagrafo("Sendo \\(f(x) = " + equacao + "\\), determine \\(x\\) tal que \\(f(x) = " + k + "\\)");

		// Resolução: resolução de ax + b = k
		int ladoDireito = k - b;
		String res = "Igualamos \\(f(x)\\) a \\(" + k + "\\): \\(\\\\\\)";
		res += "\\(" + aStr + "x" + bLatex(b) + " = " + k + "\\). \\(\\\\\\)";
		res += "\\(" + aStr + "x = " + k + bLatex(-b) + " = " + ladoDireito + "\\). \\(\\\\\\)";
		if(a > 1)
			res += "\\(x = \\dfrac{" + ladoDireito + "}{" + a + "} = " + xResult + "\\).";
		else
			res += "\\(x = " + xResult + "\\).";

		setResolucao(res);
		gerarAlternativas("" + xResult);
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
