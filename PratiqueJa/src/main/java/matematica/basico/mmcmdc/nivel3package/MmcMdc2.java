package matematica.basico.mmcmdc.nivel3package;

import matematica.MMC;
import matematica.GeradorExercicio;

// Dado MDC(A,B) e MMC(A,B) e A, encontre B usando MDC × MMC = A × B
public class MmcMdc2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b, d, m;
		do
		{
			int[] primos = {2, 3, 5, 7};
			int base = primos[rand.nextInt(4)];
			a = base * (2 + rand.nextInt(7)); // 2..8 × base
			b = base * (2 + rand.nextInt(7));
			d = (int) MMC.mdc(a, b);
			m = (int) MMC.mmc(a, b);
		}
		while(a == b || m > 400 || d == a || d == b);

		addParagrafo(
			"Sabe-se que \\(\\text{MDC}(A,\\,B) = " + d + "\\), " +
			"\\(\\text{MMC}(A,\\,B) = " + m + "\\) e \\(A = " + a + "\\). " +
			"Calcule \\(B\\):"
		);
		gerarAlternativasInteiras(b);

		addResolucao(
			"\\(\\begin{aligned}" +
			"& \\text{MDC} \\times \\text{MMC} = A \\times B\\\\" +
			"& " + d + " \\times " + m + " = " + a + " \\times B\\\\" +
			"& B = \\dfrac{" + (d * m) + "}{" + a + "} = \\mathbf{" + b + "}" +
			"\\end{aligned}\\)"
		);
	}
}
