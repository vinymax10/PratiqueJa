package matematica.intermediario.notacaocientifica.nivel2package;

import java.util.Arrays;
import matematica.GeradorExercicio;

// Adição com igualação de expoentes: (a×10^n) + (b×10^{n-1}) = a,b × 10^n
public class NotacaoCientifica5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b;
		do
		{
			a = 1 + rand.nextInt(8); // 1..8
			b = 1 + rand.nextInt(8); // 1..8
		}
		while (a + b >= 10); // garante distrator a+b < 10 e em NC

		int n = 4 + rand.nextInt(4); // 4..7

		// resultado = a,b × 10^n
		String mantissa = a + "{,}" + b;

		String correta = "\\(" + mantissa + " \\times 10^{" + n + "}\\)";
		String e1     = "\\(" + (a + b) + " \\times 10^{" + n + "}\\)";       // soma direta, sem igualar
		String e2     = "\\(" + mantissa + " \\times 10^{" + (n + 1) + "}\\)"; // expoente errado
		String e3     = "\\(" + mantissa + " \\times 10^{" + (n - 1) + "}\\)"; // expoente errado

		addParagrafo("Calcule, deixando o resultado em notação científica:");
		addParagrafo("\\(" + a + " \\times 10^{" + n + "} + " + b + " \\times 10^{" + (n - 1) + "}\\)");
		embaralharEAdicionarAlternativas(correta, Arrays.asList(e1, e2, e3));
		setResolucao(
			"Converter o segundo termo para o expoente " + n + ":" +
			"\\(\\\\\\)" +
			"\\(" + b + " \\times 10^{" + (n - 1) + "} = 0{,}" + b + " \\times 10^{" + n + "}\\)" +
			"\\(\\\\\\)" +
			"Somar as mantissas com mesmo expoente:" +
			"\\(\\\\\\)" +
			"\\((" + a + " + 0{,}" + b + ") \\times 10^{" + n + "} = \\mathbf{" + mantissa + " \\times 10^{" + n + "}}\\)"
		);
	}
}
