package matematica.avancado.funcoestrigonometricas.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Exercicio7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// 4.º quadrante: cos>0, sen<0 → tg<0
		int[][] triples = {{3, 4, 5}, {5, 12, 13}, {8, 15, 17}, {7, 24, 25}};
		int[] t = triples[rand.nextInt(triples.length)];
		int p = t[0], q = t[1], r = t[2];
		int pp = p * p, qq = q * q, rr = r * r;

		addParagrafo("Dado que \\(\\cos\\theta = \\dfrac{" + q + "}{" + r
				+ "}\\) e \\(\\theta\\) está no 4.º quadrante, calcule \\(\\operatorname{tg}\\,\\theta\\).");

		String correta = "\\(-\\dfrac{" + p + "}{" + q + "}\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(\\dfrac{" + p + "}{" + q + "}\\)");
		distratores.add("\\(-\\dfrac{" + q + "}{" + p + "}\\)");
		distratores.add("\\(\\dfrac{" + p + "}{" + r + "}\\)");
		embaralharEAdicionarAlternativas(correta, distratores);

		addResolucao("Passo 1 — encontrar \\(\\operatorname{sen}\\,\\theta\\) pela identidade fundamental:");
		addResolucao("\\(\\operatorname{sen}^2\\theta + \\cos^2\\theta = 1\\)");
		addResolucao("\\(\\operatorname{sen}^2\\theta + \\dfrac{" + qq + "}{" + rr + "} = 1\\)");
		addResolucao("\\(\\operatorname{sen}^2\\theta = \\dfrac{" + pp + "}{" + rr + "}\\)"
				+ " (\\(\\operatorname{sen}\\theta < 0\\) no 4.º quadrante)");
		addResolucao("\\(\\operatorname{sen}\\,\\theta = -\\dfrac{" + p + "}{" + r + "}\\)");
		addResolucao("Passo 2 — calcular \\(\\operatorname{tg}\\,\\theta\\):");
		addResolucao("\\(\\operatorname{tg}\\,\\theta = \\dfrac{\\operatorname{sen}\\,\\theta}{\\cos\\theta}"
				+ " = \\dfrac{-\\dfrac{" + p + "}{" + r + "}}{\\dfrac{" + q + "}{" + r
				+ "}} = \\mathbf{-\\dfrac{" + p + "}{" + q + "}}\\)"
				+ " (negativo: no 4.º quadrante \\(\\operatorname{tg}\\,\\theta < 0\\))");
	}
}
