package matematica.avancado.logaritmo.nivel2package;

import matematica.GeradorExercicio;

public class Expressao5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// log_b(a) + log_b(c) - log_b(d) com três termos
		// a = b^x1, c = b^x2, d = b^x3, resultado = x1+x2-x3
		int b  = 2 + rand.nextInt(4); // 2, 3, 4, 5
		int x1 = 1 + rand.nextInt(3);
		int x2 = 1 + rand.nextInt(3);
		int x3 = 1 + rand.nextInt(2);
		while (x1 + x2 - x3 <= 0) { x1 = 1 + rand.nextInt(3); x2 = 1 + rand.nextInt(3); }

		int x = x1 + x2 - x3;
		int a = (int) Math.pow(b, x1);
		int c = (int) Math.pow(b, x2);
		int d = (int) Math.pow(b, x3);

		addParagrafo("Calcule o valor da expressão:");
		addParagrafo("\\(\\log_{" + b + "} " + a + " + \\log_{" + b + "} " + c + " - \\log_{" + b + "} " + d + "\\)");

		long ac  = (long) a * c;
		long acd = ac / d;
		addResolucao("Aplicando produto e quociente:");
		addResolucao("\\(\\log_{" + b + "}\\!\\left(\\dfrac{" + a + " \\cdot " + c + "}{" + d + "}\\right) = \\log_{" + b + "} " + acd + "\\)");
		addResolucao("\\(\\log_{" + b + "} " + b + "^{" + x + "} = \\mathbf{" + x + "}\\)");

		gerarAlternativas("" + x);
	}
}
