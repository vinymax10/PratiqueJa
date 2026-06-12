package matematica.avancado.logaritmo.nivel3package;

import matematica.GeradorExercicio;
import matematica.Racional;

public class Expressao2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Mudança de base: log_{p^m}(p^n) = log_p(p^n)/log_p(p^m) = n/m
		// Garante resultado fracionário: n não divisível por m
		int p = rand.nextBoolean() ? 2 : 3;
		int m = 2 + rand.nextInt(3); // 2, 3, 4
		int n = 2 + rand.nextInt(5); // 2..6
		while (n == m || n % m == 0) n = 2 + rand.nextInt(5);

		int leftBase = (int) Math.pow(p, m);
		int rightArg = (int) Math.pow(p, n);
		Racional resultado = new Racional(n, m);
		resultado.fatoracao(2);

		addParagrafo("Use a fórmula de mudança de base para calcular:");
		addParagrafo("\\(\\log_{" + leftBase + "} " + rightArg + " = \\,?\\)");

		String res = "Fórmula de mudança de base com base auxiliar \\(" + p + "\\): \\(\\\\\\)";
		res += "\\(\\log_{" + leftBase + "} " + rightArg + " = \\dfrac{\\log_{" + p + "} " + rightArg + "}{\\log_{" + p + "} " + leftBase + "}\\\\";
		res += "= \\dfrac{\\log_{" + p + "} " + p + "^{" + n + "}}{\\log_{" + p + "} " + p + "^{" + m + "}} = \\dfrac{" + n + "}{" + m + "}\\) \\(\\\\\\)";
		if (resultado.denominador != 1)
			res += "\\(= " + resultado.showDfrac() + " = \\mathbf{" + resultado.toStringLatex() + "}\\)";
		else
			res += "\\(= \\mathbf{" + resultado.toStringLatex() + "}\\)";

		gerarAlternativas(resultado);
		setResolucao(res);
	}
}
