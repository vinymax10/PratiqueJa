package matematica.basico.racionais.nivel3package;

import matematica.GeradorExercicio;

public class Racionais9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// a/b de N = a × N / b — N múltiplo de b para resultado inteiro
		int a = 1 + rand.nextInt(6);   // 1..6
		int b = 2 + rand.nextInt(7);   // 2..8
		while(a >= b) a = 1 + rand.nextInt(6);  // garante fração própria
		int mult = 1 + rand.nextInt(8);          // multiplicador
		int N = b * mult;                        // N é múltiplo de b

		int resultado = a * N / b;  // = a * mult

		addParagrafo("Calcule \\(\\dfrac{" + a + "}{" + b + "}\\) de \\(" + N + "\\):");

		gerarAlternativasInteiras(resultado);

		addResolucao("\\(\\dfrac{a}{b}\\) de \\(N\\) significa \\(\\dfrac{a}{b} \\times N\\).");
		addResolucao("\\(\\dfrac{" + a + "}{" + b + "} \\times " + N + " = \\dfrac{" + a + " \\times "
			 + N + "}{" + b + "} = \\dfrac{" + (a * N) + "}{" + b + "} = " + resultado + "\\)");
	}
}
