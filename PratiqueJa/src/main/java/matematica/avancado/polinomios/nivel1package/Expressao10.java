package matematica.avancado.polinomios.nivel1package;

import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;
import matematica.avancado.polinomios.Polinomio;

// Simplify polynomial by combining like terms: a1x² + b1x + a2x² + b2x
public class Expressao10 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a1 = 2 + rand.nextInt(4);
		int a2 = 2 + rand.nextInt(4);
		int b1 = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(4));
		int b2 = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(4));

		while (b1 + b2 == 0)
			b2 = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(4));

		int sumA = a1 + a2;
		int sumB = b1 + b2;

		// Show in mixed order: a1x² + b1x + a2x² + b2x
		String shown = Polinomio.termo(a1, 2, true)
				+ Polinomio.termo(b1, 1, false)
				+ Polinomio.termo(a2, 2, false)
				+ Polinomio.termo(b2, 1, false);

		String correct = Polinomio.termo(sumA, 2, true) + Polinomio.termo(sumB, 1, false);

		String d1 = Polinomio.termo(sumA + 1, 2, true) + Polinomio.termo(sumB, 1, false);
		String d2 = Polinomio.termo(sumA, 2, true) + Polinomio.termo(sumB + 1, 1, false);
		String d3 = Polinomio.termo(sumA - 1 == 0 ? sumA + 2 : sumA - 1, 2, true)
				+ Polinomio.termo(sumB - 1 == 0 ? sumB + 2 : sumB - 1, 1, false);

		List<String> distratores = new ArrayList<>();
		distratores.add("\\(" + d1 + "\\)");
		distratores.add("\\(" + d2 + "\\)");
		distratores.add("\\(" + d3 + "\\)");

		String res = "Agrupando os termos semelhantes:\\(\\\\\\)";
		res += "Termos em \\(x^2\\): \\(" + Polinomio.termo(a1, 2, true) + Polinomio.termo(a2, 2, false)
				+ " = " + Polinomio.termo(sumA, 2, true) + "\\)\\(\\\\\\)";
		res += "Termos em \\(x\\): \\(" + Polinomio.termo(b1, 1, true) + Polinomio.termo(b2, 1, false)
				+ " = " + Polinomio.termo(sumB, 1, true) + "\\)\\(\\\\\\)";
		res += "\\(\\mathbf{" + correct + "}\\)";

		addParagrafo("Simplifique combinando os termos semelhantes.");
		addParagrafo("\\(" + shown + "\\)");
		embaralharEAdicionarAlternativas("\\(" + correct + "\\)", distratores);
		setResolucao(res);
	}
}
