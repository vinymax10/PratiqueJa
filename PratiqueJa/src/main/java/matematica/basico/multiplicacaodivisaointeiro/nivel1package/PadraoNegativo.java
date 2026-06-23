package matematica.basico.multiplicacaodivisaointeiro.nivel1package;

import matematica.GeradorExercicio;

public class PadraoNegativo extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int k = 2 + rand.nextInt(4);

		addParagrafo("Observe o padrão de multiplicações por \\(-" + k + "\\):");

		String padrao = "\\(3 \\times (-" + k + ") = " + (3 * -k) + "\\), \\(\\\\\\)";
		padrao += "\\(2 \\times (-" + k + ") = " + (2 * -k) + "\\), \\(\\\\\\)";
		padrao += "\\(1 \\times (-" + k + ") = " + (-k) + "\\), \\(\\\\\\)";
		padrao += "\\(0 \\times (-" + k + ") = 0\\).";
		addParagrafo(padrao);
		addParagrafo("Qual deve ser o próximo valor? \\((-1) \\times (-" + k + ") = \\,?\\)");

		gerarAlternativasInteirasComNegativos(k);

		addResolucao("Cada vez que o multiplicador diminui \\(1\\), o produto aumenta \\(+" + k + "\\):");
		addResolucao("\\(\\ldots, " + (-2 * k) + ", " + (-k) + ", 0, \\ldots\\) — o próximo é \\(0 + " + k + " = +" + k + "\\).");
		addResolucao("Isso confirma: \\((-1) \\times (-" + k + ") = \\mathbf{+" + k + "}\\).");
		addResolucao("Negativo \\(\\times\\) negativo \\(=\\) positivo!");
	}
}
