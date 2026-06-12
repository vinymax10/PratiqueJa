package matematica.avancado.funcaoexponencial.nivel2package;

import matematica.GeradorExercicio;

public class Expressao4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int[] n0Opcoes = {100, 200, 500, 1000};
		int n0 = n0Opcoes[rand.nextInt(4)];
		int t  = 3 + rand.nextInt(3); // 3, 4 ou 5 horas
		int resultado = n0 * (int) Math.pow(2, t);

		addParagrafo("Uma colônia de bactérias parte de \\(" + n0 + "\\) células e dobra a cada hora. "
			+ "Quantas bactérias haverá após \\(" + t + "\\) horas?");

		String res = "O modelo de crescimento é \\(N(t) = N_0 \\cdot 2^t\\). \\(\\\\\\)";
		res += "Com \\(N_0 = " + n0 + "\\) e \\(t = " + t + "\\): \\(\\\\\\)";
		res += "\\(N(" + t + ") = " + n0 + " \\cdot 2^{" + t + "} = " + n0 + " \\cdot " + (int) Math.pow(2, t) + "\\) \\(\\\\\\)";
		res += "\\(N(" + t + ") = \\mathbf{" + resultado + "}\\) bactérias";

		gerarAlternativas("" + resultado);
		setResolucao(res);
	}
}
