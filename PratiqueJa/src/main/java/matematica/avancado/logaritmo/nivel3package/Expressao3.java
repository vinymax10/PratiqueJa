package matematica.avancado.logaritmo.nivel3package;

import matematica.GeradorExercicio;

public class Expressao3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Equação com quociente: log_b(x) - log_b(k) = n → x = b^n * k
		int b = rand.nextBoolean() ? 2 : 3;
		int k = 2 + rand.nextInt(8); // 2..9
		int n = 1 + rand.nextInt(3); // 1, 2, 3
		int x = (int) Math.pow(b, n) * k;

		addParagrafo("Resolva a equação logarítmica:");
		addParagrafo("\\(\\log_{" + b + "} x - \\log_{" + b + "} " + k + " = " + n + "\\)");

		addResolucao("Aplicando a propriedade do quociente:");
		addResolucao("\\(\\log_{" + b + "}\\!\\left(\\dfrac{x}{" + k + "}\\right) = " + n + "\\)");
		addResolucao("Convertendo para forma exponencial:");
		addResolucao("\\(\\dfrac{x}{" + k + "} = " + b + "^{" + n + "} = " + (int) Math.pow(b, n) + "\\)");
		addResolucao("\\(x = " + (int) Math.pow(b, n) + " \\cdot " + k + " = \\mathbf{" + x + "}\\)");

		gerarAlternativas("" + x);
	}
}
