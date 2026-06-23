package matematica.basico.mmcmdc.nivel1package;

import matematica.MMC;
import matematica.GeradorExercicio;
import matematica.basico.mmcmdc.ResolucaoMmcMdc;

public class MmcMdc3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 2 + rand.nextInt(29);
		int b = 2 + rand.nextInt(29);
		int c = 2 + rand.nextInt(29);

		while(a == b)
			b = 2 + rand.nextInt(29);

		while(b == c)
			c = 2 + rand.nextInt(29);

		addParagrafo("Calcule:");
		addParagrafo("\\(\\text{MMC}~ " + a + ", " + b + ", " + c + "=\\)");
		gerarAlternativasInteiras((int) MMC.mmc(a, b, c));
		addResolucao("\\(" + ResolucaoMmcMdc.mmc(a, b, c) + "\\)");
	}
}
