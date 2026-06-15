package matematica.basico.multiplicacaonatural.nivel2package;

import matematica.GeradorExercicio;

public class Distribuicao extends GeradorExercicio
{
	// Bases redondas para decomposição
	private static final int[] BASES = {50, 100, 150, 200};

	@Override
	protected void construir()
	{
		int a = 11 + rand.nextInt(89);
		int bRound = BASES[rand.nextInt(BASES.length)];
		boolean sub = rand.nextBoolean();
		int k = 1 + rand.nextInt(9);
		int n = sub ? bRound - k : bRound + k;
		int p1 = a * bRound;
		int pk = a * k;
		int produto = a * n;

		String decomp = sub ? bRound + " - " + k : bRound + " + " + k;
		String op = sub ? " - " : " + ";

		addParagrafo("Use a propriedade distributiva para calcular \\(" + a + " \\times " + n + "\\).");
		addParagrafo("Dica: \\(" + n + " = " + decomp + "\\)");

		gerarAlternativasInteiras(produto);

		String res = "Decompondo \\(" + n + " = " + decomp + "\\): \\(\\\\\\)";
		res += "\\(" + a + " \\times " + n
			+ " = " + a + " \\times " + bRound + op + a + " \\times " + k + " = \\\\ \\)";
		res += "\\(" + p1 + op + pk + " = \\mathbf{" + produto + "}\\)";
		setResolucao(res);
	}
}
