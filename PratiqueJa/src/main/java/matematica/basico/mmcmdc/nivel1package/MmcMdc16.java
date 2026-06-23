package matematica.basico.mmcmdc.nivel1package;

import matematica.MMC;
import matematica.GeradorExercicio;

// Múltiplos comuns são os múltiplos do MMC: pede o segundo múltiplo comum
public class MmcMdc16 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b;
		do
		{
			a = 2 + rand.nextInt(13); // 2..14
			b = 2 + rand.nextInt(13);
		}
		while(a == b || MMC.mmc(a, b) > 120);

		int mmc = (int) MMC.mmc(a, b);
		int resultado = 2 * mmc;

		addParagrafo("Qual é o segundo menor múltiplo comum positivo de \\(" + a + "\\) e \\(" + b + "\\)?");
		gerarAlternativasInteiras(resultado);

		addResolucao("Os múltiplos comuns de dois números são exatamente os múltiplos do MMC.");
		addResolucao("\\(\\text{MMC}(" + a + ",\\," + b + ") = " + mmc + "\\).");
		addResolucao("O segundo múltiplo comum é \\(2 \\times " + mmc + " = \\mathbf{" + resultado + "}\\).");
	}
}
