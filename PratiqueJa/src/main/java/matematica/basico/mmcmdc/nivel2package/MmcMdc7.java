package matematica.basico.mmcmdc.nivel2package;

import matematica.MMC;
import matematica.GeradorExercicio;

// Propriedade MMC × MDC = a × b: dado o MDC, calcular o MMC
public class MmcMdc7 extends GeradorExercicio
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
		int mdc = divisorComum;
		int mmc = a * b / mdc;

		addParagrafo("Sabendo que \\(\\text{MDC}(" + a + ",\\," + b + ") = " + mdc + "\\), calcule \\(\\text{MMC}(" + a + ",\\," + b + ")\\).");
		gerarAlternativasInteiras(mmc);

		String res = "Pela propriedade \\(\\text{MMC} \\times \\text{MDC} = a \\times b\\), isolamos o MMC: \\(\\\\\\)";
		res += "\\(\\text{MMC} = \\dfrac{a \\times b}{\\text{MDC}} = \\dfrac{" + a + " \\times " + b + "}{" + mdc + "} = \\\\ ";
		res += "\\dfrac{" + (a * b) + "}{" + mdc + "} = \\mathbf{" + mmc + "}\\)";
		setResolucao(res);
	}
}
