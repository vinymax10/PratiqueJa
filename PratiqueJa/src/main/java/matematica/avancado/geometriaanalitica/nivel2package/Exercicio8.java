package matematica.avancado.geometriaanalitica.nivel2package;

import matematica.GeradorExercicio;

public class Exercicio8 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int m = -3 + rand.nextInt(7);
		if (m == 0) m = 1;
		int b = -5 + rand.nextInt(11);

		int x1 = -3 + rand.nextInt(4);
		int x2 = x1 + 1 + rand.nextInt(3);
		int y1 = m * x1 + b;
		int y2 = m * x2 + b;

		int dy = y2 - y1;
		int dx = x2 - x1;

		addParagrafo("Determine o coeficiente linear da reta que passa pelos pontos "
				+ "\\(A(" + x1 + ";\\;" + y1 + ")\\) e \\(B(" + x2 + ";\\;" + y2 + ")\\).");

		gerarAlternativasInteiras(b, 4, false);

		String res = "Calculamos primeiro o coeficiente angular e depois o linear:"
				+ "\\(\\\\\\)"
				+ "\\(m = \\dfrac{y_2 - y_1}{x_2 - x_1} = \\dfrac{" + dy + "}{" + dx + "} = " + m + "\\\\"
				+ "b = y_1 - m \\cdot x_1 = " + y1 + " - " + m + " \\cdot (" + x1 + ") = \\\\" 
				+ y1 + " - (" + (m * x1) + ") = \\mathbf{" + b + "}\\)";
		setResolucao(res);
	}
}
