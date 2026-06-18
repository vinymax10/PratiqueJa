package matematica.basico.mmcmdc.nivel3package;

import matematica.MMC;
import matematica.GeradorExercicio;

// Propriedade reversa: dados MDC, MMC e A, encontrar B (segunda variação de faixa)
public class MmcMdc15 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b, d, m;
		do
		{
			int[] primos = {2, 3, 5, 7, 11};
			int base = primos[rand.nextInt(5)];
			a = base * (3 + rand.nextInt(8)); // 3..10 × base
			b = base * (3 + rand.nextInt(8));
			d = (int) MMC.mdc(a, b);
			m = (int) MMC.mmc(a, b);
		}
		while(a == b || m > 600 || d == a || d == b);

		addParagrafo(
			"Sabe-se que \\(\\text{MDC}(A,\\,B) = " + d + "\\), " +
			"\\(\\text{MMC}(A,\\,B) = " + m + "\\) e \\(A = " + a + "\\). " +
			"Calcule \\(B\\):"
		);
		gerarAlternativasInteiras(b);

		String res = "Pela propriedade \\(\\text{MDC} \\times \\text{MMC} = A \\times B\\), isolamos \\(B\\): \\(\\\\\\)";
		res += "\\(B = \\dfrac{\\text{MDC} \\times \\text{MMC}}{A} = \\dfrac{" + d + " \\times " + m + "}{" + a + "} = \\\\ ";
		res += "\\dfrac{" + (d * m) + "}{" + a + "} = \\mathbf{" + b + "}\\)";
		setResolucao(res);
	}
}
