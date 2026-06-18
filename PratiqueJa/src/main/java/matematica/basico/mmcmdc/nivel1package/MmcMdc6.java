package matematica.basico.mmcmdc.nivel1package;

import matematica.MMC;
import matematica.GeradorExercicio;
import matematica.basico.mmcmdc.ResolucaoMmcMdc;

// Maior divisor comum de dois números (construído a partir de um fator comum)
public class MmcMdc6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int divisorComum, primeiroFator, segundoFator;
		do
		{
			divisorComum  = 2 + rand.nextInt(9);  // 2..10
			primeiroFator = 2 + rand.nextInt(7);  // 2..8
			segundoFator  = 2 + rand.nextInt(7);
		}
		while(primeiroFator == segundoFator || MMC.mdc(primeiroFator, segundoFator) != 1);

		int a = divisorComum * primeiroFator;
		int b = divisorComum * segundoFator;

		addParagrafo("Qual é o maior divisor comum de \\(" + a + "\\) e \\(" + b + "\\)?");
		gerarAlternativasInteiras((int) MMC.mdc(a, b));
		setResolucao("\\(" + ResolucaoMmcMdc.mdc(a, b) + "\\)");
	}
}
