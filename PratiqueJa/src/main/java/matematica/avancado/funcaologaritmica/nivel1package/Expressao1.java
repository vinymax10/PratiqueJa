package matematica.avancado.funcaologaritmica.nivel1package;

import matematica.GeradorExercicio;

public class Expressao1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// f(x) = log_a(x), calcular f(a^n) = n
		int[] bases = {2, 3, 4, 5, 10};
		int a = bases[rand.nextInt(bases.length)];
		int n = 1 + rand.nextInt(4); // 1..4
		int arg = (int) Math.pow(a, n);

		String baseStr = "\\log_{" + a + "}";
		addParagrafo("Dada a função \\(f(x) = \\log_{" + a + "}(x)\\), calcule \\(f(" + arg + ")\\).");

		addResolucao("Escrever o argumento como potência da base:");

		String passo = "\\(f(" + arg + ") = " + baseStr + "(" + arg + ")";
		if (n > 1)
			passo += " = " + baseStr + "(" + a + "^{" + n + "})";
		passo += " = \\mathbf{" + n + "}\\)";

		gerarAlternativas("" + n);
		addResolucao(passo);
	}
}
