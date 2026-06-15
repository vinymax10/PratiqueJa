package matematica.basico.multiplicacaonatural.nivel3package;

import matematica.GeradorExercicio;

public class FatorFaltante extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 3 + rand.nextInt(7);
		int a = 100 + rand.nextInt(900);
		int produto = a * b;

		if (rand.nextBoolean())
			addParagrafo("Qual é o valor de \\(\\square\\) na equação"
				+ " \\(\\square \\times " + b + " = " + produto + "\\)?");
		else
			addParagrafo("Qual é o valor de \\(\\square\\) na equação"
				+ " \\(" + b + " \\times \\square = " + produto + "\\)?");

		gerarAlternativasInteiras(a);

		String res = "Para encontrar o fator desconhecido, dividimos o produto pelo fator conhecido: \\(\\\\\\)";
		res += "\\(\\square = " + produto + " \\div " + b + " = \\mathbf{" + a + "}\\)";
		setResolucao(res);
	}
}
