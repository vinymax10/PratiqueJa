package matematica.basico.mmcmdc.nivel3package;

import matematica.MMC;
import matematica.GeradorExercicio;
import matematica.basico.mmcmdc.ResolucaoMmcMdc;

// Multi-passo: encontrar o MMC a partir do MDC pela propriedade
public class MmcMdc13 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int divisorComum, primeiroFator, segundoFator;
		do
		{
			divisorComum  = 3 + rand.nextInt(8);  // 3..10
			primeiroFator = 2 + rand.nextInt(8);  // 2..9
			segundoFator  = 2 + rand.nextInt(8);
		}
		while(primeiroFator == segundoFator || MMC.mdc(primeiroFator, segundoFator) != 1);

		int a = divisorComum * primeiroFator;
		int b = divisorComum * segundoFator;
		int mdc = divisorComum;
		int mmc = a * b / mdc;

		addParagrafo("Calcule \\(\\text{MMC}(" + a + ",\\," + b + ")\\) usando a relação entre MMC e MDC.");
		gerarAlternativasInteiras(mmc);

		String res = "Primeiro encontramos o MDC pela fatoração simultânea: \\(\\\\\\)";
		res += "\\(" + ResolucaoMmcMdc.mdc(a, b) + "\\) \\(\\\\\\)";
		res += "Em seguida, pela propriedade \\(\\text{MMC} = \\dfrac{a \\times b}{\\text{MDC}}\\): \\(\\\\\\)";
		res += "\\(\\text{MMC} = \\dfrac{" + a + " \\times " + b + "}{" + mdc + "} = \\dfrac{" + (a * b) + "}{" + mdc + "} = \\mathbf{" + mmc + "}\\)";
		setResolucao(res);
	}
}
