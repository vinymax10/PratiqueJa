package matematica.basico.mmcmdc.nivel2package;

import matematica.MMC;
import matematica.GeradorExercicio;
import matematica.basico.mmcmdc.ResolucaoMmcMdc;

// MMC de dois números (faixa maior, sem divisibilidade direta)
public class MmcMdc10 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b;
		do
		{
			a = 6 + rand.nextInt(20); // 6..25
			b = 6 + rand.nextInt(20);
		}
		while(a == b || a % b == 0 || b % a == 0 || MMC.mmc(a, b) > 400);

		addParagrafo("Calcule:");
		addParagrafo("\\(\\text{MMC}(" + a + ",\\," + b + ") =\\)");
		gerarAlternativasInteiras((int) MMC.mmc(a, b));
		setResolucao("\\(" + ResolucaoMmcMdc.mmc(a, b) + "\\)");
	}
}
