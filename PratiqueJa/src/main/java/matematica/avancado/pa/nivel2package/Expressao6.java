package matematica.avancado.pa.nivel2package;

import matematica.GeradorExercicio;
import matematica.Racional;
import matematica.avancado.pa.ResolucaoPA;

public class Expressao6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int n = 2 * (2 + rand.nextInt(10));
		int a1Val = 1 + rand.nextInt(20);
		int rVal = 1 + rand.nextInt(10);
		Racional a1 = new Racional(a1Val);
		Racional r = new Racional(rVal);
		Racional an = ResolucaoPA.a(a1, r, n);
		Racional sn = ResolucaoPA.soma(a1, an, n);

		int parte1 = 2 * a1Val;
		int parte2 = (n - 1) * rVal;
		int inner = parte1 + parte2;
		int produto = inner * n;

		addParagrafo("Calcule a soma dos " + n + " primeiros termos da PA com \\(a_1 = " + a1Val
				+ "\\) e \\(r = " + rVal + "\\).");
		gerarAlternativas(sn.toString());

		addResolucao("Usando a fórmula alternativa da soma:");
		addResolucao("\\(S_n = \\dfrac{\\left[2a_1 + (n-1) \\cdot r\\right] \\cdot n}{2}\\)");
		addResolucao("\\(S_{" + n + "} = \\dfrac{\\left[2 \\cdot " + a1Val + " + (" + n + " - 1) \\cdot " + rVal
				+ "\\right] \\cdot " + n + "}{2}\\)");
		addResolucao("\\(S_{" + n + "} = \\dfrac{\\left[" + parte1 + " + " + (n - 1) + " \\cdot " + rVal
				+ "\\right] \\cdot " + n + "}{2}\\)");
		addResolucao("\\(S_{" + n + "} = \\dfrac{" + inner + " \\cdot " + n + "}{2} = \\dfrac{" + produto
				+ "}{2} = \\mathbf{" + sn.showDfrac() + "}\\)");
	}
}
