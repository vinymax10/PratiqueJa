package matematica.basico.mmcmdc.nivel1package;

import matematica.MMC;
import matematica.GeradorExercicio;
import matematica.basico.mmcmdc.ResolucaoMmcMdc;

// MMC de dois números primos entre si (resultado é o produto)
public class MmcMdc12 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b;
		do
		{
			a = 2 + rand.nextInt(9);  // 2..10
			b = 2 + rand.nextInt(9);
		}
		while(a == b || MMC.mdc(a, b) != 1);

		addParagrafo("Calcule o MMC dos números abaixo:");
		addParagrafo("\\(\\text{MMC}(" + a + ",\\," + b + ") =\\)");
		gerarAlternativasInteiras((int) MMC.mmc(a, b));
		addResolucao("\\(" + ResolucaoMmcMdc.mmc(a, b) + "\\)");
	}
}
