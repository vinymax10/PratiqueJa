package matematica.basico.multiplicacaonatural.nivel1package;

import matematica.GeradorExercicio;

public class FatorFaltante extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 2 + rand.nextInt(9);
		int a = 2 + rand.nextInt(9);
		int produto = a * b;

		if (rand.nextBoolean())
			addParagrafo("Qual é o valor de \\(\\square\\) na equação \\(\\square \\times " + b + " = " + produto + "\\)?");
		else
			addParagrafo("Qual é o valor de \\(\\square\\) na equação \\(" + b + " \\times \\square = " + produto + "\\)?");

		gerarAlternativasInteiras(a);

		String res = "Para encontrar o fator desconhecido, dividimos o produto pelo fator conhecido: \\(\\\\\\)";
		res += "\\(\\square = " + produto + " \\div " + b + " = \\mathbf{" + a + "}\\)";
		setResolucao(res);
	}
}
