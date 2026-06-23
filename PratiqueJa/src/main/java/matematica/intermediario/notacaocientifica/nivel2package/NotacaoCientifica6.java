package matematica.intermediario.notacaocientifica.nivel2package;

import java.util.Arrays;
import matematica.GeradorExercicio;

// Subtração igualando expoentes: (a×10^n) - (b×10^{n-1}) = (a - b/10) × 10^n
public class NotacaoCientifica6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b, num;
		do
		{
			a   = 2 + rand.nextInt(8); // 2..9
			b   = 1 + rand.nextInt(9); // 1..9
			num = 10 * a - b;          // deve ser ≥ 10 para mantissa ≥ 1
		}
		while (num < 10);

		int n           = 4 + rand.nextInt(4); // 4..7
		int resultInt   = num / 10;
		int resultDec   = num % 10;
		String mantissa = resultInt + "{,}" + resultDec;

		// erro comum: adicionou em vez de subtrair → a,b × 10^n
		String mantissaErrada = a + "{,}" + b;

		String correta = "\\(" + mantissa + " \\times 10^{" + n + "}\\)";
		String e1     = "\\(" + mantissaErrada + " \\times 10^{" + n + "}\\)";    // somou ao invés de subtrair
		String e2     = "\\(" + mantissa + " \\times 10^{" + (n + 1) + "}\\)";
		String e3     = "\\(" + mantissa + " \\times 10^{" + (n - 1) + "}\\)";

		addParagrafo("Calcule, deixando o resultado em notação científica:");
		addParagrafo("\\(" + a + " \\times 10^{" + n + "} - " + b + " \\times 10^{" + (n - 1) + "}\\)");
		embaralharEAdicionarAlternativas(correta, Arrays.asList(e1, e2, e3));
		addResolucao("Converter o segundo termo para o expoente " + n + ":");
		addResolucao("\\(" + b + " \\times 10^{" + (n - 1) + "} = 0{,}" + b + " \\times 10^{" + n + "}\\)");
		addResolucao("Subtrair as mantissas com mesmo expoente:");
		addResolucao("\\((" + a + " - 0{,}" + b + ") \\times 10^{" + n + "} = \\mathbf{" + mantissa + " \\times 10^{" + n + "}}\\)");
	}
}
