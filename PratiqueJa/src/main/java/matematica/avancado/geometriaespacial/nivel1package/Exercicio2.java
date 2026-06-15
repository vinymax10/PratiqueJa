package matematica.avancado.geometriaespacial.nivel1package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Exercicio2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int r     = 2 + rand.nextInt(5); // 2..6
		int h     = 2 + rand.nextInt(7); // 2..8
		int r2    = r * r;
		int vCoef = r2 * h;

		addParagrafo("Um cilindro tem raio \\(r = " + r + "\\,\\text{cm}\\) e altura \\(h = " + h
				+ "\\,\\text{cm}\\). Calcule o volume em função de \\(\\pi\\).");

		String correta = "\\(" + vCoef + "\\pi\\,\\text{cm}^3\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(" + (2 * r * h) + "\\pi\\,\\text{cm}^3\\)");       // 2rh (área lateral)
		distratores.add("\\(" + (vCoef + r2) + "\\pi\\,\\text{cm}^3\\)");       // r²(h+1)
		distratores.add("\\(" + (r2 * (h + r)) + "\\pi\\,\\text{cm}^3\\)");     // r²(h+r)
		embaralharEAdicionarAlternativas(correta, distratores);

		String res = "\\(V = \\pi r^2 h = \\pi \\cdot " + r + "^2 \\cdot " + h
				+ " = \\pi \\cdot " + r2 + " \\cdot " + h + " = \\mathbf{" + vCoef + "\\pi}\\,\\text{cm}^3\\)";
		setResolucao(res);
	}
}
