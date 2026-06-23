package matematica.basico.mmcmdc.nivel3package;

import matematica.MMC;
import matematica.GeradorExercicio;
import matematica.basico.mmcmdc.ResolucaoMmcMdc;

// MDC de três números — pedido como maior divisor comum dos três
public class MmcMdc12 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int divisorComum, p, q, r;
		do
		{
			divisorComum = 5 + rand.nextInt(12); // 5..16
			p = 2 + rand.nextInt(9);             // 2..10
			q = 2 + rand.nextInt(9);
			r = 2 + rand.nextInt(9);
		}
		while(p == q || q == r || p == r || MMC.mdc(MMC.mdc(p, q), r) != 1);

		int a = divisorComum * p;
		int b = divisorComum * q;
		int c = divisorComum * r;

		addParagrafo("Qual é o maior número que divide \\(" + a + "\\), \\(" + b + "\\) e \\(" + c + "\\) ao mesmo tempo?");
		gerarAlternativasInteiras((int) MMC.mdc(a, b, c));
		addResolucao("\\(" + ResolucaoMmcMdc.mdc(a, b, c) + "\\)");
	}
}
