package matematica.intermediario.radiciacao.nivel3package;

import java.util.Arrays;

import matematica.GeradorExercicio;

// Subtração com coeficientes: a·√n1 - b·√n2 = (aA - bB)√r, via simplificação
public class Radiciacao7 extends GeradorExercicio
{
	private static final int[] LIVRES = {2, 3, 5, 6, 7};

	@Override
	protected void construir()
	{
		int r, A, B, a, b, coef;
		do
		{
			r    = LIVRES[rand.nextInt(LIVRES.length)];
			A    = 3 + rand.nextInt(3); // 3..5
			B    = 2 + rand.nextInt(3); // 2..4
			a    = 1 + rand.nextInt(3); // 1..3
			b    = 1 + rand.nextInt(2); // 1..2
			coef = a * A - b * B;
		}
		while (coef <= 0 || A * A * r > 500 || B * B * r > 500);

		int nA = A * A * r;
		int nB = B * B * r;

		String ca    = (a == 1) ? "" : ("" + a);
		String cb    = (b == 1) ? "" : ("" + b);

		String correta = "\\(" + coef + "\\sqrt{" + r + "}\\)";
		String e1      = "\\(" + (coef + 1) + "\\sqrt{" + r + "}\\)";
		String e2      = "\\(" + coef + "\\sqrt{" + (r * 2) + "}\\)";
		String e3      = "\\(" + (a * A + b * B) + "\\sqrt{" + r + "}\\)"; // soma em vez de subtração

		addParagrafo("Calcule:");
		addParagrafo("\\(" + ca + "\\sqrt{" + nA + "} - " + cb + "\\sqrt{" + nB + "}\\)");
		embaralharEAdicionarAlternativas(correta, Arrays.asList(e1, e2, e3));
		addResolucao("Simplificar cada radical:");
		addResolucao("\\(\\sqrt{" + nA + "} = \\sqrt{" + (A * A) + " \\times " + r + "} = " + A + "\\sqrt{" + r + "}\\)");
		addResolucao("\\(\\sqrt{" + nB + "} = \\sqrt{" + (B * B) + " \\times " + r + "} = " + B + "\\sqrt{" + r + "}\\)");
		addResolucao("Aplicar coeficientes e subtrair:");
		addResolucao("\\(" + a + " \\cdot " + A + "\\sqrt{" + r + "} - " + b + " \\cdot " + B + "\\sqrt{" + r + "} = (" + (a * A) + " - " + (b * B) + ")\\sqrt{" + r + "} = \\mathbf{" + coef + "\\sqrt{" + r + "}}\\)");
	}
}
