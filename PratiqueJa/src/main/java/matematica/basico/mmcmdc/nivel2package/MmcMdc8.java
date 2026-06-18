package matematica.basico.mmcmdc.nivel2package;

import matematica.MMC;
import matematica.GeradorExercicio;

// Propriedade MMC × MDC = a × b: dado o MMC, calcular o MDC
public class MmcMdc8 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int divisorComum, primeiroFator, segundoFator;
		do
		{
			divisorComum  = 2 + rand.nextInt(9);  // 2..10
			primeiroFator = 2 + rand.nextInt(8);  // 2..9
			segundoFator  = 2 + rand.nextInt(8);
		}
		while(primeiroFator == segundoFator || MMC.mdc(primeiroFator, segundoFator) != 1);

		int a = divisorComum * primeiroFator;
		int b = divisorComum * segundoFator;
		int mmc = a * b / divisorComum;
		int mdc = a * b / mmc;

		addParagrafo("Sabendo que \\(\\text{MMC}(" + a + ",\\," + b + ") = " + mmc + "\\), calcule \\(\\text{MDC}(" + a + ",\\," + b + ")\\).");
		gerarAlternativasInteiras(mdc);

		String res = "Pela propriedade \\(\\text{MMC} \\times \\text{MDC} = a \\times b\\), isolamos o MDC: \\(\\\\\\)";
		res += "\\(\\text{MDC} = \\dfrac{a \\times b}{\\text{MMC}} = \\dfrac{" + a + " \\times " + b + "}{" + mmc + "} = \\\\ ";
		res += "\\dfrac{" + (a * b) + "}{" + mmc + "} = \\mathbf{" + mdc + "}\\)";
		setResolucao(res);
	}
}
