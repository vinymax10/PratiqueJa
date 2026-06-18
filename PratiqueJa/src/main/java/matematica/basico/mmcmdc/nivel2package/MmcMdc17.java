package matematica.basico.mmcmdc.nivel2package;

import matematica.MMC;
import matematica.GeradorExercicio;
import matematica.basico.mmcmdc.ResolucaoMmcMdc;

// MMC de dois números (terceira variação de faixa)
public class MmcMdc17 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b;
		do
		{
			a = 8 + rand.nextInt(18); // 8..25
			b = 8 + rand.nextInt(18);
		}
		while(a == b || a % b == 0 || b % a == 0 || MMC.mmc(a, b) > 450);

		addParagrafo("Calcule:");
		addParagrafo("\\(\\text{MMC}(" + a + ",\\," + b + ") =\\)");
		gerarAlternativasInteiras((int) MMC.mmc(a, b));
		setResolucao("\\(" + ResolucaoMmcMdc.mmc(a, b) + "\\)");
	}
}
