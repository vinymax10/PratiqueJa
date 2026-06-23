package matematica.basico.multiplicacaonatural.nivel1package;

import matematica.GeradorExercicio;

public class Distributiva extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 2 + rand.nextInt(9);
		int b = 1 + rand.nextInt(9);
		int c = 1 + rand.nextInt(9);
		int n = b * 10 + c;
		int bTen = b * 10;
		int p1 = a * bTen;
		int p2 = a * c;
		int produto = a * n;

		addParagrafo("Use a propriedade distributiva para calcular \\(" + a + " \\times " + n + "\\).");
		addParagrafo("Dica: \\(" + n + " = " + bTen + " + " + c + "\\)");

		gerarAlternativasInteiras(produto);

		addResolucao("Decompondo \\(" + n + " = " + bTen + " + " + c
			+ "\\) e aplicando a propriedade distributiva:");
		addResolucao("\\(" + a + " \\times " + n + " = " + a + " \\times " + bTen
			+ " + " + a + " \\times " + c + " = " + p1 + " + " + p2 + " = \\mathbf{" + produto + "}\\)");
	}
}
