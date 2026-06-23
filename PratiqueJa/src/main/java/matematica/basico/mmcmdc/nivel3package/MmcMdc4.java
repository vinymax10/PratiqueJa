package matematica.basico.mmcmdc.nivel3package;

import matematica.MMC;
import matematica.GeradorExercicio;
import matematica.basico.mmcmdc.ResolucaoMmcMdc;

// MDC de três números (faixa ampliada)
public class MmcMdc4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int divisorComum, p, q, r;
		do
		{
			divisorComum = 4 + rand.nextInt(11); // 4..14
			p = 2 + rand.nextInt(10);            // 2..11
			q = 2 + rand.nextInt(10);
			r = 2 + rand.nextInt(10);
		}
		while(p == q || q == r || p == r || MMC.mdc(MMC.mdc(p, q), r) != 1);

		int a = divisorComum * p;
		int b = divisorComum * q;
		int c = divisorComum * r;

		addParagrafo("Calcule:");
		addParagrafo("\\(\\text{MDC}(" + a + ",\\," + b + ",\\," + c + ") =\\)");
		gerarAlternativasInteiras((int) MMC.mdc(a, b, c));
		addResolucao("\\(" + ResolucaoMmcMdc.mdc(a, b, c) + "\\)");
	}
}
