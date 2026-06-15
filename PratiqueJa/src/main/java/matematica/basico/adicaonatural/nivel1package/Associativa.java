package matematica.basico.adicaonatural.nivel1package;

import matematica.GeradorExercicio;

public class Associativa extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(9);
		int b = 1 + rand.nextInt(9);
		int c = 1 + rand.nextInt(9);

		addParagrafo("Qual número deve substituir \\(\\square\\) para que a igualdade seja verdadeira?");
		addParagrafo("\\((" + a + " + " + b + ") + " + c + " = " + a + " + (\\square + " + c + ")\\)");

		gerarAlternativasInteiras(b);

		String res = "Pela propriedade associativa, a forma de agrupar as parcelas não altera a soma: \\(\\\\\\)";
		res += "\\((" + a + " + " + b + ") + " + c + " = " + a + " + (" + b + " + " + c + ")\\) \\(\\\\\\)";
		res += "Portanto, \\(\\square = \\mathbf{" + b + "}\\)";
		setResolucao(res);
	}
}
