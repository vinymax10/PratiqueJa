package matematica.avancado.logaritmo.nivel3package;

import matematica.GeradorExercicio;

public class Expressao4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Equação: log_b(x^p) = n → p·log_b(x) = n → x = b^(n/p)
		// Garante resultado inteiro: n = p * logX
		int b    = 2 + rand.nextInt(3); // 2, 3, 4
		int p    = 2 + rand.nextInt(2); // 2 ou 3 (expoente em x)
		int logX = 1 + rand.nextInt(3); // log_b(x) = 1, 2, 3
		int n    = p * logX;
		int x    = (int) Math.pow(b, logX);

		addParagrafo("Resolva a equação logarítmica:");
		addParagrafo("\\(\\log_{" + b + "} x^{" + p + "} = " + n + "\\)");

		addResolucao("Propriedade da potência: \\(\\log_b x^p = p \\cdot \\log_b x\\)");
		addResolucao("\\(" + p + " \\cdot \\log_{" + b + "} x = " + n + "\\)");
		addResolucao("\\(\\log_{" + b + "} x = \\dfrac{" + n + "}{" + p + "} = " + logX + "\\)");
		addResolucao("\\(x = " + b + "^{" + logX + "} = \\mathbf{" + x + "}\\)");

		gerarAlternativas("" + x);
	}
}
