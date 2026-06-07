package matematica.basico.mmcmdc.nivel2package;

import matematica.MMC;
import matematica.GeradorExercicio;
import matematica.basico.mmcmdc.ResolucaoMmcMdc;

public class MmcMdc2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b;
		do
		{
			a = 6 + rand.nextInt(25); // 6..30
			b = 6 + rand.nextInt(25);
		}
		while(a == b || a % b == 0 || b % a == 0 || MMC.mmc(a, b) > 400);

		addParagrafo("Calcule:");
		addParagrafo("\\(\\text{MMC}(" + a + ",\\," + b + ") =\\)");
		gerarAlternativasInteiras((int) MMC.mmc(a, b));
		setResolucao("\\(" + ResolucaoMmcMdc.mmc(a, b) + "\\)");
	}
}
