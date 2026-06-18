package matematica.avancado.geometriaespacial.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Exercicio8 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int r    = 2 + rand.nextInt(5); // 2..6
		int h    = 2 + rand.nextInt(7); // 2..8
		int rph  = r + h;
		int coef = 2 * r * rph;

		addParagrafo("Calcule a área total de um cilindro com raio \\(r = " + r
				+ "\\,\\text{cm}\\) e altura \\(h = " + h + "\\,\\text{cm}\\).");

		String correta = "\\(" + coef + "\\pi\\,\\text{cm}^2\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(" + (2 * r * h) + "\\pi\\,\\text{cm}^2\\)");          // apenas lateral
		distratores.add("\\(" + (r * r * h) + "\\pi\\,\\text{cm}^2\\)");           // volume
		distratores.add("\\(" + (2 * r * (rph + 1)) + "\\pi\\,\\text{cm}^2\\)");  // off-by-1 no raio
		embaralharEAdicionarAlternativas(correta, distratores);

		String res = "\\(A_{\\text{total}} = 2\\pi r(h + r) = 2\\pi \\cdot " + r + " \\cdot (" + h + " + " + r
				+ ") = 2\\pi \\cdot " + r + " \\cdot " + rph + " = \\mathbf{" + coef + "\\pi}\\,\\text{cm}^2\\)";
		setResolucao(res);
	}
}
