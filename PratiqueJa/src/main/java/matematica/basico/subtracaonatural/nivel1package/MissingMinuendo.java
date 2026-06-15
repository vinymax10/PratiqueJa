package matematica.basico.subtracaonatural.nivel1package;

import matematica.GeradorExercicio;

public class MissingMinuendo extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 5 + rand.nextInt(15);
		int c = 10 + rand.nextInt(20);
		int a = b + c;

		addParagrafo("Qual é o valor de \\(\\square\\) na equação \\(\\square - " + b + " = " + c + "\\)?");

		gerarAlternativasInteiras(a);

		String res = "Para encontrar o minuendo desconhecido, somamos a diferença com o subtraendo: \\(\\\\\\)";
		res += "\\(\\square = " + c + " + " + b + " = \\mathbf{" + a + "}\\)";
		setResolucao(res);
	}
}
