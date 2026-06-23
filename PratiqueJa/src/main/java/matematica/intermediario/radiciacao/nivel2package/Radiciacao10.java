package matematica.intermediario.radiciacao.nivel2package;

import java.util.Arrays;

import matematica.GeradorExercicio;

// Adição/subtração direta de radicais semelhantes: m√r ± n√r
public class Radiciacao10 extends GeradorExercicio
{
	private static final int[] LIVRES = {2, 3, 5, 6, 7};

	@Override
	protected void construir()
	{
		int r = LIVRES[rand.nextInt(LIVRES.length)];
		int m = 2 + rand.nextInt(8); // 2..9
		int n = 1 + rand.nextInt(7); // 1..7
		boolean soma = rand.nextBoolean();

		// Para subtração: garantir m > n e resultado > 0
		if (!soma && m <= n)
		{
			int tmp = m; m = n; n = tmp;
		}
		if (!soma && m == n)
			m = n + 1 + rand.nextInt(4);

		int coef = soma ? m + n : m - n;

		// Distractors
		int e3raw = soma ? m * n : m + n;
		if (e3raw == coef) e3raw = coef + 2;

		String op       = soma ? "+" : "-";
		String correta  = "\\(" + coef + "\\sqrt{" + r + "}\\)";
		String e1       = "\\(" + (coef + 1) + "\\sqrt{" + r + "}\\)";
		String e2       = "\\(" + coef + "\\sqrt{" + (r * 2) + "}\\)";
		String e3       = "\\(" + e3raw + "\\sqrt{" + r + "}\\)";

		addParagrafo("Calcule:");
		addParagrafo("\\(" + m + "\\sqrt{" + r + "} " + op + " " + n + "\\sqrt{" + r + "}\\)");
		embaralharEAdicionarAlternativas(correta, Arrays.asList(e1, e2, e3));

		String regra = soma ? "Radicais semelhantes: somam-se os coeficientes."
		                    : "Radicais semelhantes: subtraem-se os coeficientes.";
		addResolucao(regra);
		addResolucao("\\(" + m + "\\sqrt{" + r + "} " + op + " " + n + "\\sqrt{" + r + "} = (" + m + " " + op + " " + n + ")\\sqrt{" + r + "}\\)");
		addResolucao("\\(= \\mathbf{" + coef + "\\sqrt{" + r + "}}\\)");
	}
}
