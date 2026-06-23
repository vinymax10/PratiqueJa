package matematica.basico.subtracaonatural.nivel1package;

import matematica.GeradorExercicio;

public class NaoAssociativa extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int c = 2 + rand.nextInt(5);
		int b = c + 3 + rand.nextInt(7);
		int a = b + c + 5 + rand.nextInt(10);

		int ab = a - b;
		int left = ab - c;
		int bc = b - c;
		int right = a - bc;

		addParagrafo("Calcule \\((" + a + " - " + b + ") - " + c + "\\).");

		gerarAlternativasInteiras(left);

		addResolucao("Resolvemos o que está dentro dos parênteses primeiro:");
		addResolucao("\\((" + a + " - " + b + ") - " + c + " = " + ab + " - " + c + " = \\mathbf{" + left + "}\\)");
		addResolucao("Atenção: a subtração não é associativa — os parênteses importam!");
		addResolucao("Se a posição dos parênteses mudasse:");
		addResolucao("\\(" + a + " - (" + b + " - " + c + ") = " + a + " - " + bc + " = " + right + "\\)");
		addResolucao("Como \\(" + left + " \\neq " + right + "\\), os parênteses alteram o resultado.");
	}
}
