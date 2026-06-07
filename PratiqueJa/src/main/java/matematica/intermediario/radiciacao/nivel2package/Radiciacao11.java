package matematica.intermediario.radiciacao.nivel2package;

import java.util.Arrays;

import matematica.GeradorExercicio;

// Adição/subtração via simplificação: √(A²r) ± √(B²r) = (A±B)√r
public class Radiciacao11 extends GeradorExercicio
{
	private static final int[] LIVRES = {2, 3, 5, 6, 7};

	@Override
	protected void construir()
	{
		int r, A, B;
		do
		{
			r = LIVRES[rand.nextInt(LIVRES.length)];
			A = 2 + rand.nextInt(5); // 2..6
			B = 2 + rand.nextInt(5); // 2..6
		}
		while (A == B || A * A * r > 400 || B * B * r > 400);

		boolean soma = rand.nextBoolean();
		if (!soma && A < B)
		{
			int tmp = A; A = B; B = tmp;
		}

		int nA   = A * A * r;
		int nB   = B * B * r;
		int coef = soma ? A + B : A - B;

		int e3raw = soma ? A * B : A + B;
		if (e3raw == coef) e3raw = coef + 2;

		String op      = soma ? "+" : "-";
		String correta = "\\(" + coef + "\\sqrt{" + r + "}\\)";
		String e1      = "\\(" + (coef + 1) + "\\sqrt{" + r + "}\\)";
		String e2      = "\\(" + coef + "\\sqrt{" + (r * 2) + "}\\)";
		String e3      = "\\(" + e3raw + "\\sqrt{" + r + "}\\)";

		addParagrafo("Calcule:");
		addParagrafo("\\(\\sqrt{" + nA + "} " + op + " \\sqrt{" + nB + "}\\)");
		embaralharEAdicionarAlternativas(correta, Arrays.asList(e1, e2, e3));
		setResolucao(
			"Simplificar cada radical:" +
			"\\(\\\\\\)" +
			"\\(\\sqrt{" + nA + "} = \\sqrt{" + (A * A) + " \\times " + r + "} = " + A + "\\sqrt{" + r + "}\\)" +
			"\\(\\\\\\)" +
			"\\(\\sqrt{" + nB + "} = \\sqrt{" + (B * B) + " \\times " + r + "} = " + B + "\\sqrt{" + r + "}\\)" +
			"\\(\\\\\\)" +
			"Radicais semelhantes:" +
			"\\(\\\\\\)" +
			"\\(" + A + "\\sqrt{" + r + "} " + op + " " + B + "\\sqrt{" + r + "} = \\mathbf{" + coef + "\\sqrt{" + r + "}}\\)"
		);
	}
}
