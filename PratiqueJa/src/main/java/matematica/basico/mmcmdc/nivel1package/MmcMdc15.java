package matematica.basico.mmcmdc.nivel1package;

import matematica.MMC;
import matematica.GeradorExercicio;
import matematica.basico.mmcmdc.ResolucaoMmcMdc;

// MDC de três números (construído a partir de um fator comum)
public class MmcMdc15 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int divisorComum, p, q, r;
		do
		{
			divisorComum = 2 + rand.nextInt(8); // 2..9
			p = 2 + rand.nextInt(6);            // 2..7
			q = 2 + rand.nextInt(6);
			r = 2 + rand.nextInt(6);
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
