package matematica.basico.mmcmdc.nivel3package;

import matematica.MMC;
import matematica.GeradorExercicio;
import matematica.basico.mmcmdc.ResolucaoMmcMdc;

// MMC de três números (terceira variação de faixa)
public class MmcMdc16 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b, c;
		do
		{
			a = 6 + rand.nextInt(18); // 6..23
			b = 6 + rand.nextInt(18);
			c = 6 + rand.nextInt(18);
		}
		while(a == b || b == c || a == c || MMC.mmc(a, b, c) > 900);

		addParagrafo("Calcule:");
		addParagrafo("\\(\\text{MMC}(" + a + ",\\," + b + ",\\," + c + ") =\\)");
		gerarAlternativasInteiras((int) MMC.mmc(a, b, c));
		setResolucao("\\(" + ResolucaoMmcMdc.mmc(a, b, c) + "\\)");
	}
}
