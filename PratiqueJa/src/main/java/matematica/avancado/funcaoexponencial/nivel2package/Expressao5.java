package matematica.avancado.funcaoexponencial.nivel2package;

import matematica.GeradorExercicio;

public class Expressao5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// M(t) = M0 * (1/2)^k onde k = número de meias-vidas
		int[] m0Opcoes = {200, 400, 800, 1600};
		int m0 = m0Opcoes[rand.nextInt(4)];
		int k  = 2 + rand.nextInt(2); // 2 ou 3 meias-vidas
		int T  = 5 * (1 + rand.nextInt(5)); // meia-vida: 5, 10, 15, 20 ou 25 anos
		int tTotal  = k * T;
		int resultado = m0 / (int) Math.pow(2, k);

		addParagrafo("Uma substância radioativa tem meia-vida de \\(" + T + "\\) anos e massa inicial de "
			+ "\\(" + m0 + "\\,\\text{g}\\). Qual será a massa restante após \\(" + tTotal + "\\) anos?");

		String res = "O tempo corresponde a \\(k = " + tTotal + " \\div " + T + " = " + k + "\\) meias-vidas. \\(\\\\\\)";
		res += "O modelo de decaimento é \\(M = M_0 \\cdot \\left(\\dfrac{1}{2}\\right)^k\\): \\(\\\\\\)";
		res += "\\(M = " + m0 + " \\cdot \\left(\\dfrac{1}{2}\\right)^{" + k + "}";
		res += " = \\dfrac{" + m0 + "}{" + (int) Math.pow(2, k) + "}";
		res += " = \\mathbf{" + resultado + "}\\,\\text{g}\\)";

		gerarAlternativas("" + resultado);
		setResolucao(res);
	}
}
