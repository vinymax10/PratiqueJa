package matematica.basico.mmcmdc.nivel1package;

import matematica.MMC;
import matematica.GeradorExercicio;
import matematica.basico.mmcmdc.ResolucaoMmcMdc;

// Menor múltiplo comum de dois números (enunciado conceitual)
public class MmcMdc7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b;
		do
		{
			a = 2 + rand.nextInt(16); // 2..17
			b = 2 + rand.nextInt(16);
		}
		while(a == b || MMC.mmc(a, b) > 180);

		addParagrafo("Qual é o menor número que é múltiplo de \\(" + a + "\\) e de \\(" + b + "\\) ao mesmo tempo?");
		gerarAlternativasInteiras((int) MMC.mmc(a, b));
		addResolucao("\\(" + ResolucaoMmcMdc.mmc(a, b) + "\\)");
	}
}
