package matematica.intermediario.notacaocientifica.nivel1package;

import java.util.Arrays;
import matematica.GeradorExercicio;

// Conversão: número inteiro grande → notação científica (mantissa 1..9)
public class NotacaoCientifica11 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(9); // 1..9
		int n = 3 + rand.nextInt(5); // 3..7

		long N = a;
		for (int i = 0; i < n; i++) N *= 10;

		String nLatex = formatarLatex(N);
		int a2 = (a < 9) ? a + 1 : a - 1;

		String correta = "\\(" + a + " \\times 10^{" + n + "}\\)";
		String e1     = "\\(" + a + " \\times 10^{" + (n + 1) + "}\\)";
		String e2     = "\\(" + a + " \\times 10^{" + (n - 1) + "}\\)";
		String e3     = "\\(" + a2 + " \\times 10^{" + n + "}\\)";

		addParagrafo("Escreva em notação científica \\((a \\times 10^n,\\; 1 \\leq a < 10)\\):");
		addParagrafo("\\(" + nLatex + "\\)");
		embaralharEAdicionarAlternativas(correta, Arrays.asList(e1, e2, e3));
		setResolucao(
			"Deslocar a vírgula " + n + " casas para a esquerda:" +
			"\\(\\\\\\)" +
			"\\(" + nLatex + " = \\mathbf{" + a + " \\times 10^{" + n + "}}\\)"
		);
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
