package matematica.avancado.geometriaanalitica.nivel1package;

import matematica.GeradorExercicio;

public class Exercicio17 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int m = -3 + rand.nextInt(7);
		if (m == 0) m = 1;
		int dx = 1 + rand.nextInt(4);
		int dy = m * dx;
		int x1 = -4 + rand.nextInt(5);
		int y1 = -4 + rand.nextInt(5);
		int x2 = x1 + dx;
		int y2 = y1 + dy;

		addParagrafo("Calcule o coeficiente angular da reta que passa pelos pontos "
				+ "\\(A(" + x1 + ";\\;" + y1 + ")\\) e \\(B(" + x2 + ";\\;" + y2 + ")\\).");

		gerarAlternativasInteiras(m, 4, false);

		String res = "O coeficiente angular mede a inclinação da reta:"
				+ "\\(\\\\\\)"
				+ "\\(m = \\dfrac{y_2 - y_1}{x_2 - x_1} = \\dfrac{" + y2 + " - (" + y1 + ")}{" + x2 + " - (" + x1 + ")} = \\dfrac{" + dy + "}{" + dx + "} = \\mathbf{" + m + "}\\)";
		setResolucao(res);
	}
}
