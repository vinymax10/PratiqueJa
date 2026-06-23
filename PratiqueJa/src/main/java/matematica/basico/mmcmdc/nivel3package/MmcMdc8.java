package matematica.basico.mmcmdc.nivel3package;

import matematica.MMC;
import matematica.GeradorExercicio;

// Propriedade reversa: dado o produto A×B e o MDC, encontrar o MMC
public class MmcMdc8 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b, d, produto, mmc;
		do
		{
			int[] primos = {2, 3, 5, 7};
			int base = primos[rand.nextInt(4)];
			a = base * (2 + rand.nextInt(8));
			b = base * (2 + rand.nextInt(8));
			d = (int) MMC.mdc(a, b);
			mmc = (int) MMC.mmc(a, b);
			produto = a * b;
		}
		while(a == b || mmc > 500 || d == 1);

		addParagrafo(
			"O produto de dois números é \\(" + produto + "\\) e o MDC deles é \\(" + d + "\\). " +
			"Qual é o MMC desses dois números?"
		);
		gerarAlternativasInteiras(mmc);

		addResolucao("Pela propriedade \\(\\text{MMC} \\times \\text{MDC} = A \\times B\\), isolamos o MMC:");
		addResolucao("\\(\\text{MMC} = \\dfrac{A \\times B}{\\text{MDC}} = \\dfrac{" + produto + "}{" + d + "} = \\mathbf{" + mmc + "}\\)");
	}
}
