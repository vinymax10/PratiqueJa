package matematica.intermediario.funcoes.nivel3package;

import matematica.GeradorExercicio;

public class Exercicio2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// f(x) = ax + b  →  f⁻¹(y) = (y - b) / a
		// Calcular f⁻¹(k) onde k = f(xResult)
		int a       = 2 + rand.nextInt(4);                                    // 2..5
		int b       = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(8)); // ±1..8
		int xResult = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(8)); // ±1..8
		int k       = a * xResult + b;

		String equacao = equacaoStr(a, b);

		addParagrafo("Sendo \\(f(x) = " + equacao
				+ "\\), calcule \\(f^{-1}(" + k + ")\\)");

		// Resolução: isola x em f(x)=y, depois substitui y=k
		int kmb = k - b;
		addResolucao("Para encontrar \\(f^{-1}\\), isolamos \\(x\\) em \\(f(x) = y\\):");
		addResolucao("\\(" + equacao + " = y\\).");
		addResolucao("\\(" + a + "x = y" + bLatex(-b) + "\\).");
		addResolucao("\\(x = \\dfrac{y" + bLatex(-b) + "}{" + a
				+ "}\\), portanto \\(f^{-1}(y) = \\dfrac{y" + bLatex(-b) + "}{" + a + "}\\).");
		addResolucao("Substituindo \\(y = " + k + "\\):");
		addResolucao("\\(f^{-1}(" + k + ") = \\dfrac{" + k + bLatex(-b) + "}{" + a
				+ "} = \\dfrac{" + kmb + "}{" + a + "} = \\mathbf{" + xResult + "}\\).");
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
