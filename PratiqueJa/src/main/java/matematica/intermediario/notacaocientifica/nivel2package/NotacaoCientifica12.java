package matematica.intermediario.notacaocientifica.nivel2package;

import java.util.Arrays;
import matematica.GeradorExercicio;

// Conversão com mantissa decimal: N → a,d × 10^k (dois algarismos significativos)
public class NotacaoCientifica12 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(9); // primeiro dígito: 1..9
		int d = 1 + rand.nextInt(9); // segundo dígito: 1..9
		int k = 3 + rand.nextInt(4); // expoente: 3..6

		// N = (10a + d) × 10^{k-1}
		long N = (long)(10 * a + d);
		for (int i = 0; i < k - 1; i++) N *= 10;

		String nLatex   = formatarLatex(N);
		String mantissa = a + "{,}" + d;

		// Forma não ajustada (erro comum): (10a+d) × 10^{k-1}
		int naoAjustado = 10 * a + d;
		String correta = "\\(" + mantissa + " \\times 10^{" + k + "}\\)";
		String e1     = "\\(" + mantissa + " \\times 10^{" + (k + 1) + "}\\)";
		String e2     = "\\(" + mantissa + " \\times 10^{" + (k - 1) + "}\\)";
		String e3     = "\\(" + naoAjustado + " \\times 10^{" + (k - 1) + "}\\)"; // não ajustado

		addParagrafo("Escreva em notação científica \\((a \\times 10^n,\\; 1 \\leq a < 10)\\):");
		addParagrafo("\\(" + nLatex + "\\)");
		embaralharEAdicionarAlternativas(correta, Arrays.asList(e1, e2, e3));
		setResolucao(
			"Posicionar a vírgula após o primeiro algarismo significativo:" +
			"\\(\\\\\\)" +
			"\\(" + nLatex + " \\rightarrow " + mantissa + "\\ldots\\)" +
			"\\(\\\\\\)" +
			"Contar \\(" + k + "\\) casas deslocadas, expoente \\(" + k + "\\):" +
			"\\(\\\\\\)" +
			"\\(" + nLatex + " = \\mathbf{" + mantissa + " \\times 10^{" + k + "}}\\)"
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
