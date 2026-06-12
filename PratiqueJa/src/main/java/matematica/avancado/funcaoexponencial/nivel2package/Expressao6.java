package matematica.avancado.funcaoexponencial.nivel2package;

import matematica.GeradorExercicio;

public class Expressao6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Equação com base fracionária: (1/a)^x = (1/a)^n → x = n
		int[] bases = {2, 3, 5};
		int a = bases[rand.nextInt(3)];
		int n = 2 + rand.nextInt(4); // 2, 3, 4, 5

		String baseStr = "\\left(\\dfrac{1}{" + a + "}\\right)";

		addParagrafo("Resolva a equação \\(" + baseStr + "^x = " + baseStr + "^{" + n + "}\\).");

		String res = "As bases são iguais \\(\\left(\\dfrac{1}{" + a + "}\\right)\\), então igualamos os expoentes: \\(\\\\\\)";
		res += "\\(x = \\mathbf{" + n + "}\\)";

		gerarAlternativas("" + n);
		setResolucao(res);
	}
}
