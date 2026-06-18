package matematica.basico.mmcmdc.nivel3package;

import matematica.MMC;
import matematica.GeradorExercicio;
import matematica.basico.mmcmdc.ResolucaoMmcMdc;

// MDC de três números (terceira variação de faixa)
public class MmcMdc17 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int divisorComum, p, q, r;
		do
		{
			divisorComum = 6 + rand.nextInt(12); // 6..17
			p = 2 + rand.nextInt(8);             // 2..9
			q = 2 + rand.nextInt(8);
			r = 2 + rand.nextInt(8);
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
