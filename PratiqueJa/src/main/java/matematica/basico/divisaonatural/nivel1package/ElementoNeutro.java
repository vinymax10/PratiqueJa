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
				String res0 = "Todo número dividido por \\(1\\) é igual a ele mesmo: \\(\\\\\\)";
				res0 += "\\(" + a + " \\div 1 = \\mathbf{" + a + "}\\)";
				setResolucao(res0);
				break;
			case 1:
				addParagrafo("Qual é o resultado de \\(" + a + " \\div " + a + "\\)?");
				gerarAlternativasInteiras(1);
				String res1 = "Todo número dividido por ele mesmo é igual a \\(1\\) (desde que não seja zero): \\(\\\\\\)";
				res1 += "\\(" + a + " \\div " + a + " = \\mathbf{1}\\)";
				setResolucao(res1);
				break;
			default:
				addParagrafo("Qual é o resultado de \\(0 \\div " + a + "\\)?");
				gerarAlternativasInteiras(0);
				String res2 = "Zero dividido por qualquer número (diferente de zero) é igual a zero: \\(\\\\\\)";
				res2 += "\\(0 \\div " + a + " = \\mathbf{0}\\)";
				setResolucao(res2);
				break;
		}
	}
}
