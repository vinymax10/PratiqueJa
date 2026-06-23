package matematica.avancado.funcaoexponencial.nivel1package;

import matematica.GeradorExercicio;

public class Expressao4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int[] bases = {2, 3, 5};
		int a = bases[rand.nextInt(3)];
		int n = 2 + rand.nextInt(4); // 2, 3, 4, 5
		int aPowN = (int) Math.pow(a, n);

		addParagrafo("Resolva a equação \\(" + a + "^x = " + aPowN + "\\).");

		gerarAlternativas("" + n);
		addResolucao("Reescrevemos \\(" + aPowN + "\\) como potência de base \\(" + a + "\\):");
		addResolucao("\\(" + aPowN + " = " + a + "^{" + n + "}\\)");
		addResolucao("Igualando bases: \\(" + a + "^x = " + a + "^{" + n + "}\\)");
		addResolucao("\\(x = \\mathbf{" + n + "}\\)");
	}
}
