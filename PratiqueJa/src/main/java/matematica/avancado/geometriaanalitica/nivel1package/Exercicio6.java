package matematica.avancado.geometriaanalitica.nivel1package;

import matematica.GeradorExercicio;

public class Exercicio6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int m  = -3 + rand.nextInt(7);
		if (m == 0) m = 2;
		int b  = -5 + rand.nextInt(16);
		int x0 = -3 + rand.nextInt(7);
		int y0 = m * x0 + b;

		addParagrafo("Uma reta com coeficiente angular \\(m = " + m + "\\) passa pelo ponto "
				+ "\\(P(" + x0 + ";\\;" + y0 + ")\\). Qual o coeficiente linear \\(b\\)?");

		gerarAlternativasInteiras(b, 4, false);

		String sinalMx0 = (m * x0) >= 0 ? " - " + (m * x0) : " + " + Math.abs(m * x0);

		String res = "Isolando \\(b\\) na forma \\(y = mx + b\\):"
				+ "\\(\\\\\\)"
				+ "\\(b = " + y0 + " - " + m + " \\cdot (" + x0 + ") = " + y0 + sinalMx0 + " = \\mathbf{" + b + "}\\)";
		setResolucao(res);
	}
}
