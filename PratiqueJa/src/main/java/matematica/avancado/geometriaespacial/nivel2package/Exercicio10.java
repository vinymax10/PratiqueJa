package matematica.avancado.geometriaespacial.nivel2package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Exercicio10 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int r    = 2 + rand.nextInt(5); // 2..6
		int h    = 2 + rand.nextInt(7); // 2..8
		int coef = 2 * r * h;

		addParagrafo("Calcule a área lateral de um cilindro com raio \\(r = " + r
				+ "\\,\\text{cm}\\) e altura \\(h = " + h + "\\,\\text{cm}\\).");

		String correta = "\\(" + coef + "\\pi\\,\\text{cm}^2\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(" + (r * r * h) + "\\pi\\,\\text{cm}^2\\)");         // volume (πr²h)
		distratores.add("\\(" + (2 * r * (h + r)) + "\\pi\\,\\text{cm}^2\\)");   // área total
		distratores.add("\\(" + (coef + 2 * r * r) + "\\pi\\,\\text{cm}^2\\)");  // A_lat + 2πr²
		embaralharEAdicionarAlternativas(correta, distratores);

		String res = "\\(A_{\\text{lateral}} = 2\\pi r h = 2\\pi \\cdot " + r + " \\cdot " + h
				+ " = \\mathbf{" + coef + "\\pi}\\,\\text{cm}^2\\)";
		setResolucao(res);
	}
}
