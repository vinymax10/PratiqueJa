package matematica.avancado.funcoestrigonometricas.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Exercicio13 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// cos(180°-θ) = -cos(θ)
		int[][] triples = {{3, 4, 5}, {5, 12, 13}, {8, 15, 17}, {7, 24, 25}};
		int[] t = triples[rand.nextInt(triples.length)];
		int q = t[1], r = t[2];

		addParagrafo("Sabendo que \\(\\cos\\theta = \\dfrac{" + q + "}{" + r
				+ "}\\), calcule \\(\\cos(180° - \\theta)\\).");

		String correta = "\\(-\\dfrac{" + q + "}{" + r + "}\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(\\dfrac{" + q + "}{" + r + "}\\)");
		distratores.add("\\(\\dfrac{" + (r - q) + "}{" + r + "}\\)");
		distratores.add("\\(0\\)");
		embaralharEAdicionarAlternativas(correta, distratores);

		String res = "Usando a propriedade \\(\\cos(180° - \\theta) = -\\cos\\theta\\)"
				+ " (cosseno do suplementar):"
				+ "\\(\\\\\\)"
				+ "\\(\\cos(180° - \\theta) = -\\cos\\theta = \\mathbf{-\\dfrac{" + q + "}{" + r + "}}\\)";
		setResolucao(res);
	}
}
