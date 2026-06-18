package matematica.basico.mmcmdc.nivel3package;

import matematica.MMC;
import matematica.GeradorExercicio;
import matematica.basico.mmcmdc.ResolucaoMmcMdc;

// MMC de três números (faixa ampliada)
public class MmcMdc3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b, c;
		do
		{
			a = 4 + rand.nextInt(16); // 4..19
			b = 4 + rand.nextInt(16);
			c = 4 + rand.nextInt(16);
		}
		while(a == b || b == c || a == c || MMC.mmc(a, b, c) > 700);

		addParagrafo("Calcule:");
		addParagrafo("\\(\\text{MMC}(" + a + ",\\," + b + ",\\," + c + ") =\\)");
		gerarAlternativasInteiras((int) MMC.mmc(a, b, c));
		setResolucao("\\(" + ResolucaoMmcMdc.mmc(a, b, c) + "\\)");
	}
}
