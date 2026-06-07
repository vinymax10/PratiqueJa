package matematica.intermediario.potenciacao.nivel2package;

import java.util.Arrays;

import matematica.GeradorExercicio;

public class Potenciacao6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		boolean grande = rand.nextBoolean();
		int a = 1 + rand.nextInt(9); // 1..9
		int n = 3 + rand.nextInt(4); // 3..6

		String numeroLatex, correta, e1, e2, e3, resolucao;

		if (grande)
		{
			long num = a;
			for (int i = 0; i < n; i++) num *= 10;
			numeroLatex = formatarLatex(num);
			correta    = "\\(" + a + " \\times 10^{" + n + "}\\)";
			e1         = "\\(" + a + " \\times 10^{" + (n + 1) + "}\\)";
			e2         = "\\(" + a + " \\times 10^{" + (n - 1) + "}\\)";
			e3         = "\\(" + (a < 9 ? a + 1 : a - 1) + " \\times 10^{" + n + "}\\)";
			resolucao  =
				"Mover a vírgula " + n + " casas para a esquerda:" +
				"\\(\\\\\\)" +
				"\\(" + numeroLatex + " = \\mathbf{" + a + " \\times 10^{" + n + "}}\\)";
		}
		else
		{
			StringBuilder sb = new StringBuilder("0{,}");
			for (int i = 0; i < n - 1; i++) sb.append("0");
			sb.append(a);
			numeroLatex = sb.toString();
			correta    = "\\(" + a + " \\times 10^{-" + n + "}\\)";
			e1         = "\\(" + a + " \\times 10^{-" + (n + 1) + "}\\)";
			e2         = "\\(" + a + " \\times 10^{-" + (n - 1) + "}\\)";
			e3         = "\\(" + (a < 9 ? a + 1 : a - 1) + " \\times 10^{-" + n + "}\\)";
			resolucao  =
				"Mover a vírgula " + n + " casas para a direita:" +
				"\\(\\\\\\)" +
				"\\(" + numeroLatex + " = \\mathbf{" + a + " \\times 10^{-" + n + "}}\\)";
		}

		addParagrafo("Escreva em notação científica \\((a \\times 10^n,\\; 1 \\leq a < 10)\\):");
		addParagrafo("\\(" + numeroLatex + "\\)");
		embaralharEAdicionarAlternativas(correta, Arrays.asList(e1, e2, e3));
		setResolucao(resolucao);
	}

	private String formatarLatex(long n)
	{
		String s = Long.toString(n);
		int len = s.length();
		int start = len % 3;
		if (start == 0) start = 3;
		StringBuilder sb = new StringBuilder(s.substring(0, start));
		for (int i = start; i < len; i += 3)
			sb.append("\\,").append(s, i, i + 3);
		return sb.toString();
	}
}
