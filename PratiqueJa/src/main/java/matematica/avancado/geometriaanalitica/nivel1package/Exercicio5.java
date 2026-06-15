package matematica.avancado.geometriaanalitica.nivel1package;

import matematica.GeradorExercicio;

public class Exercicio5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int m = -3 + rand.nextInt(7);
		if (m == 0) m = 2;
		int b  = -5 + rand.nextInt(16);
		int x0 = -3 + rand.nextInt(7);
		int y  = m * x0 + b;

		String sinalB  = b  >= 0 ? " + " + b  : " - " + Math.abs(b);
		String equacao = "y = " + m + "x" + sinalB;

		addParagrafo("Na reta \\(" + equacao + "\\), qual o valor de \\(y\\) para \\(x = " + x0 + "\\)?");

		gerarAlternativasInteiras(y, 4, false);

		String res = "Substituindo \\(x = " + x0 + "\\) na equação:"
				+ "\\(\\\\\\)"
				+ "\\(y = " + m + " \\cdot (" + x0 + ")" + sinalB + " = " + (m * x0) + sinalB + " = \\mathbf{" + y + "}\\)";
		setResolucao(res);
	}
}
