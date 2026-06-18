package matematica.intermediario.notacaocientifica.nivel1package;

import java.util.Arrays;
import matematica.GeradorExercicio;

// Conversão: número decimal pequeno → notação científica (expoente negativo)
public class NotacaoCientifica12 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(9); // 1..9
		int n = 3 + rand.nextInt(4); // 3..6

		// 0,000...a  (n-1 zeros após a vírgula, depois o dígito a)
		String nLatex = buildSmall(a, n);
		int a2 = (a < 9) ? a + 1 : a - 1;

		String correta = "\\(" + a + " \\times 10^{-" + n + "}\\)";
		String e1     = "\\(" + a + " \\times 10^{-" + (n + 1) + "}\\)";
		String e2     = "\\(" + a + " \\times 10^{-" + (n - 1) + "}\\)";
		String e3     = "\\(" + a2 + " \\times 10^{-" + n + "}\\)";

		addParagrafo("Escreva em notação científica \\((a \\times 10^n,\\; 1 \\leq a < 10)\\):");
		addParagrafo("\\(" + nLatex + "\\)");
		embaralharEAdicionarAlternativas(correta, Arrays.asList(e1, e2, e3));
		setResolucao(
			"Deslocar a vírgula " + n + " casas para a direita:" +
			"\\(\\\\\\)" +
			"\\(" + nLatex + " = \\mathbf{" + a + " \\times 10^{-" + n + "}}\\)"
		);
	}

	private String buildSmall(int digit, int n)
	{
		StringBuilder sb = new StringBuilder("0{,}");
		for (int i = 0; i < n - 1; i++) sb.append("0");
		sb.append(digit);
		return sb.toString();
	}
}
