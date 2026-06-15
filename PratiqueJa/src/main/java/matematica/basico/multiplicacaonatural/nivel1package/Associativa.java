package matematica.basico.multiplicacaonatural.nivel1package;

import matematica.GeradorExercicio;

public class Associativa extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 2 + rand.nextInt(5);
		int b = 2 + rand.nextInt(5);
		int c = 2 + rand.nextInt(5);
		int produto = a * b * c;

		addParagrafo("Qual número deve substituir \\(\\square\\) para que a igualdade seja verdadeira?");
		addParagrafo("\\((" + a + " \\times " + b + ") \\times " + c
			+ " = " + a + " \\times (\\square \\times " + c + ")\\)");

		gerarAlternativasInteiras(b);

		String res = "Pela propriedade associativa, o agrupamento dos fatores não altera o produto: \\(\\\\\\)";
		res += "\\((" + a + " \\times " + b + ") \\times " + c
			+ " = " + a + " \\times (" + b + " \\times " + c + ")\\) \\(\\\\\\)";
		res += "Portanto, \\(\\square = \\mathbf{" + b + "}\\)";
		setResolucao(res);
	}
}
