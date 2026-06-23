package matematica.basico.mmcmdc.nivel1package;

import matematica.MMC;
import matematica.GeradorExercicio;
import matematica.basico.mmcmdc.ResolucaoMmcMdc;

// MMC de dois números (par sem relação de divisibilidade direta)
public class MmcMdc17 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b;
		do
		{
			a = 3 + rand.nextInt(15); // 3..17
			b = 3 + rand.nextInt(15);
		}
		while(a == b || a % b == 0 || b % a == 0 || MMC.mmc(a, b) > 220);

		addParagrafo("Calcule:");
		addParagrafo("\\(\\text{MMC}(" + a + ",\\," + b + ") =\\)");
		gerarAlternativasInteiras((int) MMC.mmc(a, b));
		addResolucao("\\(" + ResolucaoMmcMdc.mmc(a, b) + "\\)");
	}
}
