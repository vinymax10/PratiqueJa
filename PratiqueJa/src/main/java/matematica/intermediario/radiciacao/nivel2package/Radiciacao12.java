package matematica.intermediario.radiciacao.nivel2package;

import java.util.Arrays;

import matematica.GeradorExercicio;

// Racionalização tipo 1: k/√a = m√a, onde k = m·a
public class Radiciacao12 extends GeradorExercicio
{
	private static final int[] NAO_QUAD = {2, 3, 5, 6, 7, 10, 11};

	@Override
	protected void construir()
	{
		int a = NAO_QUAD[rand.nextInt(NAO_QUAD.length)];
		int m = 2 + rand.nextInt(4); // 2..5
		int k = m * a;               // k/√a = k√a/a = m√a

		String correta = "\\(" + m + "\\sqrt{" + a + "}\\)";
		String e1      = "\\(" + (m + 1) + "\\sqrt{" + a + "}\\)";
		String e2      = "\\(" + m + "\\sqrt{" + (a + 1) + "}\\)";
		String e3      = "\\(\\dfrac{" + k + "\\sqrt{" + a + "}}{" + a + "}\\)"; // não simplificado

		addParagrafo("Racionalize o denominador:");
		addParagrafo("\\(\\dfrac{" + k + "}{\\sqrt{" + a + "}}\\)");
		embaralharEAdicionarAlternativas(correta, Arrays.asList(e1, e2, e3));
		addResolucao("Multiplicar por \\(\\dfrac{\\sqrt{" + a + "}}{\\sqrt{" + a + "}}\\):");
		addResolucao("\\(\\dfrac{" + k + "}{\\sqrt{" + a + "}} \\cdot \\dfrac{\\sqrt{" + a + "}}{\\sqrt{" + a + "}} = \\dfrac{" + k + "\\sqrt{" + a + "}}{" + a + "} = \\mathbf{" + m + "\\sqrt{" + a + "}}\\)");
	}
}
