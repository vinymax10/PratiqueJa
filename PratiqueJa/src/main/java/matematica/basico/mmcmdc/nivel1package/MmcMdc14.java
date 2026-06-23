package matematica.basico.mmcmdc.nivel1package;

import matematica.MMC;
import matematica.GeradorExercicio;
import matematica.basico.mmcmdc.ResolucaoMmcMdc;

// MMC de três números pequenos
public class MmcMdc14 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b, c;
		do
		{
			a = 2 + rand.nextInt(10); // 2..11
			b = 2 + rand.nextInt(10);
			c = 2 + rand.nextInt(10);
		}
		while(a == b || b == c || a == c || MMC.mmc(a, b, c) > 300);

		addParagrafo("Calcule:");
		addParagrafo("\\(\\text{MMC}(" + a + ",\\," + b + ",\\," + c + ") =\\)");
		gerarAlternativasInteiras((int) MMC.mmc(a, b, c));
		addResolucao("\\(" + ResolucaoMmcMdc.mmc(a, b, c) + "\\)");
	}
}
