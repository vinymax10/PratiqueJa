package matematica.basico.mmcmdc.nivel1package;

import matematica.MMC;
import matematica.GeradorExercicio;
import matematica.basico.mmcmdc.ResolucaoMmcMdc;

// MDC de dois números primos entre si (resultado é 1)
public class MmcMdc13 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b;
		do
		{
			a = 2 + rand.nextInt(13);  // 2..14
			b = 2 + rand.nextInt(13);
		}
		while(a == b || MMC.mdc(a, b) != 1);

		addParagrafo("Qual é o máximo divisor comum (MDC) de \\(" + a + "\\) e \\(" + b + "\\)?");
		gerarAlternativasInteiras((int) MMC.mdc(a, b));
		setResolucao("\\(" + ResolucaoMmcMdc.mdc(a, b) + "\\)");
	}
}
