package matematica.avancado.polinomios.nivel3package;

import java.util.ArrayList;
import java.util.List;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.avancado.polinomios.Polinomio;

// Briot-Ruffini: divide degree-3 polynomial by (x − r), find the quotient
public class Expressao3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int r  = 1 + rand.nextInt(4);
		int qd = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(3));
		int qe = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(3));

		// p(x) = (x − r)(x² + qd·x + qe)
		int B = qd - r;
		int C = qe - r * qd;
		int D = -r * qe;

		// Briot-Ruffini steps (verify)
		int q0 = 1;
		int q1 = q0 * r + B;   // = qd
		int q2 = q1 * r + C;   // = qe
		// resto = q2*r + D = 0

		String poly = Polinomio.formatar(1, B, C, D);
		String quotient = Polinomio.formatar(1, q1, q2);
		String correct = quotient;

		int dq1 = (q1 != 1) ? q1 + 1 : q1 + 2;
		int dq2 = (q2 != 1) ? q2 + 1 : q2 + 2;
		String distr1 = Polinomio.formatar(1, dq1, q2);
		String distr2 = Polinomio.formatar(1, q1, dq2);
		String distr3 = Polinomio.formatar(1, dq1, dq2);

		List<String> distratores = new ArrayList<>();
		distratores.add("\\(" + distr1 + "\\)");
		distratores.add("\\(" + distr2 + "\\)");
		distratores.add("\\(" + distr3 + "\\)");

		addParagrafo("Use o algoritmo de Briot-Ruffini para dividir \\(p(x)\\) por \\((x - " + r
				+ ")\\). Qual o quociente?");
		addParagrafo("\\(p(x) = " + poly + "\\)");
		embaralharEAdicionarAlternativas("\\(" + correct + "\\)", distratores);
		addResolucao("Coeficientes de \\(p(x)\\): \\(1, "
				+ B + ", " + C + ", " + D + "\\) com \\(a = " + r + "\\)");
		addResolucao("Algoritmo de Briot-Ruffini:");
		addResolucao("Descendo o coef. líder: \\(" + q0 + "\\)");
		addResolucao("\\(" + q0 + " \\cdot " + r + Auxiliar.getNumber(B, "", false)
				+ " = " + q1 + "\\)");
		addResolucao("\\(" + q1 + " \\cdot " + r + Auxiliar.getNumber(C, "", false)
				+ " = " + q2 + "\\)");
		addResolucao("Resto: \\(" + q2 + " \\cdot " + r + Auxiliar.getNumber(D, "", false)
				+ " = 0 \\; \\checkmark\\)");
		addResolucao("Quociente: \\(\\mathbf{" + correct + "}\\)");
	}
}
