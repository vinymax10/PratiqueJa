package matematica.basico.mmcmdc.nivel2package;

import matematica.MMC;
import matematica.GeradorExercicio;

// Propriedade MMC × MDC = a × b: calcular diretamente o produto
public class MmcMdc9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b;
		do
		{
			a = 4 + rand.nextInt(16); // 4..19
			b = 4 + rand.nextInt(16);
		}
		while(a == b);

		int mmc = (int) MMC.mmc(a, b);
		int mdc = (int) MMC.mdc(a, b);
		int produto = a * b;

		addParagrafo("Calcule o produto \\(\\text{MMC}(" + a + ",\\," + b + ") \\times \\text{MDC}(" + a + ",\\," + b + ")\\).");
		gerarAlternativasInteiras(produto);

		String res = "Para quaisquer dois números, vale a propriedade \\(\\text{MMC} \\times \\text{MDC} = a \\times b\\). \\(\\\\\\)";
		res += "Logo, basta multiplicar os dois números: \\(\\\\\\)";
		res += "\\(\\text{MMC} \\times \\text{MDC} = " + a + " \\times " + b + " = \\mathbf{" + produto + "}\\)";
		setResolucao(res);
	}
}
