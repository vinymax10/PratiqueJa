package matematica.basico.subtracaonatural.nivel1package;

import matematica.GeradorExercicio;

public class ElementoNeutro extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 10 + rand.nextInt(50);

		if (rand.nextBoolean())
		{
			addParagrafo("Qual é o valor de \\(" + a + " - 0\\)?");
			gerarAlternativasInteiras(a);

			addResolucao("Subtrair \\(0\\) não altera o valor — \\(0\\) é o elemento neutro da subtração à direita:");
			addResolucao("\\(" + a + " - 0 = \\mathbf{" + a + "}\\)");
		}
		else
		{
			addParagrafo("Qual é o valor de \\(" + a + " - " + a + "\\)?");
			gerarAlternativasInteiras(0);

			addResolucao("Todo número subtraído de si mesmo é igual a zero:");
			addResolucao("\\(" + a + " - " + a + " = \\mathbf{0}\\)");
		}
	}
}
