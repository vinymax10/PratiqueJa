package matematica.basico.mmcmdc.nivel2package;

import matematica.MMC;
import matematica.GeradorExercicio;
import matematica.basico.mmcmdc.ResolucaoMmcMdc;

// MMC de três números — pedido como "menor número divisível por todos"
public class MmcMdc12 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b, c;
		do
		{
			a = 2 + rand.nextInt(12); // 2..13
			b = 2 + rand.nextInt(12);
			c = 2 + rand.nextInt(12);
		}
		while(a == b || b == c || a == c || MMC.mmc(a, b, c) > 360);

		addParagrafo("Qual é o menor número, diferente de zero, divisível por \\(" + a + "\\), \\(" + b + "\\) e \\(" + c + "\\) ao mesmo tempo?");
		gerarAlternativasInteiras((int) MMC.mmc(a, b, c));
		setResolucao("\\(" + ResolucaoMmcMdc.mmc(a, b, c) + "\\)");
	}
}
