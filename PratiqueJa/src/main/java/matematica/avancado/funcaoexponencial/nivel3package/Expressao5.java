package matematica.avancado.funcaoexponencial.nivel3package;

import matematica.GeradorExercicio;
import matematica.Racional;

public class Expressao5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// a^(m*x) = 1/a^n  =>  p^(m*x) = p^(-n)  =>  x = -n/m (Racional negativo)
		// Exemplo: 9^x = 1/27  =>  3^(2x) = 3^(-3)  =>  x = -3/2
		int p = rand.nextBoolean() ? 2 : 3;
		int m = 2 + rand.nextInt(3);  // 2, 3, 4
		int n = 2 + rand.nextInt(4);  // 2, 3, 4, 5
		while (n == m) n = 2 + rand.nextInt(4);

		int leftBase  = (int) Math.pow(p, m);  // p^m
		int rightDen  = (int) Math.pow(p, n);  // 1/p^n
		Racional x    = new Racional(-n, m);
		x.fatoracao(2);

		addParagrafo("Resolva a equação \\(" + leftBase + "^x = \\dfrac{1}{" + rightDen + "}\\).");

		String res = "Reescrevemos ambos os lados com base \\(" + p + "\\): \\(\\\\\\)";
		res += "\\(" + leftBase + " = " + p + "^{" + m + "}\\) \\(\\quad\\) e \\(\\quad\\)";
		res += "\\(\\dfrac{1}{" + rightDen + "} = " + p + "^{-" + n + "}\\) \\(\\\\\\)";
		res += "\\((" + p + "^{" + m + "})^x = " + p + "^{-" + n + "}\\) \\(\\\\\\)";
		res += "\\(" + p + "^{" + m + "x} = " + p + "^{-" + n + "}\\) \\(\\\\\\)";
		res += "Igualando os expoentes: \\(" + m + "x = -" + n + "\\) \\(\\\\\\)";
		res += "\\(x = \\dfrac{-" + n + "}{" + m + "} = \\mathbf{" + x.toStringLatex() + "}\\)";

		gerarAlternativas(x);
		setResolucao(res);
	}
}
