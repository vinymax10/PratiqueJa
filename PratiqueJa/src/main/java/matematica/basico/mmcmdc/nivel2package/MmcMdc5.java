package matematica.basico.mmcmdc.nivel2package;

import matematica.MMC;
import matematica.GeradorExercicio;
import matematica.basico.mmcmdc.ResolucaoMmcMdc;

// MMC de três números
public class MmcMdc5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b, c;
		do
		{
			a = 2 + rand.nextInt(14); // 2..15
			b = 2 + rand.nextInt(14);
			c = 2 + rand.nextInt(14);
		}
		while(a == b || b == c || a == c || MMC.mmc(a, b, c) > 400);

		addParagrafo("Calcule:");
		addParagrafo("\\(\\text{MMC}(" + a + ",\\," + b + ",\\," + c + ") =\\)");
		gerarAlternativasInteiras((int) MMC.mmc(a, b, c));
		addResolucao("\\(" + ResolucaoMmcMdc.mmc(a, b, c) + "\\)");
	}
}
