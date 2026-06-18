package matematica.basico.mmcmdc.nivel1package;

import matematica.MMC;
import matematica.GeradorExercicio;
import matematica.basico.mmcmdc.ResolucaoMmcMdc;

// MMC de dois números (cálculo direto pela fatoração simultânea)
public class MmcMdc5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b;
		do
		{
			a = 2 + rand.nextInt(18); // 2..19
			b = 2 + rand.nextInt(18);
		}
		while(a == b || MMC.mmc(a, b) > 200);

		addParagrafo("Calcule:");
		addParagrafo("\\(\\text{MMC}(" + a + ",\\," + b + ") =\\)");
		gerarAlternativasInteiras((int) MMC.mmc(a, b));
		setResolucao("\\(" + ResolucaoMmcMdc.mmc(a, b) + "\\)");
	}
}
