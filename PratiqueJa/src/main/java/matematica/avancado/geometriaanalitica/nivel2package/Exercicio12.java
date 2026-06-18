package matematica.avancado.geometriaanalitica.nivel2package;

import matematica.GeradorExercicio;

public class Exercicio12 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int m  = -2 + rand.nextInt(6);
		if (m == 0) m = 1;
		int x1 = -3 + rand.nextInt(7);
		int y1 = -3 + rand.nextInt(7);
		int b  = y1 - m * x1;
		int x2 = x1 + 1 + rand.nextInt(4);
		int y2 = m * x2 + b;

		String sinalB  = b  >= 0 ? " + " + b  : " - " + Math.abs(b);

		addParagrafo("Uma reta com coeficiente angular \\(m = " + m + "\\) passa pelo ponto "
				+ "\\(P(" + x1 + ";\\;" + y1 + ")\\). Qual o valor de \\(y\\) quando \\(x = " + x2 + "\\)?");

		gerarAlternativasInteiras(y2, 4, false);

		String sinalMx1 = (m * x1) >= 0 ? " - " + (m * x1) : " + " + Math.abs(m * x1);

		String res = "Calculamos \\(b\\) e depois \\(y\\):"
				+ "\\(\\\\\\)"
				+ "\\(b = " + y1 + " - " + m + " \\cdot (" + x1 + ") = " + y1 + sinalMx1 + " = " + b + "\\\\"
				+ "y = " + m + " \\cdot " + x2 + sinalB + " = \\mathbf{" + y2 + "}\\)";
		setResolucao(res);
	}
}
