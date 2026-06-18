package matematica.basico.mmcmdc.nivel1package;

import matematica.MMC;
import matematica.GeradorExercicio;
import matematica.basico.mmcmdc.ResolucaoMmcMdc;

// MDC de dois números (com fator comum não trivial e cofatores coprimos)
public class MmcMdc18 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int divisorComum, primeiroFator, segundoFator;
		do
		{
			divisorComum  = 3 + rand.nextInt(9);  // 3..11
			primeiroFator = 2 + rand.nextInt(8);  // 2..9
			segundoFator  = 2 + rand.nextInt(8);
		}
		while(primeiroFator == segundoFator || MMC.mdc(primeiroFator, segundoFator) != 1);

		int a = divisorComum * primeiroFator;
		int b = divisorComum * segundoFator;

		addParagrafo("Calcule:");
		addParagrafo("\\(\\text{MDC}(" + a + ",\\," + b + ") =\\)");
		gerarAlternativasInteiras((int) MMC.mdc(a, b));
		setResolucao("\\(" + ResolucaoMmcMdc.mdc(a, b) + "\\)");
	}
}
