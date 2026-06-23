package matematica.basico.mmcmdc.nivel3package;

import matematica.MMC;
import matematica.GeradorExercicio;
import matematica.basico.mmcmdc.ResolucaoMmcMdc;

// MMC de três números — pedido como menor número divisível por todos
public class MmcMdc11 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b, c;
		do
		{
			a = 5 + rand.nextInt(16); // 5..20
			b = 5 + rand.nextInt(16);
			c = 5 + rand.nextInt(16);
		}
		while(a == b || b == c || a == c || MMC.mmc(a, b, c) > 800);

		addParagrafo("Qual é o menor número, diferente de zero, divisível simultaneamente por \\(" + a + "\\), \\(" + b + "\\) e \\(" + c + "\\)?");
		gerarAlternativasInteiras((int) MMC.mmc(a, b, c));
		addResolucao("\\(" + ResolucaoMmcMdc.mmc(a, b, c) + "\\)");
	}
}
