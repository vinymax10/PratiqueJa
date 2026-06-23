package matematica.basico.adicaonatural.nivel1package;

import matematica.GeradorExercicio;

public class ElementoNeutro extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 10 + rand.nextInt(90);
		boolean zeroEsquerda = rand.nextBoolean();

		if (zeroEsquerda)
			addParagrafo("Qual é o valor de \\(0 + " + a + "\\)?");
		else
			addParagrafo("Qual é o valor de \\(" + a + " + 0\\)?");

		gerarAlternativasInteiras(a);

		addResolucao("O número \\(0\\) é o elemento neutro da adição: somar \\(0\\) não altera o valor.");
		if (zeroEsquerda)
			addResolucao("\\(0 + " + a + " = \\mathbf{" + a + "}\\)");
		else
			addResolucao("\\(" + a + " + 0 = \\mathbf{" + a + "}\\)");
	}
}
