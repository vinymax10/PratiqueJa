package matematica.basico.divisaonatural.nivel1package;

import matematica.GeradorExercicio;

public class ElementoNeutro extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 2 + rand.nextInt(18);

		int tipo = rand.nextInt(3);
		switch (tipo)
		{
			case 0:
				addParagrafo("Qual é o resultado de \\(" + a + " \\div 1\\)?");
				gerarAlternativasInteiras(a);
				addResolucao("Todo número dividido por \\(1\\) é igual a ele mesmo:");
				addResolucao("\\(" + a + " \\div 1 = \\mathbf{" + a + "}\\)");
				break;
			case 1:
				addParagrafo("Qual é o resultado de \\(" + a + " \\div " + a + "\\)?");
				gerarAlternativasInteiras(1);
				addResolucao("Todo número dividido por ele mesmo é igual a \\(1\\) (desde que não seja zero):");
				addResolucao("\\(" + a + " \\div " + a + " = \\mathbf{1}\\)");
				break;
			default:
				addParagrafo("Qual é o resultado de \\(0 \\div " + a + "\\)?");
				gerarAlternativasInteiras(0);
				addResolucao("Zero dividido por qualquer número (diferente de zero) é igual a zero:");
				addResolucao("\\(0 \\div " + a + " = \\mathbf{0}\\)");
				break;
		}
	}
}
