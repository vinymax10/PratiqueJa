package matematica.basico.mmcmdc.nivel3package;

import matematica.MMC;
import matematica.GeradorExercicio;
import matematica.basico.mmcmdc.ResolucaoMmcMdc;

// Multi-passo: calcular MMC e MDC e somar
public class MmcMdc9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b;
		do
		{
			a = 6 + rand.nextInt(24); // 6..29
			b = 6 + rand.nextInt(24);
		}
		while(a == b || MMC.mmc(a, b) > 400);

		int mmc = (int) MMC.mmc(a, b);
		int mdc = (int) MMC.mdc(a, b);
		int resultado = mmc + mdc;

		addParagrafo("Calcule \\(\\text{MMC}(" + a + ",\\," + b + ") + \\text{MDC}(" + a + ",\\," + b + ")\\).");
		gerarAlternativasInteiras(resultado);

		String res = "Calculamos o MMC e o MDC separadamente e somamos. \\(\\\\\\)";
		res += "\\(\\text{MMC}(" + a + ",\\," + b + ")\\): \\(\\\\\\)";
		res += "\\(" + ResolucaoMmcMdc.mmc(a, b) + "\\) \\(\\\\\\)";
		res += "\\(\\text{MDC}(" + a + ",\\," + b + ")\\): \\(\\\\\\)";
		res += "\\(" + ResolucaoMmcMdc.mdc(a, b) + "\\) \\(\\\\\\)";
		res += "\\(\\text{MMC} + \\text{MDC} = " + mmc + " + " + mdc + " = \\mathbf{" + resultado + "}\\)";
		setResolucao(res);
	}
}
