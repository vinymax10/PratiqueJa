package matematica.avancado.funcaoexponencial.nivel2package;

import matematica.GeradorExercicio;

public class Expressao7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = rand.nextBoolean() ? 2 : 3;
		int b = 2 + rand.nextInt(2);            // coef. de x: 2 ou 3
		int c = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(3)); // ±1..3
		int x = 1 + rand.nextInt(4);            // resposta inteira 1..4
		int d = b * x + c;                      // expoente do lado direito

		String expo = expoStr(b, c);

		addParagrafo("Resolva a equação \\(" + a + "^{" + expo + "} = " + a + "^{" + d + "}\\).");

		int dc = d - c;
		String res = "As bases são iguais, então igualamos os expoentes: \\(\\\\\\)";
		res += "\\(" + expo + " = " + d + "\\\\";
		res += "" + b + "x = " + d + " - (" + c + ")\\\\";
		res += "" + b + "x = " + dc + "\\\\";
		res += "x = \\dfrac{" + dc + "}{" + b + "} = \\mathbf{" + x + "}\\)";

		gerarAlternativas("" + x);
		setResolucao(res);
	}

	private static String expoStr(int m, int c)
	{
		String s = (m == 1 ? "" : "" + m) + "x";
		if (c > 0) return s + " + " + c;
		if (c < 0) return s + " - " + (-c);
		return s;
	}
}
