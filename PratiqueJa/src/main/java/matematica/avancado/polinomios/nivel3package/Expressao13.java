package matematica.avancado.polinomios.nivel3package;

import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;
import matematica.avancado.polinomios.Polinomio;

// Add or subtract two degree-3 polynomials
public class Expressao13 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a1 = 1 + rand.nextInt(3);
		int b1 = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(3));
		int c1 = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(4));
		int d1 = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(4));

		int a2 = 1 + rand.nextInt(3);
		int b2 = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(3));
		int c2 = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(4));
		int d2 = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(4));

		boolean add = rand.nextBoolean();

		int rA = a1 + (add ? a2 : -a2);
		while (rA == 0) { a2 = 1 + rand.nextInt(3); rA = a1 + (add ? a2 : -a2); }
		int rB = b1 + (add ? b2 : -b2);
		int rC = c1 + (add ? c2 : -c2);
		int rD = d1 + (add ? d2 : -d2);

		String p = Polinomio.formatar(a1, b1, c1, d1);
		String q = Polinomio.formatar(a2, b2, c2, d2);
		String op = add ? "+" : "-";
		String correct = Polinomio.formatar(rA, rB, rC, rD);

		int dA = (rA != 1) ? rA + 1 : 2;
		int dB = (rB != -1) ? rB + 1 : rB + 2;
		int dC = (rC != -1) ? rC + 1 : rC + 2;
		String distr1 = Polinomio.formatar(dA, rB, rC, rD);
		String distr2 = Polinomio.formatar(rA, dB, rC, rD);
		String distr3 = Polinomio.formatar(rA, rB, dC, rD);

		List<String> distratores = new ArrayList<>();
		distratores.add("\\(" + distr1 + "\\)");
		distratores.add("\\(" + distr2 + "\\)");
		distratores.add("\\(" + distr3 + "\\)");

		// effective addends for display (op affects sign of second poly's terms)
		int effB2 = add ? b2 : -b2;
		int effC2 = add ? c2 : -c2;
		int effD2 = add ? d2 : -d2;

		String res = "Agrupando os termos semelhantes:\\(\\\\\\)";
		res += "\\(x^3\\): \\(" + sigSum(a1, add ? a2 : -a2) + " = " + rA + "\\)\\(\\\\\\)";
		res += "\\(x^2\\): \\(" + sigSum(b1, effB2) + " = " + rB + "\\)\\(\\\\\\)";
		res += "\\(x\\): \\(" + sigSum(c1, effC2) + " = " + rC + "\\)\\(\\\\\\)";
		res += "Constante: \\(" + sigSum(d1, effD2) + " = " + rD + "\\)\\(\\\\\\)";
		res += "\\(\\mathbf{p(x) " + op + " q(x) = " + correct + "}\\)";

		addParagrafo("Dados \\(p(x) = " + p + "\\) e \\(q(x) = " + q
				+ "\\), calcule \\(p(x) " + op + " q(x)\\).");
		embaralharEAdicionarAlternativas("\\(" + correct + "\\)", distratores);
		setResolucao(res);
	}

	private static String sigSum(int v1, int v2)
	{
		return v2 >= 0 ? v1 + "+" + v2 : v1 + String.valueOf(v2);
	}
}
