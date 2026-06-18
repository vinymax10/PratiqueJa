package matematica.basico.mmcmdc.nivel2package;

import matematica.MMC;
import matematica.GeradorExercicio;
import matematica.basico.mmcmdc.ResolucaoMmcMdc;

// MDC de dois números (faixa maior)
public class MmcMdc11 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int divisorComum, primeiroFator, segundoFator;
		do
		{
			divisorComum  = 4 + rand.nextInt(12); // 4..15
			primeiroFator = 2 + rand.nextInt(8);  // 2..9
			segundoFator  = 2 + rand.nextInt(8);
		}
		while(primeiroFator == segundoFator || MMC.mdc(primeiroFator, segundoFator) != 1);

		int a = divisorComum * primeiroFator;
		int b = divisorComum * segundoFator;

		addParagrafo("Qual é o maior número que divide \\(" + a + "\\) e \\(" + b + "\\) ao mesmo tempo?");
		gerarAlternativasInteiras((int) MMC.mdc(a, b));
		setResolucao("\\(" + ResolucaoMmcMdc.mdc(a, b) + "\\)");
	}
}
