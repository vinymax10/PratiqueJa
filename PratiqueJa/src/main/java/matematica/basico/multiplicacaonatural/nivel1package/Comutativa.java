package matematica.basico.multiplicacaonatural.nivel1package;

import matematica.GeradorExercicio;

public class Comutativa extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 2 + rand.nextInt(9);
		int b = 2 + rand.nextInt(9);
		while (b == a)
			b = 2 + rand.nextInt(9);
		int produto = a * b;

		addParagrafo("Sabendo que \\(" + a + " \\times " + b + " = " + produto
			+ "\\), qual é o valor de \\(" + b + " \\times " + a + "\\)?");

		gerarAlternativasInteiras(produto);

		String res = "Pela propriedade comutativa, a ordem dos fatores não altera o produto: \\(\\\\\\)";
		res += "\\(" + b + " \\times " + a + " = " + a + " \\times " + b + " = \\mathbf{" + produto + "}\\)";
		setResolucao(res);
	}
}
