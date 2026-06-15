package matematica.basico.divisaonatural.nivel1package;

import matematica.GeradorExercicio;

public class MissingFator extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 2 + rand.nextInt(9);
		int q = 2 + rand.nextInt(9);
		int a = b * q;

		if (rand.nextBoolean())
		{
			addParagrafo("Qual é o valor de \\(\\square\\) em \\(\\square \\div " + b + " = " + q + "\\)?");
			gerarAlternativasInteiras(a);

			String res = "Para encontrar o dividendo desconhecido, multiplicamos o divisor pelo quociente: \\(\\\\\\)";
			res += "\\(\\square = " + b + " \\times " + q + " = \\mathbf{" + a + "}\\)";
			setResolucao(res);
		}
		else
		{
			addParagrafo("Qual é o valor de \\(\\square\\) em \\(" + a + " \\div \\square = " + q + "\\)?");
			gerarAlternativasInteiras(b);

			String res = "Para encontrar o divisor desconhecido, dividimos o dividendo pelo quociente: \\(\\\\\\)";
			res += "\\(\\square = " + a + " \\div " + q + " = \\mathbf{" + b + "}\\)";
			setResolucao(res);
		}
	}
}
