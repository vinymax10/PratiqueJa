package matematica.intermediario.equacaosegundograu.nivel2package;

import matematica.GeradorExercicio;
import matematica.ParCor;

public class Expressao16 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// n * (n+1) = N  →  n² + n - N = 0
		int n = 2 + rand.nextInt(8);
		int N = n * (n + 1);
		int c = -N;
		int delta = 1 + 4 * N;
		int sqrtDelta = 2 * n + 1;

		String res = "Sejam \\(n\\) e \\(n+1\\) os inteiros consecutivos. \\(\\\\\\)";
		res += "\\(n(n+1)=" + N + "\\)" + "\\(\\\\\\)";
		res += "Expandindo: \\(\\\\\\)";
		res += "\\(n^2+n-" + N + "=0\\)" + "\\(\\\\\\)";
		res += "\\(a=1, \\quad b=1, \\quad c=" + c + "\\)" + "\\(\\\\\\)";
		res += "\\(" + ParCor.formula("x=\\dfrac{-b\\pm\\sqrt{\\Delta}}{2a}") + "\\)" + "\\(\\\\\\)";
		res += "\\(\\Delta=1^2-4\\cdot 1\\cdot(" + c + ")=1+" + (4 * N) + "=" + delta + "\\)" + "\\(\\\\\\)";
		res += "\\(n=\\dfrac{-1\\pm\\sqrt{" + delta + "}}{2}=\\dfrac{-1\\pm " + sqrtDelta + "}{2}\\)" + "\\(\\\\\\)";
		res += "Tomando a solução positiva: \\(\\\\\\)";
		res += "\\(n=\\dfrac{-1+" + sqrtDelta + "}{2}=\\dfrac{" + (sqrtDelta - 1) + "}{2}=\\mathbf{" + n + "}\\)";

		addParagrafo("O produto de dois inteiros positivos consecutivos é \\(" + N + "\\). Encontre o menor deles.");
		gerarAlternativasInteiras(n);
		setResolucao(res);
	}
}
