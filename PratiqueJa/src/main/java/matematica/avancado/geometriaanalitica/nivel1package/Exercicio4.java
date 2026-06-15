package matematica.avancado.geometriaanalitica.nivel1package;

import matematica.GeradorExercicio;

public class Exercicio4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int m = -3 + rand.nextInt(7);
		if (m == 0) m = 2;
		int b = -5 + rand.nextInt(11);
		boolean askM = rand.nextBoolean();

		String sinalB = b >= 0 ? " + " + b : " - " + Math.abs(b);
		String equacao = "y = " + m + "x" + sinalB;

		if (askM)
		{
			addParagrafo("Na reta \\(" + equacao + "\\), qual é o coeficiente angular \\(m\\)?");
			gerarAlternativasInteiras(m, 4, false);

			String res = "Na forma \\(y = mx + b\\), o coeficiente angular é o fator que multiplica \\(x\\)."
					+ "\\(\\\\\\)"
					+ "\\(" + equacao + " \\Rightarrow m = \\mathbf{" + m + "}\\)";
			setResolucao(res);
		}
		else
		{
			addParagrafo("Na reta \\(" + equacao + "\\), qual é o coeficiente linear \\(b\\)?");
			gerarAlternativasInteiras(b, 4, false);

			String res = "Na forma \\(y = mx + b\\), o coeficiente linear é o termo independente de \\(x\\)."
					+ "\\(\\\\\\)"
					+ "\\(" + equacao + " \\Rightarrow b = \\mathbf{" + b + "}\\)";
			setResolucao(res);
		}
	}
}
