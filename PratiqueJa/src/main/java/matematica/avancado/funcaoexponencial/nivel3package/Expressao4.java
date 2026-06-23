package matematica.avancado.funcaoexponencial.nivel3package;

import matematica.GeradorExercicio;

public class Expressao4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Equação: a^(2x+1) = a^(x+5) com base que exige conversão
		// Forma: (p^m)^x = p^n  =>  p^(mx) = p^n  =>  x = n/m (fracionário)
		// Variante: expoente negativo  p^x = (1/p)^n = p^(-n)  =>  x = -n
		int tipo = rand.nextInt(3);
		switch (tipo)
		{
			case 0: conversaoNegativa(); break;
			case 1: crescimentoBase3();  break;
			default: crescimentoBase4(); break;
		}
	}

	private void conversaoNegativa()
	{
		// (1/a)^x = a^n  =>  a^(-x) = a^n  =>  x = -n
		int a = rand.nextBoolean() ? 2 : 3;
		int n = 2 + rand.nextInt(4); // 2..5
		int aPowN = (int) Math.pow(a, n);

		addParagrafo("Resolva a equação \\(\\left(\\dfrac{1}{" + a + "}\\right)^x = " + aPowN + "\\).");

		gerarAlternativas("" + (-n));
		addResolucao("Reescrevemos: \\(\\dfrac{1}{" + a + "} = " + a + "^{-1}\\), logo:");
		addResolucao("\\(\\left(" + a + "^{-1}\\right)^x = " + a + "^{" + n + "}\\)");
		addResolucao("\\(" + a + "^{-x} = " + a + "^{" + n + "}\\)");
		addResolucao("\\(-x = " + n + "\\)");
		addResolucao("\\(x = \\mathbf{" + (-n) + "}\\)");
	}

	private void crescimentoBase3()
	{
		// N(t) = N0 * 3^t (triplica a cada período)
		int[] n0Opcoes = {100, 200, 500};
		int n0 = n0Opcoes[rand.nextInt(3)];
		int t  = 2 + rand.nextInt(3); // 2, 3 ou 4
		int resultado = n0 * (int) Math.pow(3, t);

		addParagrafo("Uma população de \\(" + n0 + "\\) organismos triplica a cada dia. "
			+ "Qual será a população após \\(" + t + "\\) dias?");

		gerarAlternativas("" + resultado);
		addResolucao("O modelo de crescimento é \\(P(t) = P_0 \\cdot 3^t\\).");
		addResolucao("\\(P(" + t + ") = " + n0 + " \\cdot 3^{" + t + "} = " + n0 + " \\cdot " + (int) Math.pow(3, t) + "\\)");
		addResolucao("\\(P(" + t + ") = \\mathbf{" + resultado + "}\\) organismos");
	}

	private void crescimentoBase4()
	{
		// N(t) = N0 * 4^t (quadruplica a cada período)
		int[] n0Opcoes = {50, 100, 200};
		int n0 = n0Opcoes[rand.nextInt(3)];
		int t  = 2 + rand.nextInt(2); // 2 ou 3
		int resultado = n0 * (int) Math.pow(4, t);

		addParagrafo("Um vírus quadruplica a cada hora, partindo de \\(" + n0 + "\\) unidades. "
			+ "Quantas unidades haverá após \\(" + t + "\\) horas?");

		gerarAlternativas("" + resultado);
		addResolucao("O modelo de crescimento é \\(N(t) = N_0 \\cdot 4^t\\).");
		addResolucao("\\(N(" + t + ") = " + n0 + " \\cdot 4^{" + t + "} = " + n0 + " \\cdot " + (int) Math.pow(4, t) + "\\)");
		addResolucao("\\(N(" + t + ") = \\mathbf{" + resultado + "}\\) unidades");
	}
}
