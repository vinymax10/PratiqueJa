package matematica.basico.subtracaonatural.nivel1package;

import matematica.GeradorExercicio;

public class MissingSubtraendo extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 25 + rand.nextInt(35);
		int c = 5 + rand.nextInt(15);
		int b = a - c;

		addParagrafo("Qual é o valor de \\(\\square\\) na equação \\(" + a + " - \\square = " + c + "\\)?");

		gerarAlternativasInteiras(b);

		String res = "Para encontrar o subtraendo desconhecido, subtraímos a diferença do minuendo: \\(\\\\\\)";
		res += "\\(\\square = " + a + " - " + c + " = \\mathbf{" + b + "}\\)";
		setResolucao(res);
	}
}
