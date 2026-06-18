package matematica.intermediario.radiciacao.nivel1package;

import matematica.GeradorExercicio;

public class Radiciacao6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 2 + rand.nextInt(9); // 2..10
		int cubo = a * a * a;

		addParagrafo("Calcule:");
		addParagrafo("\\(\\sqrt[3]{" + cubo + "}\\)");
		gerarAlternativas("" + a);
		setResolucao(
			"\\(\\sqrt[3]{" + cubo + "} = \\sqrt[3]{" + a + "^3} = \\mathbf{" + a + "}\\)"
		);
	}
}
