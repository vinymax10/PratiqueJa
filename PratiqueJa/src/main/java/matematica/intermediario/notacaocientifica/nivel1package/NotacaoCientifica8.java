package matematica.intermediario.notacaocientifica.nivel1package;

import java.util.Arrays;
import matematica.GeradorExercicio;

// NC → número decimal: identificar o valor representado pela notação científica
public class NotacaoCientifica8 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		boolean positivo = rand.nextBoolean();
		int a = 1 + rand.nextInt(9); // 1..9
		int n = 3 + rand.nextInt(3); // 3..5

		if (positivo)
		{
			long N = a;
			for (int i = 0; i < n; i++) N *= 10;

			int a2 = (a < 9) ? a + 1 : a - 1;
			long N3 = a2;
			for (int i = 0; i < n; i++) N3 *= 10;

			String correta = "\\(" + formatarLatex(N) + "\\)";
			String e1     = "\\(" + formatarLatex(N * 10) + "\\)";
			String e2     = "\\(" + formatarLatex(N / 10) + "\\)";
			String e3     = "\\(" + formatarLatex(N3) + "\\)";

			addParagrafo("Qual número representa a notação científica?");
			addParagrafo("\\(" + a + " \\times 10^{" + n + "}\\)");
			embaralharEAdicionarAlternativas(correta, Arrays.asList(e1, e2, e3));
			setResolucao(
				"Deslocar a vírgula " + n + " casas para a direita:" +
				"\\(\\\\\\)" +
				"\\(" + a + " \\times 10^{" + n + "} = \\mathbf{" + formatarLatex(N) + "}\\)"
			);
		}
		else
		{
			int a2 = (a < 9) ? a + 1 : a - 1;

			String correta = "\\(" + buildSmall(a, n) + "\\)";
			String e1     = "\\(" + buildSmall(a, n + 1) + "\\)";
			String e2     = "\\(" + buildSmall(a, n - 1) + "\\)";
			String e3     = "\\(" + buildSmall(a2, n) + "\\)";

			addParagrafo("Qual número representa a notação científica?");
			addParagrafo("\\(" + a + " \\times 10^{-" + n + "}\\)");
			embaralharEAdicionarAlternativas(correta, Arrays.asList(e1, e2, e3));
			setResolucao(
				"Deslocar a vírgula " + n + " casas para a esquerda:" +
				"\\(\\\\\\)" +
				"\\(" + a + " \\times 10^{-" + n + "} = \\mathbf{" + buildSmall(a, n) + "}\\)"
			);
		}
	}

	private String buildSmall(int digit, int n)
	{
		StringBuilder sb = new StringBuilder("0{,}");
		for (int i = 0; i < n - 1; i++) sb.append("0");
		sb.append(digit);
		return sb.toString();
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
