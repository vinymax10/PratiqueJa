package matematica.basico.mmcmdc.nivel3package;

import matematica.MMC;
import matematica.GeradorExercicio;

// Propriedade: dados MDC e MMC, encontrar o produto dos dois números
public class MmcMdc18 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b, d, m;
		do
		{
			int[] primos = {2, 3, 5, 7};
			int base = primos[rand.nextInt(4)];
			a = base * (2 + rand.nextInt(8));
			b = base * (2 + rand.nextInt(8));
			d = (int) MMC.mdc(a, b);
			m = (int) MMC.mmc(a, b);
		}
		while(a == b || m > 500 || d == 1);

		int produto = d * m;

		addParagrafo("Dois números têm \\(\\text{MDC} = " + d + "\\) e \\(\\text{MMC} = " + m + "\\). Qual é o produto desses dois números?");
		gerarAlternativasInteiras(produto);

		addResolucao("Pela propriedade \\(\\text{MMC} \\times \\text{MDC} = a \\times b\\), o produto dos números é igual ao produto do MMC pelo MDC:");
		addResolucao("\\(a \\times b = \\text{MMC} \\times \\text{MDC} = " + m + " \\times " + d + " = \\mathbf{" + produto + "}\\)");
	}
}
