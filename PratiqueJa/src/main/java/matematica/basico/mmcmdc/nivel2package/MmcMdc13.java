package matematica.basico.mmcmdc.nivel2package;

import matematica.MMC;
import matematica.GeradorExercicio;
import matematica.basico.mmcmdc.ResolucaoMmcMdc;

// MDC de três números (faixa maior)
public class MmcMdc13 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int divisorComum, p, q, r;
		do
		{
			divisorComum = 3 + rand.nextInt(9); // 3..11
			p = 2 + rand.nextInt(9);            // 2..10
			q = 2 + rand.nextInt(9);
			r = 2 + rand.nextInt(9);
		}
		while(p == q || q == r || p == r || MMC.mdc(MMC.mdc(p, q), r) != 1);

		int a = divisorComum * p;
		int b = divisorComum * q;
		int c = divisorComum * r;

		addParagrafo("Calcule:");
		addParagrafo("\\(\\text{MDC}(" + a + ",\\," + b + ",\\," + c + ") =\\)");
		gerarAlternativasInteiras((int) MMC.mdc(a, b, c));
		setResolucao("\\(" + ResolucaoMmcMdc.mdc(a, b, c) + "\\)");
	}
}
