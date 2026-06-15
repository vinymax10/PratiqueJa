package matematica.avancado.geometriaespacial.nivel1package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Exercicio3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int r    = 1 + rand.nextInt(6); // 1..6
		int r2   = r * r;
		int coef = 4 * r2;

		addParagrafo("Calcule a área da superfície de uma esfera de raio \\(r = " + r + "\\,\\text{cm}\\).");

		String correta = "\\(" + coef + "\\pi\\,\\text{cm}^2\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(" + r2 + "\\pi\\,\\text{cm}^2\\)");        // πr² (área do círculo máximo)
		distratores.add("\\(" + (2 * r2) + "\\pi\\,\\text{cm}^2\\)");  // 2πr²
		distratores.add("\\(" + (3 * r2) + "\\pi\\,\\text{cm}^2\\)");  // 3πr²
		embaralharEAdicionarAlternativas(correta, distratores);

		String res = "\\(A_{\\text{sup}} = 4\\pi r^2 = 4\\pi \\cdot " + r + "^2 = 4\\pi \\cdot "
				+ r2 + " = \\mathbf{" + coef + "\\pi}\\,\\text{cm}^2\\)";
		setResolucao(res);
	}
}
