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
		// Quando soma e {A,B}={2,3}: coef=5, e3raw=A*B=6==coef+1=e1 → colisão; usar coef+2
		if (e3raw == coef || e3raw == coef + 1) e3raw = coef + 2;

		String op      = soma ? "+" : "-";
		String correta = "\\(" + coef + "\\sqrt{" + r + "}\\)";
		String e1      = "\\(" + (coef + 1) + "\\sqrt{" + r + "}\\)";
		String e2      = "\\(" + coef + "\\sqrt{" + (r * 2) + "}\\)";
		String e3      = "\\(" + e3raw + "\\sqrt{" + r + "}\\)";

		addParagrafo("Calcule:");
		addParagrafo("\\(\\sqrt{" + nA + "} " + op + " \\sqrt{" + nB + "}\\)");
		embaralharEAdicionarAlternativas(correta, Arrays.asList(e1, e2, e3));
		addResolucao("Simplificar cada radical:");
		addResolucao("\\(\\sqrt{" + nA + "} = \\sqrt{" + (A * A) + " \\times " + r + "} = " + A + "\\sqrt{" + r + "}\\)");
		addResolucao("\\(\\sqrt{" + nB + "} = \\sqrt{" + (B * B) + " \\times " + r + "} = " + B + "\\sqrt{" + r + "}\\)");
		addResolucao("Radicais semelhantes:");
		addResolucao("\\(" + A + "\\sqrt{" + r + "} " + op + " " + B + "\\sqrt{" + r + "} = \\mathbf{" + coef + "\\sqrt{" + r + "}}\\)");
	}
}
