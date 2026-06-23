package matematica.avancado.polinomios.nivel3package;

import java.util.ArrayList;
import java.util.List;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.avancado.polinomios.Polinomio;

// Multiply a binomial by a trinomial: (ax + b)(cx² + dx + e)
public class Expressao2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(2);
		int b = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(3));
		int c = 1 + rand.nextInt(2);
		int d = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(3));
		int e = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(3));

		int coef3 = a * c;
		int coef2 = a * d + b * c;
		int coef1 = a * e + b * d;
		int coef0 = b * e;

		String trinomio = Polinomio.formatar(c, d, e);
		String binomio = Polinomio.formatar(a, b);
		String expr = "(" + binomio + ")(" + trinomio + ")";
		String correct = Polinomio.formatar(coef3, coef2, coef1, coef0);

		int dc2 = (coef2 != 1) ? coef2 + 1 : coef2 + 2;
		int dc1 = (coef1 != 1) ? coef1 + 1 : coef1 + 2;
		String distr1 = Polinomio.formatar(coef3, dc2, coef1, coef0);
		String distr2 = Polinomio.formatar(coef3, coef2, dc1, coef0);
		String distr3 = Polinomio.formatar(coef3, coef2, coef1, coef0 != -1 ? coef0 + 1 : coef0 + 2);

		List<String> distratores = new ArrayList<>();
		distratores.add("\\(" + distr1 + "\\)");
		distratores.add("\\(" + distr2 + "\\)");
		distratores.add("\\(" + distr3 + "\\)");

		// Build intermediate step: group1 (from ax·trinomio) + group2 (from b·trinomio)
		// ax·(cx²+dx+e) = (ac)x³ + (ad)x² + (ae)x
		String group1 = Polinomio.termo(a * c, 3, true)
				+ Polinomio.termo(a * d, 2, false)
				+ Polinomio.termo(a * e, 1, false);
		// b·(cx²+dx+e) = (bc)x² + (bd)x + (be)
		String group2 = Polinomio.termo(b * c, 2, false)
				+ Polinomio.termo(b * d, 1, false)
				+ Auxiliar.getNumber(b * e, "", false);

		String aStr = (a == 1) ? "" : String.valueOf(a);

		addParagrafo("Calcule o produto.");
		addParagrafo("\\(" + expr + "\\)");
		embaralharEAdicionarAlternativas("\\(" + correct + "\\)", distratores);
		addResolucao("Aplicando a propriedade distributiva:");
		addResolucao("\\(" + aStr + "x \\cdot (" + trinomio + ") + (" + b + ") \\cdot (" + trinomio + ")\\)");
		addResolucao("\\(" + group1 + group2 + " = \\mathbf{" + correct + "}\\)");
	}
}
