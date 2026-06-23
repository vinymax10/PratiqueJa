package matematica.basico.multiplicacaonatural.nivel2package;

import matematica.GeradorExercicio;

public class FatorFaltante extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 2 + rand.nextInt(8);
		int a = 11 + rand.nextInt(89);
		int produto = a * b;

		if (rand.nextBoolean())
			addParagrafo("Qual é o valor de \\(\\square\\) na equação \\(\\square \\times " + b + " = " + produto + "\\)?");
		else
			addParagrafo("Qual é o valor de \\(\\square\\) na equação \\(" + b + " \\times \\square = " + produto + "\\)?");

		gerarAlternativasInteiras(a);

		addResolucao("Para encontrar o fator desconhecido, dividimos o produto pelo fator conhecido:");
		addResolucao("\\(\\square = " + produto + " \\div " + b + " = \\mathbf{" + a + "}\\)");
	}
}
