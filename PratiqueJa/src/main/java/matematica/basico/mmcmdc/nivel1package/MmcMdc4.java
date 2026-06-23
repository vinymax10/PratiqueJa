package matematica.basico.mmcmdc.nivel1package;

import matematica.MMC;
import matematica.GeradorExercicio;
import matematica.basico.mmcmdc.ResolucaoMmcMdc;

public class MmcMdc4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 2 + rand.nextInt(19);
		int b = 2 + rand.nextInt(19);
		int c = 2 + rand.nextInt(19);

		int fator = 2 + rand.nextInt(9);

		a *= fator;
		b *= fator;
		c *= fator;

		addParagrafo("Calcule:");
		addParagrafo("\\(\\text{MDC}~ " + a + ", " + b + ", " + c + "=\\)");
		gerarAlternativasInteiras((int) MMC.mdc(a, b, c));
		addResolucao("\\(" + ResolucaoMmcMdc.mdc(a, b, c) + "\\)");
	}
}
