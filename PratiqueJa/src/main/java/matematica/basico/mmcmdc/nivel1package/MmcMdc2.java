package matematica.basico.mmcmdc.nivel1package;

import matematica.MMC;
import matematica.GeradorExercicio;
import matematica.basico.mmcmdc.ResolucaoMmcMdc;

public class MmcMdc2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 2 + rand.nextInt(19);
		int b = 2 + rand.nextInt(19);

		while(a == b)
			b = 2 + rand.nextInt(19);

		int fator = 2 + rand.nextInt(9);

		a *= fator;
		b *= fator;

		addParagrafo("Calcule:");
		addParagrafo("\\(\\text{MDC}~ " + a + ", " + b + "=\\)");
		gerarAlternativasInteiras((int) MMC.mdc(a, b));
		setResolucao("\\(" + ResolucaoMmcMdc.mdc(a, b) + "\\)");
	}
}
