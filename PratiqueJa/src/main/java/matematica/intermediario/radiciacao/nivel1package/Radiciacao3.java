package matematica.intermediario.radiciacao.nivel1package;

import java.util.Arrays;

import matematica.GeradorExercicio;

// Raiz quarta de potência quarta perfeita: √[4]{a⁴} = a
public class Radiciacao3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 2 + rand.nextInt(5); // 2..6
		int n = (int) Math.pow(a, 4);

		addParagrafo("Calcule:");
		addParagrafo("\\(\\sqrt[4]{" + n + "}\\)");
		embaralharEAdicionarAlternativas(
			"" + a,
			Arrays.asList("" + (a + 1), "" + (a * a), "" + (a - 1))
		);
		setResolucao(
			"\\(\\sqrt[4]{" + n + "} = \\sqrt[4]{" + a + "^4} = \\mathbf{" + a + "}\\)"
		);
	}
}
