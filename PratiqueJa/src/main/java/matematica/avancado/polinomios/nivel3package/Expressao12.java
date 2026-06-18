package matematica.avancado.polinomios.nivel3package;

import java.util.ArrayList;
import java.util.List;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.avancado.polinomios.Polinomio;

// Complete factorization of a degree-3 polynomial with three positive integer roots
public class Expressao12 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int r1 = 1 + rand.nextInt(5);
		int r2 = r1 + 1 + rand.nextInt(3);
		int r3 = r2 + 1 + rand.nextInt(3);

		// p(x) = (x−r1)(x−r2)(x−r3) = x³ − (r1+r2+r3)x² + (r1r2+r1r3+r2r3)x − r1r2r3
		int B = -(r1 + r2 + r3);
		int C = r1 * r2 + r1 * r3 + r2 * r3;
		int D = -r1 * r2 * r3;

		// Briot-Ruffini dividing by (x − r1) gives quotient x² − (r2+r3)x + r2r3
		int q1 = -(r2 + r3);
		int q2 = r2 * r3;

		String poly = Polinomio.formatar(1, B, C, D);
		String correct = "(x - " + r1 + ")(x - " + r2 + ")(x - " + r3 + ")";

		// d1: wrong sign on one root
		String d1 = "(x - " + r1 + ")(x + " + r2 + ")(x - " + r3 + ")";
		// d2: off-by-one on last root
		String d2 = "(x - " + r1 + ")(x - " + r2 + ")(x - " + (r3 + 1) + ")";
		// d3: wrong first root sign
		String d3 = "(x + " + r1 + ")(x - " + r2 + ")(x - " + r3 + ")";

		List<String> distratores = new ArrayList<>();
		distratores.add("\\(" + d1 + "\\)");
		distratores.add("\\(" + d2 + "\\)");
		distratores.add("\\(" + d3 + "\\)");

		String quotient = Polinomio.formatar(1, q1, q2);

		String res = "Como \\(x = " + r1 + "\\) é raiz, \\((x - " + r1
				+ ")\\) é fator. Aplicando Briot-Ruffini:\\(\\\\\\)";
		res += "Coeficientes: \\(1, " + B + ", " + C + ", " + D
				+ "\\) com \\(a = " + r1 + "\\)\\(\\\\\\)";
		res += "\\(1 \\cdot " + r1 + Auxiliar.getNumber(B, "", false) + " = " + q1
				+ "\\)\\(\\\\\\)";
		res += "\\(" + q1 + " \\cdot " + r1 + Auxiliar.getNumber(C, "", false) + " = " + q2
				+ "\\)\\(\\\\\\)";
		res += "Quociente: \\(" + quotient + "\\)\\(\\\\\\)";
		res += "Fatorando o quociente: \\(" + quotient + " = (x - " + r2 + ")(x - " + r3
				+ ")\\)\\(\\\\\\)";
		res += "\\(\\mathbf{p(x) = " + correct + "}\\)";

		addParagrafo("Sabendo que \\(x = " + r1 + "\\) é raiz, fatore completamente \\(p(x)\\).");
		addParagrafo("\\(p(x) = " + poly + "\\)");
		embaralharEAdicionarAlternativas("\\(" + correct + "\\)", distratores);
		setResolucao(res);
	}
}
