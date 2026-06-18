package matematica.avancado.funcaoexponencial.nivel3package;

import matematica.GeradorExercicio;
import matematica.Racional;

public class Expressao8 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// a^(m1*x + c1) = a^(m2*x + c2), resultado fracionário
		// (m1-m2)*x = c2-c1 => x = k/diff com diff > 1 e k não divisível por diff
		int a  = rand.nextBoolean() ? 2 : 3;
		int m1 = 3 + rand.nextInt(2);  // 3 ou 4 => diff sempre >= 2
		int m2 = 1;
		int diff = m1 - m2;             // 2 ou 3
		// Escolher k não divisível por diff, para garantir resultado fracionário
		int k;
		do { k = 1 + rand.nextInt(7); } while (k % diff == 0);
		int c1 = 1 + rand.nextInt(3);
		int c2 = c1 + k;                // c2 - c1 = k

		Racional resultado = new Racional(k, diff);
		resultado.fatoracao(2);

		String expo1 = expoStr(m1, c1);
		String expo2 = expoStr(m2, c2);

		addParagrafo("Resolva a equação \\(" + a + "^{" + expo1 + "} = " + a + "^{" + expo2 + "}\\).");

		String res = "As bases são iguais, então igualamos os expoentes: \\(\\\\\\)";
		res += "\\(" + expo1 + " = " + expo2 + "\\\\";
		res += "" + m1 + "x - " + m2 + "x = " + c2 + " - " + c1 + "\\\\";
		res += "" + diff + "x = " + k + "\\\\";
		res += "x = \\dfrac{" + k + "}{" + diff + "} = \\mathbf{" + resultado.toStringLatex() + "}\\)";

		gerarAlternativas(resultado);
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
