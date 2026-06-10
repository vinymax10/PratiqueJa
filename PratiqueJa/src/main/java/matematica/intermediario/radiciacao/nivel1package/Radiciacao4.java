package matematica.intermediario.radiciacao.nivel1package;

import java.util.Arrays;

import matematica.GeradorExercicio;

// Raiz quadrada de fração quadrada perfeita: √(p²/q²) = p/q
public class Radiciacao4 extends GeradorExercicio
{
	private static final int[][] PARES = {
		{1,2},{1,3},{2,3},{1,4},{3,4},{1,5},{2,5},{3,5},{4,5},{1,6},{5,6},{2,7},{3,7},{4,7}
	};

	@Override
	protected void construir()
	{
		int[] par = PARES[rand.nextInt(PARES.length)];
		int p = par[0], q = par[1];
		int pp = p * p, qq = q * q;

		String correta = "\\(\\dfrac{" + p + "}{" + q + "}\\)";
		String e1      = "\\(\\dfrac{" + (p + 1) + "}{" + q + "}\\)";
		String e2      = "\\(\\dfrac{" + p + "}{" + (q + 1) + "}\\)";
		String e3      = "\\(\\dfrac{" + q + "}{" + p + "}\\)";

		addParagrafo("Calcule:");
		addParagrafo("\\(\\sqrt{\\dfrac{" + pp + "}{" + qq + "}}\\)");
		embaralharEAdicionarAlternativas(correta, Arrays.asList(e1, e2, e3));
		setResolucao(
			"\\(\\sqrt{\\dfrac{" + pp + "}{" + qq + "}} = \\dfrac{\\sqrt{" + pp + "}}{\\sqrt{" + qq + "}} = \\dfrac{\\sqrt{" + p + "^2}}{\\sqrt{" + q + "^2}} = \\mathbf{\\dfrac{" + p + "}{" + q + "}}\\)"
		);
	}
}
