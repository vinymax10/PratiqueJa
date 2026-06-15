package matematica.avancado.funcoestrigonometricas.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Exercicio6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// 3.º quadrante: sen<0, cos<0 → tg>0
		int[][] triples = {{3, 4, 5}, {5, 12, 13}, {8, 15, 17}, {7, 24, 25}};
		int[] t = triples[rand.nextInt(triples.length)];
		int p = t[0], q = t[1], r = t[2];
		int pp = p * p, qq = q * q, rr = r * r;

		addParagrafo("Dado que \\(\\operatorname{sen}\\,\\theta = -\\dfrac{" + p + "}{" + r
				+ "}\\) e \\(\\theta\\) está no 3.º quadrante, calcule \\(\\operatorname{tg}\\,\\theta\\).");

		String correta = "\\(\\dfrac{" + p + "}{" + q + "}\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(-\\dfrac{" + p + "}{" + q + "}\\)");
		distratores.add("\\(\\dfrac{" + q + "}{" + p + "}\\)");
		distratores.add("\\(-\\dfrac{" + p + "}{" + r + "}\\)");
		embaralharEAdicionarAlternativas(correta, distratores);

		String res = "Passo 1 — encontrar \\(\\cos\\theta\\) pela identidade fundamental:"
				+ "\\(\\\\\\)"
				+ "\\(\\operatorname{sen}^2\\theta + \\cos^2\\theta = 1 \\\\"
				+ "\\dfrac{" + pp + "}{" + rr + "} + \\cos^2\\theta = 1 \\\\"
				+ "\\cos^2\\theta = \\dfrac{" + qq + "}{" + rr + "}\\)"
				+ " (\\(\\cos\\theta < 0\\) no 3.º quadrante)"
				+ "\\(\\\\\\)"
				+ "\\(\\cos\\theta = -\\dfrac{" + q + "}{" + r + "}\\)"
				+ "\\(\\\\\\)"
				+ "Passo 2 — calcular \\(\\operatorname{tg}\\,\\theta\\):"
				+ "\\(\\\\\\)"
				+ "\\(\\operatorname{tg}\\,\\theta = \\dfrac{\\operatorname{sen}\\,\\theta}{\\cos\\theta}"
				+ " = \\dfrac{-\\dfrac{" + p + "}{" + r + "}}{-\\dfrac{" + q + "}{" + r
				+ "}} = \\mathbf{\\dfrac{" + p + "}{" + q + "}}\\)"
				+ " (positivo: no 3.º quadrante \\(\\operatorname{tg}\\,\\theta > 0\\))";
		setResolucao(res);
	}
}
