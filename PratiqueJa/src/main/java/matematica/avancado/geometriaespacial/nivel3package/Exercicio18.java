package matematica.avancado.geometriaespacial.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

public class Exercicio18 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int r     = 2 + rand.nextInt(5); // 2..6 (lado menor = raio)
		int h     = r + 1 + rand.nextInt(5); // h > r (lado em torno do qual gira)
		int r2    = r * r;
		int vCoef = r2 * h;

		addParagrafo("Um retângulo de lados \\(" + r + "\\,\\text{cm}\\) e \\(" + h
				+ "\\,\\text{cm}\\) gira em torno do lado de \\(" + h
				+ "\\,\\text{cm}\\). Qual é o volume do sólido gerado?");

		String correta = "\\(" + vCoef + "\\pi\\,\\text{cm}^3\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(" + (r * h * h) + "\\pi\\,\\text{cm}^3\\)"); // girou em torno do lado r (r↔h trocados)
		distratores.add("\\(" + (2 * r * h) + "\\pi\\,\\text{cm}^3\\)"); // área lateral (confundida com volume)
		distratores.add("\\(" + (2 * r * (h + r)) + "\\pi\\,\\text{cm}^3\\)"); // área total
		embaralharEAdicionarAlternativas(correta, distratores);

		String res = "O retângulo gira em torno do lado \\(" + h
				+ "\\,\\text{cm}\\), gerando um cilindro com \\(r = " + r + "\\,\\text{cm}\\) e \\(h = "
				+ h + "\\,\\text{cm}\\):"
				+ "\\(\\\\\\)"
				+ "\\(V = \\pi r^2 h = \\pi \\cdot " + r + "^2 \\cdot " + h + " = \\pi \\cdot "
				+ r2 + " \\cdot " + h + " = \\mathbf{" + vCoef + "\\pi}\\,\\text{cm}^3\\)";
		setResolucao(res);
	}
}
