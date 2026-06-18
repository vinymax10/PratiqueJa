package matematica.basico.mmcmdc.nivel2package;

import matematica.MMC;
import matematica.GeradorExercicio;
import matematica.basico.mmcmdc.ResolucaoMmcMdc;

// MDC de dois números (fator comum não trivial, cofatores coprimos)
public class MmcMdc4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int divisorComum, primeiroFator, segundoFator;
		do
		{
			divisorComum  = 3 + rand.nextInt(10); // 3..12
			primeiroFator = 2 + rand.nextInt(9);  // 2..10
			segundoFator  = 2 + rand.nextInt(9);
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
