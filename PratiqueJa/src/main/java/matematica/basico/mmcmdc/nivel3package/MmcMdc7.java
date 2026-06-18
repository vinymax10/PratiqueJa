package matematica.basico.mmcmdc.nivel3package;

import matematica.MMC;
import matematica.GeradorExercicio;

// Propriedade reversa: dados MDC, MMC e B, encontrar A
public class MmcMdc7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b, d, m;
		do
		{
			int[] primos = {2, 3, 5, 7};
			int base = primos[rand.nextInt(4)];
			a = base * (2 + rand.nextInt(8)); // 2..9 × base
			b = base * (2 + rand.nextInt(8));
			d = (int) MMC.mdc(a, b);
			m = (int) MMC.mmc(a, b);
		}
		while(a == b || m > 500 || d == a || d == b);

		addParagrafo(
			"Sabe-se que \\(\\text{MDC}(A,\\,B) = " + d + "\\), " +
			"\\(\\text{MMC}(A,\\,B) = " + m + "\\) e \\(B = " + b + "\\). " +
			"Calcule \\(A\\):"
		);
		gerarAlternativasInteiras(a);

		String res = "Pela propriedade \\(\\text{MDC} \\times \\text{MMC} = A \\times B\\), isolamos \\(A\\): \\(\\\\\\)";
		res += "\\(A = \\dfrac{\\text{MDC} \\times \\text{MMC}}{B} = \\dfrac{" + d + " \\times " + m + "}{" + b + "} = \\\\ ";
		res += "\\dfrac{" + (d * m) + "}{" + b + "} = \\mathbf{" + a + "}\\)";
		setResolucao(res);
	}
}
