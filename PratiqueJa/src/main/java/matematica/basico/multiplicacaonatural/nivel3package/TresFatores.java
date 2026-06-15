package matematica.basico.multiplicacaonatural.nivel3package;

import matematica.GeradorExercicio;

public class TresFatores extends GeradorExercicio
{
	// Pares (a, c) onde a × c é um número redondo — facilita o reagrupamento
	private static final int[][] PARES = {
		{25, 4},    // 25 × 4  = 100
		{50, 2},    // 50 × 2  = 100
		{125, 8},   // 125 × 8 = 1000
		{25, 40},   // 25 × 40 = 1000
		{50, 20}    // 50 × 20 = 1000
	};

	@Override
	protected void construir()
	{
		int[] par = PARES[rand.nextInt(PARES.length)];
		int a = par[0];
		int c = par[1];
		int ac = a * c;
		int n = 2 + rand.nextInt(49);
		int produto = ac * n;

		// embaralha a ordem dos três fatores na questão, mantendo a e c separados por n
		String fatores;
		switch (rand.nextInt(3))
		{
			case 0:  fatores = a + " \\times " + n + " \\times " + c; break;
			case 1:  fatores = n + " \\times " + a + " \\times " + c; break;
			default: fatores = c + " \\times " + n + " \\times " + a; break;
		}

		addParagrafo("Use a propriedade associativa para calcular, agrupando os fatores"
			+ " que resultam em um número redondo:");
		addParagrafo("\\(" + fatores + "\\)");

		gerarAlternativasInteiras(produto);

		String res = "Reagrupamos \\(" + a + "\\) e \\(" + c + "\\), pois \\("
			+ a + " \\times " + c + " = " + ac + "\\) (número redondo): \\(\\\\\\)";
		res += "\\(" + fatores + " = (" + a + " \\times " + c + ") \\times " + n
			+ " = " + ac + " \\times " + n + " = \\mathbf{" + produto + "}\\)";
		setResolucao(res);
	}
}
