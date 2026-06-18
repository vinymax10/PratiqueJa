package matematica.avancado.funcoestrigonometricas.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Exercicio11 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// tg=p/q → cos=q/r usando tg²+1=sec²=1/cos²
		// Triplas: tg=3/4→cos=4/5; tg=5/12→cos=12/13; tg=8/15→cos=15/17
		int[][] triples = {{3, 4, 5}, {5, 12, 13}, {8, 15, 17}};
		int[] t = triples[rand.nextInt(triples.length)];
		int p = t[0], q = t[1], r = t[2];
		int pp = p * p, qq = q * q, rr = r * r;

		addParagrafo("Dado que \\(\\operatorname{tg}\\,\\theta = \\dfrac{" + p + "}{" + q
				+ "}\\) e \\(\\theta\\) está no 1.º quadrante, calcule \\(\\cos\\theta\\).");

		String correta = "\\(\\dfrac{" + q + "}{" + r + "}\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(\\dfrac{" + p + "}{" + r + "}\\)");
		distratores.add("\\(-\\dfrac{" + q + "}{" + r + "}\\)");
		distratores.add("\\(\\dfrac{" + q + "}{" + p + "}\\)");
		embaralharEAdicionarAlternativas(correta, distratores);

		String res = "Usando a identidade \\(\\operatorname{tg}^2\\theta + 1 = \\sec^2\\theta = \\dfrac{1}{\\cos^2\\theta}\\):"
				+ "\\(\\\\\\)"
				+ "\\(\\left(\\dfrac{" + p + "}{" + q + "}\\right)^2 + 1 = \\dfrac{1}{\\cos^2\\theta} \\\\"
				+ "\\dfrac{" + pp + "}{" + qq + "} + \\dfrac{" + qq + "}{" + qq + "} = \\dfrac{1}{\\cos^2\\theta} \\\\"
				+ "\\dfrac{" + rr + "}{" + qq + "} = \\dfrac{1}{\\cos^2\\theta} \\\\"
				+ "\\cos^2\\theta = \\dfrac{" + qq + "}{" + rr + "} \\\\"
				+ "\\cos\\theta = \\mathbf{\\dfrac{" + q + "}{" + r + "}}\\)"
				+ " (positivo: \\(\\theta\\) está no 1.º quadrante)";
		setResolucao(res);
	}
}
