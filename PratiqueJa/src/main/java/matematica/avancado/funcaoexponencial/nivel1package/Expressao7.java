package matematica.avancado.funcaoexponencial.nivel1package;

import matematica.GeradorExercicio;

public class Expressao7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int[] bases = {2, 3, 5};
		int a = bases[rand.nextInt(3)];
		int n = 1 + rand.nextInt(3); // 1, 2, 3
		int resultado = (int) Math.pow(a, n);

		String expo = n == 1 ? "" + a : a + "^{" + n + "}";

		addParagrafo("Dada \\(f(x) = " + a + "^x\\), calcule \\(f(" + n + ")\\).");

		String res = "Substituindo \\(x = " + n + "\\) na função: \\(\\\\\\)";
		res += "\\(f(" + n + ") = " + expo + " = \\mathbf{" + resultado + "}\\)";

		gerarAlternativas("" + resultado);
		setResolucao(res);
	}
}
