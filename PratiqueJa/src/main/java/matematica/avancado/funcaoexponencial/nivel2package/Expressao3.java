package matematica.avancado.funcaoexponencial.nivel2package;

import matematica.GeradorExercicio;

public class Expressao3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// a^(m1*x + c1) = a^(m2*x + c2), resultado inteiro
		// (m1-m2)*x = c2-c1 => x = (c2-c1)/(m1-m2) inteiro
		int a  = rand.nextBoolean() ? 2 : 3;
		int m1 = 2 + rand.nextInt(3);  // 2, 3, 4
		int m2 = 1;
		int x  = 2 + rand.nextInt(5);  // resposta: 2..6
		int c1 = 1 + rand.nextInt(4);  // 1..4
		// (m1-m2)*x = c2-c1  => c2 = c1 + (m1-m2)*x
		int c2 = c1 + (m1 - m2) * x;

		String expo1 = expoStr(m1, c1);
		String expo2 = expoStr(m2, c2);

		addParagrafo("Resolva a equação \\(" + a + "^{" + expo1 + "} = " + a + "^{" + expo2 + "}\\).");

		String res = "As bases são iguais, então igualamos os expoentes: \\(\\\\\\)";
		res += "\\(" + expo1 + " = " + expo2 + "\\\\";
		res += "" + m1 + "x - " + m2 + "x = " + c2 + " - " + c1 + "\\\\";
		res += "" + (m1 - m2) + "x = " + ((m1 - m2) * x) + "\\\\";
		res += "x = \\mathbf{" + x + "}\\)";

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
