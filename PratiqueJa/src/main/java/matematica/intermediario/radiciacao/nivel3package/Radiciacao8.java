package matematica.intermediario.radiciacao.nivel3package;

import java.util.Arrays;

import matematica.GeradorExercicio;

// Produto de conjugados: (√a + √b)(√a - √b) = a - b
public class Radiciacao8 extends GeradorExercicio
{
	private static final int[] BASES = {2, 3, 5, 6, 7, 10, 11, 13, 15};

	@Override
	protected void construir()
	{
		int a, b;
		do
		{
			a = BASES[rand.nextInt(BASES.length)];
			b = BASES[rand.nextInt(BASES.length)];
		}
		while(a <= b);

		int res = a - b;

		String e1 = "" + (a + b);
		String e2 = "" + (b - a);
		String e3 = "" + (a * b);
		if(e3.equals("" + res)) e3 = "" + (a + 2);

		addParagrafo("Calcule:");
		addParagrafo("\\((\\sqrt{" + a + "} + \\sqrt{" + b + "})(\\sqrt{" + a + "} - \\sqrt{" + b + "})\\)");
		embaralharEAdicionarAlternativas("" + res, Arrays.asList(e1, e2, e3));
		setResolucao(
			"Produto de conjugados \\((A+B)(A-B) = A^2 - B^2\\):" +
			"\\(\\\\\\)" +
			"\\((\\sqrt{" + a + "})^2 - (\\sqrt{" + b + "})^2 = " + a + " - " + b + " = \\mathbf{" + res + "}\\)"
		);
	}
}
