package matematica.avancado.funcaoexponencial.nivel3package;

import matematica.GeradorExercicio;

public class Expressao2 extends GeradorExercicio
{
	// Casos pré-definidos que produzem montante inteiro
	// {C, taxa%, t, M, powNum, powDen, baseStr, decStr}
	private static final int[][]   CASOS   = {
		{1000, 10, 2, 1210},
		{1000, 10, 3, 1331},
		{2000, 10, 2, 2420},
		{1000, 20, 2, 1440},
		{2000, 20, 3, 3456},
		{1600, 25, 2, 2500},
	};
	private static final String[]  BASE_STR  = {"1{,}10","1{,}10","1{,}10","1{,}20","1{,}20","1{,}25"};
	private static final String[]  DEC_STR   = {"1{,}21","1{,}331","1{,}21","1{,}44","1{,}728","1{,}5625"};
	private static final String[]  TAXA_STR  = {"10","10","10","20","20","25"};

	@Override
	protected void construir()
	{
		int idx = rand.nextInt(CASOS.length);
		int C   = CASOS[idx][0];
		int p   = CASOS[idx][1];
		int t   = CASOS[idx][2];
		int M   = CASOS[idx][3];
		String baseStr = BASE_STR[idx];
		String decStr  = DEC_STR[idx];
		String taxaStr = TAXA_STR[idx];

		addParagrafo("Um capital de \\(R\\$\\," + C + "{,}00\\) é aplicado a juros compostos de "
			+ "\\(" + taxaStr + "\\%\\) ao período. "
			+ "Qual é o montante após \\(" + t + "\\) períodos? "
			+ "Use \\((" + baseStr + ")^{" + t + "} = " + decStr + "\\).");

		String res = "O montante é dado por \\(M = C \\cdot (1 + i)^t\\): \\(\\\\\\)";
		res += "\\(M = " + C + " \\cdot (1 + 0{,}" + (p < 10 ? "0" : "") + p + ")^{" + t + "}\\\\";
		res += "M = " + C + " \\cdot (" + baseStr + ")^{" + t + "}\\\\";
		res += "M = " + C + " \\cdot " + decStr + " = \\mathbf{R\\$\\," + M + "{,}00}\\)";

		gerarAlternativas("" + M);
		setResolucao(res);
	}
}
